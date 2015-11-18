import ch.qos.logback.classic.Level;
import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.graph.compose.MultiUnion;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.util.FileUtils;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.util.iterator.Filter;
import com.hp.hpl.jena.vocabulary.RDF;
import dd.soccer.perception.perceptingobjects.BodyState;
import dd.soccer.perception.perceptingobjects.NavigatingLandmark;
import org.junit.Before;
import org.junit.Test;
import org.topbraid.spin.arq.ARQFactory;
import org.topbraid.spin.inference.DefaultSPINRuleComparator;
import org.topbraid.spin.inference.SPINInferences;
import org.topbraid.spin.inference.SPINRuleComparator;
import org.topbraid.spin.system.SPINModuleRegistry;
import org.topbraid.spin.util.CommandWrapper;
import org.topbraid.spin.util.JenaUtil;
import org.topbraid.spin.util.SPINQueryFinder;
import org.topbraid.spin.vocabulary.SPIN;

;import java.util.List;
import java.util.Map;

/**
 * Created by Sergey on 04.11.2015.
 */
public class TopBraidIntegrationTest {

    private Model baseModel;
    private OntModel ontModel;
    private Model newTriples;
    private String ns;

    @Before
    public void doBefore(){
        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);
        SPINModuleRegistry.get().init();
        loadMainModel();
        createModelForNewTriples();
    }

    private void loadMainModel(){
        baseModel = ModelFactory.createDefaultModel();
        System.out.println("Loading domain ontology...");
        baseModel.read("D:\\code\\IdeaProjects\\ProtoAgent\\KB.ttl", FileUtils.langTurtle);
        ontModel = JenaUtil.createOntologyModel(OntModelSpec.OWL_MEM, baseModel);
        ns = baseModel.getNsPrefixMap().get("");
        SPINModuleRegistry.get().registerAll(ontModel, null);
    }

    private void createModelForNewTriples(){
        newTriples = ModelFactory.createDefaultModel();
        ontModel.addSubModel(newTriples);
        newTriples.setNsPrefixes(baseModel.getNsPrefixMap());
    }

    private void insertTestEntity(){
        OntClass senseBody = ontModel.getOntClass(ns + BodyState.class.getSimpleName());
        ontModel.createIndividual("some_body_state", senseBody);
    }

    @Test
    public void testWOOWLRL(){
        insertTestEntity();
        long start = System.currentTimeMillis();
        SPINInferences.run(ontModel, newTriples, null, null, false, null);
        long end = System.currentTimeMillis();
        System.out.println("Inferred triples: " + newTriples.size() + " during " + (end-start));
        showNodes(ontModel);
        findSpecialSee(ontModel);
    }

    private Map<Resource,List<CommandWrapper>> cls2Query;
    private Map<Resource,List<CommandWrapper>> cls2Constructor;
    private SPINRuleComparator comparator;

    private void owlrlLoad(){
        System.out.println("Loading OWL RL ontology...");
        OntModel owlrlModel = loadModelWithImports("http://topbraid.org/spin/owlrl-all");
        SPINModuleRegistry.get().registerAll(owlrlModel, null);
        MultiUnion multiUnion = JenaUtil.createMultiUnion(new Graph[] {
                ontModel.getGraph(),
                owlrlModel.getGraph()
        });
        Model unionModel = ModelFactory.createModelForGraph(multiUnion);
        cls2Query = SPINQueryFinder.getClass2QueryMap(unionModel, ontModel, SPIN.rule, true, false);
        cls2Constructor = SPINQueryFinder.getClass2QueryMap(ontModel, ontModel, SPIN.constructor, true, false);
        comparator = new DefaultSPINRuleComparator(ontModel);
    }

    @Test
    public void testWithTBOWLRLForSeeRelation(){
        owlrlLoad();
        long start = System.currentTimeMillis();
        SPINInferences.run(ontModel, newTriples, cls2Query, cls2Constructor, null, null, false, SPIN.rule, comparator, null);
        long end = System.currentTimeMillis();
        System.out.println("Inferred triples: " + newTriples.size() + " during " + (end-start));
        showNodes(ontModel);
        findSpecialSee(ontModel);
    }

    @Test
    public void testWithTBOWLRL(){
        owlrlLoad();
        insertTestEntity();
        long start = System.currentTimeMillis();
        SPINInferences.run(ontModel, newTriples, cls2Query, cls2Constructor, null, null, false, SPIN.rule, comparator, null);
        long end = System.currentTimeMillis();
        System.out.println("Inferred triples: " + newTriples.size() + " during " + (end-start));
        showNodes(ontModel);
        findSpecialSee(ontModel);
    }

    @Test
    public void testOnSaveAndRepeat(){
        insertTestEntity();
        SPINInferences.run(ontModel, newTriples, null, null, false, null);
        System.out.println("Inferred triples: " + newTriples.size());
        ontModel.add(newTriples);
        newTriples.removeAll();
        SPINInferences.run(ontModel, newTriples, null, null, false, null);
        System.out.println("Inferred triples: " + newTriples.size());
    }

    @Test
    public void testOnSeeRelationInferenceWithJenaReasoner(){
        findSpecialSee(useJenaReasoner(ontModel));
    }

    @Test
    public void testOnTeamWork(){
        insertTestEntity();
        long start = System.currentTimeMillis();
        findSpecialSee(useJenaReasoner(ontModel));
        //findSpecialSee(anotherResoner(ontModel));
        SPINInferences.run(ontModel, newTriples, null, null, false, null);
        long end = System.currentTimeMillis();
        System.out.println("Inferred triples: " + newTriples.size() + " during " + (end-start));
        showNodes(ontModel);
        findSpecialSee(ontModel);
    }

    private  OntModel useJenaReasoner(OntModel ontModel){
        Reasoner reasoner = ReasonerRegistry.getOWLMicroReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_LITE_MEM;
        ontModelSpec.setReasoner(reasoner);
        long start = System.currentTimeMillis();
        OntModel model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
        long end = System.currentTimeMillis();
        System.out.println((end-start));
        return model;
    }

    private Model anotherResoner(OntModel ontModel){
        Reasoner reasoner = ReasonerRegistry.getOWLMicroReasoner();
        long start = System.currentTimeMillis();
        InfModel pModel = ModelFactory.createInfModel(reasoner, ontModel);
        long end = System.currentTimeMillis();
        System.out.println((end-start));
        return pModel;

    }

    private void findSpecialSee(Model model){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ?i ?sc \n" +
                "WHERE { \n" +
                "\t?sc (rdfs:subClassOf)* :ModelRelation .\n" +
                "\t?i a ?sc .\n" +
                "}");
        Query query = ARQFactory.get().createQuery(model, sb.toString());
        QueryExecution qe = ARQFactory.get().createQueryExecution(query, model);
        ResultSet rs = qe.execSelect();


        while (rs.hasNext()) {
            QuerySolution row = rs.nextSolution();
            Statement s = ResourceFactory.createStatement(row.getResource("i"), RDF.type, row.getResource("sc"));
            ontModel.add(s);
        }
        ResultSetFormatter.out(System.out, rs, query);
        qe.close();
    }

    private void showStatements(Model newTriples){
        System.out.println("--------------------------------------------------------");
        StmtIterator stmtIterator = newTriples.listStatements();
        while(stmtIterator.hasNext()){
            System.out.println(stmtIterator.nextStatement());
        }
        System.out.println("--------------------------------------------------------");
    }

    private void showNodes(OntModel ontModel){
        System.out.println("--------------------------------------------------------");
        System.out.println("Check that new triples in the model...");
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT\n" +
                "DISTINCT ?node\n" +
                "WHERE {\n" +
                "\t?i a :Node.\n" +
                "\t?node :isNodeActive true . \n" +
                "}");

        Query query = ARQFactory.get().createQuery(ontModel, sb.toString());
        QueryExecution qe = ARQFactory.get().createQueryExecution(query, ontModel);
        ResultSet results = qe.execSelect();
        ResultSetFormatter.out(System.out, results, query);
        qe.close();
        System.out.println("--------------------------------------------------------");
    }

    private static OntModel loadModelWithImports(String url) {
        Model baseModel = ModelFactory.createDefaultModel();
        baseModel.read(url);
        return JenaUtil.createOntologyModel(OntModelSpec.OWL_MEM,baseModel);
    }

}
