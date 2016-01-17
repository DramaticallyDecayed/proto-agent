import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import org.topbraid.spin.system.SPINModuleRegistry;

/**
 * Created by Sergey on 22.11.2015.
 */
public class BareModelLoader implements KBLoader{

    @Override
    public Model loadKBModel(String path, String type) {
        OntModel ontModel = ModelLoadingUtinls.loadModelWithImports(
                path,
                type
        );
        SPINModuleRegistry.get().registerAll(ontModel, null);
        return ontModel;
    }
}
