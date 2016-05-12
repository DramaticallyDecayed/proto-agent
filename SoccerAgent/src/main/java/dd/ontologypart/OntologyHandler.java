package dd.ontologypart;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileUtils;
import dd.ontologyinterchanger.BareModelInterchanger;
import dd.ontologyinterchanger.QuieringUtils;
import org.topbraid.spin.system.SPINModuleRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 12.05.2016.
 */
public class OntologyHandler {

    public void init(String ontologyFileName) {
        SPINModuleRegistry.get().init();
        BareModelInterchanger bmi = new BareModelInterchanger(
                getClass().getClassLoader().getResource(ontologyFileName).toString(),
                FileUtils.langTurtle);
        System.out.println("START");
        bmi.runInference();
        bmi.commitInference();
        System.out.println("new triples: " + bmi.getNewTriples().size());

        List<String> nodeNames = getNewNodes(bmi.getOntModel());
        bmi.commitModel(constructNode(bmi.getOntModel(), nodeNames.get(0)));
        bmi.commitModel(constructNode(bmi.getOntModel(), nodeNames.get(1)));

        bmi.runInference();
        nodeNames = getNewNodes(bmi.getNewTriples());
        bmi.commitInference();

        System.out.println(nodeNames);
        System.out.println("new triples: " + bmi.getNewTriples().size());
    }

    private static Model constructNode(Model ontModel, String nodeName) {
        Model model = QuieringUtils.constructQuery(
                ontModel,
                "CONSTRUCT{" +
                        "?node core2ed:hasDerivative ?actderivative ." +
                        "}" +
                        "WHERE {" +
                        "BIND(:" + nodeName + " AS ?node)" +
                        "    ?node a core2ed:Node ." +
                        "?node core2ed:implement ?cu ." +
                        "?cu core2ed:hasDerivative ?actderivative ." +
                        "MINUS{" +
                        "?node core2ed:hasDerivative ?derivative ." +
                        "}" +
                        "}"
        );
        return model;
    }

    private static List<String> getNewNodes(Model ontModel) {
        ResultSet resultSet = QuieringUtils.getQueryResults(
                ontModel,
                "SELECT ?node " +
                        "WHERE {" +
                        "?node a core2ed:Node ." +
                        "}"
        );
        List<String> nodeNames = new ArrayList<>();
        for (; resultSet.hasNext(); ) {
            QuerySolution qs = resultSet.next();
            nodeNames.add(qs.getResource("node").getLocalName());
        }
        return nodeNames;
    }
}
