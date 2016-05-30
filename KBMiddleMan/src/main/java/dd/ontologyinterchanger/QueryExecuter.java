package dd.ontologyinterchanger;

/**
 * Created by Sergey on 29.05.2016.
 */
public interface QueryExecuter {
    QueryHolder executeQuery(QueryHolder queryHolder);
    QueryHolder executeQueryOnInference(QueryHolder queryHolder);
    void runInference();
    void commitResults();
}
