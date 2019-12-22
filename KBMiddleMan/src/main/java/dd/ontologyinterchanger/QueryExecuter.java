package dd.ontologyinterchanger;


import org.apache.jena.rdf.model.Model;

/**
 * Created by Sergey on 29.05.2016.
 */
public interface QueryExecuter {
    QueryHolder executeQuery(QueryHolder queryHolder);
    QueryHolder executeQueryOnInference(QueryHolder queryHolder);
    void runInference();
    void commitResults();
    void commitResults(Model m);
    void arm();
    boolean isArmed();
    void disarm();
}
