import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.util.FileUtils;
import dd.soccer.perception.perceptingobjects.BodyState;
import org.junit.Test;
import org.topbraid.spin.arq.ARQFactory;
import org.topbraid.spin.inference.SPINInferences;;
import org.topbraid.spin.system.SPINModuleRegistry;
import org.topbraid.spin.util.JenaUtil;

import java.util.concurrent.TimeUnit;

/**
 * Created by Sergey on 04.11.2015.
 */
public class TopBraidIntegrationTest {

    @Test
    public void test() throws InterruptedException {
        // Initialize system functions and templates
        SPINModuleRegistry.get().init();

        System.out.println("Loading domain ontology...");
        Model baseModel = ModelFactory.createDefaultModel();
        baseModel.read("D:\\code\\IdeaProjects\\ProtoAgent\\KB.ttl", FileUtils.langTurtle);

        // Create OntModel with imports
        OntModel ontModel = JenaUtil.createOntologyModel(OntModelSpec.OWL_MEM, baseModel);

        // Register locally defined functions
        SPINModuleRegistry.get().registerAll(ontModel, null);


        // Create and add Model for inferred triples
        Model newTriples = ModelFactory.createDefaultModel();
        ontModel.addSubModel(newTriples);
        newTriples.setNsPrefixes(baseModel.getNsPrefixMap());

        String ns = baseModel.getNsPrefixMap().get("");

        OntClass senseBody = ontModel.getOntClass(ns + BodyState.class.getSimpleName());
        ontModel.createIndividual("some_body_state", senseBody);

        // Run all inferences
        SPINInferences.run(ontModel, newTriples, null, null, false, null);
        System.out.println("Inferred triples: " + newTriples.size());

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT\n" +
                "*\n" +
                "WHERE {\n" +
                "\t?i a :Node.\n" +
                "}");

        Query query = ARQFactory.get().createQuery(ontModel, sb.toString());
        QueryExecution qe = ARQFactory.get().createQueryExecution(query, ontModel);
        ResultSet results = qe.execSelect();
        ResultSetFormatter.out(System.out, results, query);
        qe.close();

    }

}
