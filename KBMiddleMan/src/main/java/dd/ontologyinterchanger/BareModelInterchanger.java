package dd.ontologyinterchanger;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileUtils;
import org.topbraid.spin.system.SPINModuleRegistry;

import java.util.ArrayList;
import java.util.Map;

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

    public BareModelInterchanger(String path, Map<String, String> paths) {
        SPINModuleRegistry.get().init();
        ontModel = (OntModel) new BareModelLoader().loadKBModel(path,
                FileUtils.langTurtle, paths);
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

    public QueryHolder excuteQueryOnMain(QueryHolder sqh) {
        return sqh.executeQuery(ontModel);
    }

    public QueryHolder excuteQueryOnInferenced(QueryHolder sqh) {
        return sqh.executeQuery(newTriples);
    }
}
