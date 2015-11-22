import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;

/**
 * Created by Sergey on 22.11.2015.
 */
public class BareModelInterchanger {

    private OntModel ontModel;
    private Model newTriples;
    private String ns;

    public BareModelInterchanger(){
        ontModel = (OntModel)  new BareModelLoader().loadKBModel();
        newTriples = ModelLoadingUtinls.createModel4InferredValues(ontModel);
        ns = ontModel.getNsPrefixMap().get("");
    }

    public void insertIndividual(Class dmClass, String name){
        OntClass ontclass = ontModel.getOntClass(ns + dmClass.getSimpleName());
        ontModel.createIndividual(ns+name, ontclass);
    }

    public void runInference(){
        BareInferenceRunner.run(ontModel, newTriples);
    }

    public void commitInference(){
        ontModel.add(newTriples);
        newTriples.removeAll();
    }

    public OntModel getOntModel(){
        return ontModel;
    }

    public Model getNewTriples(){
        return newTriples;
    }
}
