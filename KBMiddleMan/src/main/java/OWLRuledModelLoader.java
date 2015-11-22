import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.graph.compose.MultiUnion;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileUtils;
import org.topbraid.spin.inference.DefaultSPINRuleComparator;
import org.topbraid.spin.system.SPINModuleRegistry;
import org.topbraid.spin.util.JenaUtil;
import org.topbraid.spin.util.SPINQueryFinder;
import org.topbraid.spin.vocabulary.SPIN;

/**
 * Created by Sergey on 22.11.2015.
 */
public class OWLRuledModelLoader implements KBLoader{

    @Override
    public Model loadKBModel(){
//        OntModel ontModel = (OntModel)(new BareModelLoader()).loadKBModel();
//        OntModel owlrlModel = ModelLoadingUtinls.loadModelWithImports(
//                "http://topbraid.org/spin/owlrl-all",
//                FileUtils.langN3);
//        SPINModuleRegistry.get().registerAll(owlrlModel, null);
//        MultiUnion multiUnion = JenaUtil.createMultiUnion(new Graph[]{
//                ontModel.getGraph(),
//                owlrlModel.getGraph()
//        });
//        Model unionModel = ModelFactory.createModelForGraph(multiUnion);
//        cls2Query = SPINQueryFinder.getClass2QueryMap(unionModel, ontModel, SPIN.rule, true, false);
//        cls2Constructor = SPINQueryFinder.getClass2QueryMap(ontModel, ontModel, SPIN.constructor, true, false);
//        comparator = new DefaultSPINRuleComparator(ontModel);
        return null;
    }


}
