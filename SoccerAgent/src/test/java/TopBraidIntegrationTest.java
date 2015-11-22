import ch.qos.logback.classic.Level;
import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.graph.compose.MultiUnion;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.rdf.model.impl.StmtIteratorImpl;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.util.iterator.Filter;
import dd.soccer.perception.perceptingobjects.BodyState;
import dd.soccer.sas.presentation.soccerrelations.SeeNavigation;
import org.junit.Before;
import org.junit.Test;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.topbraid.spin.inference.DefaultSPINRuleComparator;
import org.topbraid.spin.inference.SPINInferences;
import org.topbraid.spin.inference.SPINRuleComparator;
import org.topbraid.spin.system.SPINModuleRegistry;
import org.topbraid.spin.util.CommandWrapper;
import org.topbraid.spin.util.JenaUtil;
import org.topbraid.spin.util.SPINQueryFinder;
import org.topbraid.spin.vocabulary.SPIN;

import java.util.List;
import java.util.Map;

;

/**
 * Created by Sergey on 04.11.2015.
 */
public class TopBraidIntegrationTest {


    private BareModelInterchanger interchanger;
    private OntModel ontModel;
    private Model newTriples;


    @Before
    public void doBefore(){
        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);
        SPINModuleRegistry.get().init();
        loadMainModel();
        createModelForNewTriples();
    }

    private void loadMainModel(){
        interchanger = new BareModelInterchanger();
        ontModel = interchanger.getOntModel();
    }

    private void createModelForNewTriples(){
        newTriples = ModelFactory.createDefaultModel();
        ontModel.addSubModel(newTriples);
        newTriples.setNsPrefixes(ontModel.getNsPrefixMap());
    }

    private void insertTestEntity(){
        interchanger.insertIndividual(BodyState.class, "some_body_state");
    }

    @Test
    public void testWOOWLRL(){

        long start = System.currentTimeMillis();

        insertTestEntity();
        interchanger.runInference();
        showNodes(interchanger.getNewTriples());
        interchanger.commitInference();


        interchanger.insertIndividual(SeeNavigation.class, "some_navi");
        interchanger.runInference();
        showNodes(interchanger.getNewTriples());
        interchanger.commitInference();


        long end = System.currentTimeMillis();
        System.out.println("Inferred triples: " + newTriples.size() + " during " + (end-start));
        showNodes(ontModel);
        findSpecialSee(ontModel);
        QuieringUtils.showModelStatements(newTriples);
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
        showNodes(newTriples);
        SPINInferences.run(ontModel, newTriples, null, null, false, null);
        System.out.println("Inferred triples: " + newTriples.size());
        showNodes(newTriples);
        ontModel.add(newTriples);
        newTriples.removeAll();
        SPINInferences.run(ontModel, newTriples, null, null, false, null);
        System.out.println("Inferred triples: " + newTriples.size());
        showNodes(newTriples);
    }

    @Test
    public void testOnSeeRelationInferenceWithJenaReasoner(){
        findSpecialSee(useJenaReasoner(ontModel));
    }

    @Test
    public void testOnTeamWork(){
        insertTestEntity();
        long start = System.currentTimeMillis();
        System.out.println("size before OWL RL " + ontModel.size());
        //findSpecialSee(useJenaReasoner(ontModel));
        findSpecialSee(anotherResoner(ontModel));
        //findSpecialSee(pelletReasoner(ontModel));
        System.out.println("size after OWL RL " + ontModel.size());
        SPINInferences.run(ontModel, newTriples, null, null, false, null);
        long end = System.currentTimeMillis();
        System.out.println("Inferred triples: " + newTriples.size() + " during " + (end-start));
        showNodes(ontModel);
        findSpecialSee(ontModel);
    }

    private Model pelletReasoner(OntModel ontModel){
        InfModel pModel = ModelFactory.createInfModel(PelletReasonerFactory.theInstance().create(), ontModel);
        ExtendedIterator<Statement> stmts = pModel.listStatements().filterDrop( new Filter<Statement>() {
            @Override
            public boolean accept(Statement o) {
                return ontModel.contains( o );
            }
        });
        Model deductions = ModelFactory.createDefaultModel().add( new StmtIteratorImpl( stmts ));
        return deductions;
    }

    private Model useJenaReasoner(OntModel ontModel){
        Reasoner reasoner = ReasonerRegistry.getOWLMicroReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_LITE_MEM;
        ontModelSpec.setReasoner(reasoner);
        long start = System.currentTimeMillis();
        InfModel model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
        long end = System.currentTimeMillis();
        System.out.println((end-start));
        return model;
    }

    private Model anotherResoner(OntModel ontModel){
        Reasoner reasoner = ReasonerRegistry.getOWLMicroReasoner();
        long start = System.currentTimeMillis();
        InfModel pModel = ModelFactory.createInfModel(reasoner, ontModel);
        long end = System.currentTimeMillis();
//        ExtendedIterator<Statement> stmts = pModel.listStatements().filterDrop( new Filter<Statement>() {
//            @Override
//            public boolean accept(Statement o) {
//                return ontModel.contains( o );
//            }
//        });
//        Model deductions = ModelFactory.createDefaultModel().add( new StmtIteratorImpl( stmts ));
        System.out.println((end-start));
//        System.out.println(deductions.size());
//        return deductions;
        return pModel;

    }

    private void findSpecialSee(Model model){
        QuieringUtils.showQueryResults(
                model,
                "PREFIX : <http://dd.com/SASKBTest#> " +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                "SELECT ?i ?sc \n" +
                "WHERE { \n" +
                "\t?sc (rdfs:subClassOf)* :ModelRelation .\n" +
                "\t?i a ?sc .\n" +
                "}"
        );
    }

    private void showNodes(Model ontModel){
        QuieringUtils.showQueryResults(
                ontModel,
                "SELECT\n" +
                "?node\n" +
                "WHERE {\n" +
                "\t?node :isNodeActive true . \n" +
                "}"
        );
    }

    private static OntModel loadModelWithImports(String url) {
        Model baseModel = ModelFactory.createDefaultModel();
        baseModel.read(url);
        return JenaUtil.createOntologyModel(OntModelSpec.OWL_MEM,baseModel);
    }

}
