package dd.ontologypart;

import dd.ontologyinterchanger.BareModelInterchanger;
import dd.ontologyinterchanger.QueryExecuter;
import dd.ontologyinterchanger.QueryHolder;

/**
 * Created by Sergey on 12.05.2016.
 */
public class OntologyHandler implements QueryExecuter{

    private BareModelInterchanger bmi;
    private final static String ONTOLOGY_FILE_NAME = "soccer2ed.rdf";


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
}
