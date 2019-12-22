package dd.ontologyinterchanger;


import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.topbraid.spin.util.JenaUtil;

/**
 * Created by Sergey on 22.11.2015.
 */
public class ModelLoadingUtinls {

    private static Logger logger = LoggerFactory.getLogger(ModelLoadingUtinls .class);

    public static OntModel loadModelWithImports(String url, String language) {
        logger.info("Loading ontology:\n\t " + url);
        Model baseModel = ModelFactory.createDefaultModel();
        baseModel.read(url, language);
        return JenaUtil.createOntologyModel(OntModelSpec.OWL_MEM, baseModel);
    }

    public static Model createModel4InferredValues(OntModel ontModel){
        Model newTriples = ModelFactory.createDefaultModel();
        ontModel.addSubModel(newTriples);
        newTriples.setNsPrefixes(ontModel.getNsPrefixMap());
        return newTriples;
    }
}
