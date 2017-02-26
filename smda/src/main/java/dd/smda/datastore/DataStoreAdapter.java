package dd.smda.datastore;

import com.bigdata.rdf.sail.webapp.client.RemoteRepositoryManager;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.model.Value;
/**
 * Created by Sergey on 21.02.2017.
 */
public class DataStoreAdapter {

    public static final String COUNTER_VAR_NAME = "counter";

    private static final String SERVICE_URL = "http://localhost:9999/blazegraph";
    private final String namespace = "smda";
    private RemoteRepositoryManager repo;

    public void open(){
        repo = new RemoteRepositoryManager(SERVICE_URL, false);
    }

    public void update(String update) throws Exception {
        repo.getRepositoryForNamespace(namespace)
                .prepareUpdate(update).evaluate();
    }

    public int count(String select) throws Exception {
        TupleQueryResult result = repo
                .getRepositoryForNamespace(namespace)
                .prepareTupleQuery(select)
                .evaluate();
        return Integer.valueOf(result.next().getBinding(COUNTER_VAR_NAME).getValue().stringValue());
    }

    public boolean ask(String ask) throws Exception {
        return repo.getRepositoryForNamespace(namespace).prepareBooleanQuery(ask).evaluate();
    }

    public void close() throws Exception {
        repo.close();
    }
}
