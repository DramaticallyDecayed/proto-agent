package dd.ontologyinterchanger;

import org.apache.jena.graph.Graph;
import org.apache.jena.graph.compose.MultiUnion;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileUtils;
import org.topbraid.spin.inference.DefaultSPINRuleComparator;
import org.topbraid.spin.system.SPINModuleRegistry;
import org.topbraid.spin.util.JenaUtil;
import org.topbraid.spin.util.SPINQueryFinder;
import org.topbraid.spin.vocabulary.SPIN;

/**
 * Created by Sergey on 22.11.2015.
 */
public class OWLRuledModelLoader implements KBLoader {

    @Override
    public Model loadKBModel(String path, String type){
        OntModel ontModel = (OntModel)(new dd.ontologyinterchanger.BareModelLoader()).loadKBModel(path, type);
        OntModel owlrlModel = dd.ontologyinterchanger.ModelLoadingUtinls.loadModelWithImports(
                "http://topbraid.org/spin/owlrl-all",
                FileUtils.langN3);
        SPINModuleRegistry.get().registerAll(owlrlModel, null);
        MultiUnion multiUnion = JenaUtil.createMultiUnion(new Graph[]{
                ontModel.getGraph(),
                owlrlModel.getGraph()
        });
        Model unionModel = ModelFactory.createModelForGraph(multiUnion);
//        cls2Query = SPINQueryFinder.getClass2QueryMap(unionModel, ontModel, SPIN.rule, true, false);
//        cls2Constructor = SPINQueryFinder.getClass2QueryMap(ontModel, ontModel, SPIN.constructor, true, false);
//        comparator = new DefaultSPINRuleComparator(ontModel);
        return null;
    }


}
