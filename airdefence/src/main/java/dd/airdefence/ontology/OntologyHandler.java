package dd.airdefence.ontology;

import dd.ontologyinterchanger.BareModelInterchanger;
import dd.ontologyinterchanger.QueryExecuter;
import dd.ontologyinterchanger.QueryHolder;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import java.io.*;

public class OntologyHandler implements QueryExecuter {

    private String importOntologyNS;

    private boolean armed;
    private BareModelInterchanger bmi;
    private final static String ONTOLOGY_FILE_NAME = "airdefence.ttl";
    private final static String CORE_ONTOLOGY_NAME = "core2ed";

    public OntologyHandler(){
        bmi = new BareModelInterchanger(getResourceName());
        importOntologyNS = bmi.getPrefixForXMLNS(CORE_ONTOLOGY_NAME);
    }

    private String getResourceName() {
        return getClass().getClassLoader().getResource(ONTOLOGY_FILE_NAME).toString();
    }

    @Override
    public QueryHolder executeQueryOnInference(QueryHolder queryHolder){


        try {
            RDFDataMgr.write(new FileOutputStream(new File("out.ttl")), bmi.getOntModel(), Lang.TURTLE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
