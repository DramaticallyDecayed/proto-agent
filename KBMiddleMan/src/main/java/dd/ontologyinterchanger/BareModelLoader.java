package dd.ontologyinterchanger;


import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.topbraid.spin.system.SPINModuleRegistry;

import java.util.Map;

/**
 * Created by Sergey on 22.11.2015.
 */
public class BareModelLoader implements KBLoader {

    @Override
    public Model loadKBModel(String path, String type) {
        OntModel ontModel = ModelLoadingUtinls.loadModelWithImports(
                path,
                type
        );
        SPINModuleRegistry.get().registerAll(ontModel, null);
        return ontModel;
    }


    public Model loadKBModel(String path, String type, Map<String, String> paths) {
        OntModel ontModel = ModelLoadingUtinls.loadModelWithImports(
                path,
                type
        );
        Model model = ModelFactory.createDefaultModel();
        paths
                .entrySet()
                .stream()
                .forEach(
                        x -> {
                            Model m = load(x.getKey(), x.getValue());
                            ontModel.addSubModel(m, false);
                        }
                );
        SPINModuleRegistry.get().registerAll(ontModel, null);
        return ontModel;
    }


    private Model load(String name, String lang) {
        return ModelFactory
                .createDefaultModel()
                .read(
                        BareModelLoader.class.getClassLoader().getResourceAsStream(name),
                        null,
                        lang
                );
    }
}
