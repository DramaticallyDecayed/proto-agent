import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileUtils;
import org.topbraid.spin.system.SPINModuleRegistry;

/**
 * Created by Sergey on 22.11.2015.
 */
public class BareModelLoader implements KBLoader{

    @Override
    public Model loadKBModel() {
        OntModel ontModel = ModelLoadingUtinls.loadModelWithImports(
                "D:\\code\\IdeaProjects\\ProtoAgent\\SoccerAgent\\src\\main\\resources\\KB.ttl",
                FileUtils.langTurtle
        );
        SPINModuleRegistry.get().registerAll(ontModel, null);
        return ontModel;
    }
}
