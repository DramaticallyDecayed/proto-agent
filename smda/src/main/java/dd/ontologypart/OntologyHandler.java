package dd.ontologypart;

import dd.ontologyinterchanger.BareModelInterchanger;
import dd.ontologyinterchanger.QueryExecuter;
import dd.ontologyinterchanger.QueryHolder;
import org.apache.jena.rdf.model.Model;

/**
 * Created by Sergey on 06.02.2017.
 */
public class OntologyHandler implements QueryExecuter {

    private boolean armed;
    private BareModelInterchanger bmi;
    private final static String ONTOLOGY_FILE_NAME = "parameter-graph.ttl";


    public OntologyHandler(){
        bmi = new BareModelInterchanger(getResourceName());
    }

    private String getResourceName() {
        return getClass().getClassLoader().getResource(ONTOLOGY_FILE_NAME).toString();
    }

    @Override
    public QueryHolder executeQueryOnInference(QueryHolder queryHolder){
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