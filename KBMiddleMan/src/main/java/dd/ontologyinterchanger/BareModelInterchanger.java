package dd.ontologyinterchanger;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileUtils;
import org.topbraid.spin.system.SPINModuleRegistry;

/**
 * Created by Sergey on 22.11.2015.
 */
public class BareModelInterchanger {

    private OntModel ontModel;
    private Model newTriples;
    private String ns;

    public BareModelInterchanger(String path, String type) {
        SPINModuleRegistry.get().init();
        ontModel = (OntModel) new BareModelLoader().loadKBModel(path, type);
        addModel4Inference();
        ns = getPrefixForXMLNS("");
    }

    public BareModelInterchanger(String path) {
        SPINModuleRegistry.get().init();
        ontModel = (OntModel) new BareModelLoader().loadKBModel(path,
                FileUtils.langTurtle);
        addModel4Inference();
        ns = getPrefixForXMLNS("");
    }

    public String getPrefixForXMLNS(String xmlns) {
        return ontModel.getNsPrefixMap().get(xmlns);
    }

    private void addModel4Inference() {
        newTriples = ModelLoadingUtinls.createModel4InferredValues(ontModel);
        ontModel.addSubModel(newTriples);
    }

    public Individual insertIndividual(Class dmClass, String name) {
        OntClass ontclass = ontModel.getOntClass(ns + dmClass.getSimpleName());
        return ontModel.createIndividual(ns + name, ontclass);
    }

    public Individual insertIndividual(String className, String instanceName) {
        return insertIndividual(ns, className, instanceName);
    }

    public Individual insertIndividual(String importedNS, String className, String instanceName) {
        OntClass ontclass = ontModel.getOntClass(importedNS + className);
        return ontModel.createIndividual(this.ns + instanceName, ontclass);
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
