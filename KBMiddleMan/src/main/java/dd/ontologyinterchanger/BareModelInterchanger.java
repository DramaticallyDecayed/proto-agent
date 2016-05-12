package dd.ontologyinterchanger;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import dd.ontologyinterchanger.BareInferenceRunner;

/**
 * Created by Sergey on 22.11.2015.
 */
public class BareModelInterchanger {

    private OntModel ontModel;
    private Model newTriples;
    private String ns;

    public BareModelInterchanger(String path, String type) {
        ontModel = (OntModel) new BareModelLoader().loadKBModel(path, type);
        addModel4Inference();
        ns = ontModel.getNsPrefixMap().get("");
    }

    private void addModel4Inference() {
        newTriples = ModelLoadingUtinls.createModel4InferredValues(ontModel);
        ontModel.addSubModel(newTriples);
    }

    public void insertIndividual(Class dmClass, String name) {
        OntClass ontclass = ontModel.getOntClass(ns + dmClass.getSimpleName());
        ontModel.createIndividual(ns + name, ontclass);
    }

    public void runInference() {
        BareInferenceRunner.run(ontModel, newTriples);
    }

    public void commitInference() {
        ontModel.add(newTriples);
        newTriples.removeAll();
    }

    public void commitModel(Model model) {
        ontModel.add(model);
    }


    public OntModel getOntModel() {
        return ontModel;
    }

    public Model getNewTriples() {
        return newTriples;
    }
}
