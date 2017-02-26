package dd.smda.datastore;

import com.bigdata.rdf.sail.webapp.client.RemoteRepositoryManager;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 21.02.2017.
 */
public class DataStoreAdapter {

    public static final String COUNTER_VAR_NAME = "counter";

    private static final String SERVICE_URL = "http://localhost:9999/blazegraph";
    private final String namespace = "smda";
    private RemoteRepositoryManager repo;

    public void open() {
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

    public List<String>[] select(String select, String... args) throws Exception {
        TupleQueryResult result = repo
                .getRepositoryForNamespace(namespace)
                .prepareTupleQuery(select)
                .evaluate();
        List<String>[] list = new List[args.length];

        for (int i = 0; i < args.length; i++) {
            list[i] = new ArrayList<>();
        }

        while (result.hasNext()) {
            BindingSet bs = result.next();
            for (int i = 0; i < args.length; i++) {
                list[i].add(bs.getValue(args[i]).stringValue());
            }
        }
        return list;
    }
}
