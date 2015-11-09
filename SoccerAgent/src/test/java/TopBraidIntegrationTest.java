import ch.qos.logback.classic.Level;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileUtils;
import dd.soccer.perception.perceptingobjects.BodyState;
import dd.soccer.perception.perceptingobjects.NavigatingLandmark;
import org.junit.Test;
import org.topbraid.spin.arq.ARQFactory;
import org.topbraid.spin.inference.SPINInferences;
import org.topbraid.spin.system.SPINModuleRegistry;
import org.topbraid.spin.util.JenaUtil;

;

/**
 * Created by Sergey on 04.11.2015.
 */
public class TopBraidIntegrationTest {

    @Test
    public void test() throws InterruptedException {


        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);

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

        System.out.println("-=============================================-");


        OntClass senseBody = ontModel.getOntClass(ns + BodyState.class.getSimpleName());
        //ontModel.createIndividual("some_body_state", senseBody);


        // Run all inferences
        SPINInferences.run(ontModel, newTriples, null, null, false, null);
        System.out.println("Inferred triples: " + newTriples.size());

        System.out.println("Add new triples to the model...");
        ontModel.add(newTriples);

        System.out.print("Clear new triples model: ");
        newTriples.removeAll();
        System.out.println(newTriples.size());


        System.out.println("Check that new triples in the model...");
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


        senseBody = ontModel.getOntClass(ns + NavigatingLandmark.class.getSimpleName());
        ontModel.createIndividual("some_body_state", senseBody);

        System.out.println("Check that no the same triples are generated....");
        // Run all inferences
        SPINInferences.run(ontModel, newTriples, null, null, false, null);
        System.out.println("Inferred triples: " + newTriples.size());


    }

}
