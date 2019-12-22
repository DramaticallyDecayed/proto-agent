package dd.airdefence.ontology;

import dd.ontologyinterchanger.BareModelInterchanger;
import dd.ontologyinterchanger.QueryExecuter;
import dd.ontologyinterchanger.QueryHolder;
import org.apache.jena.rdf.model.Model;

import java.util.HashMap;
import java.util.Map;

public class OntologyHandler implements QueryExecuter {

    private String importOntologyNS;

    private boolean armed;
    private BareModelInterchanger bmi;
    private final static String ONTOLOGY_FILE_NAME = "airdefence.ttl";
    private final static String CORE_ONTOLOGY_NAME = "core2ed";

    public OntologyHandler() {
        Map paths = new HashMap<String, String>();
        paths.put("sp.ttl", "TURTLE");
        paths.put("spin.ttl", "TURTLE");
        paths.put("spl.spin.ttl", "TURTLE");
        paths.put(CORE_ONTOLOGY_NAME + ".rdf", "RDF/XML");

        bmi = new BareModelInterchanger(getResourceName(), paths);
        importOntologyNS = bmi.getPrefixForXMLNS(CORE_ONTOLOGY_NAME);
    }

    private String getResourceName() {
        return getClass().getClassLoader().getResource(ONTOLOGY_FILE_NAME).toString();
    }

    @Override
    public QueryHolder executeQueryOnInference(QueryHolder queryHolder) {
        return bmi.excuteQueryOnInferenced(queryHolder);
    }

    @Override
    public QueryHolder executeQuery(QueryHolder queryHolder) {
        return bmi.excuteQueryOnMain(queryHolder);
    }

    @Override
    public void runInference() {
        bmi.runInference();
    }

    @Override
    public void commitResults() {
        bmi.commitInference();
    }

    @Override
    public void commitResults(Model m) {
        bmi.commitModel(m);
    }

    @Override
    public void arm() {
        armed = true;
    }

    @Override
    public boolean isArmed() {
        return armed;
    }

    @Override
    public void disarm() {
        armed = false;
    }
}
