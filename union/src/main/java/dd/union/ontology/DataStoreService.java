package dd.union.ontology;

import dd.omadpter.datastore.DataStoreQuerier;
import dd.omadpter.datastore.DataStoreWriter;

import java.util.HashMap;
import java.util.Map;

public enum DataStoreService {
    INSTANCE;

    private static String NAMESPACE = "arcolim";
    //private static String NAMESPACE = "kb";
    private static String PREFIX = "http://dramaticallydecayed.com/arcolim#";
    private static Map<String, String> prefixes;
    public static DataStoreWriter dataStoreWriter;
    public static DataStoreQuerier dataStoreQuerier;

    static {
        prefixes = new HashMap<>();
        prefixes.put("", PREFIX);
        dataStoreWriter = new DataStoreWriter(
                NAMESPACE,
                prefixes
        );
        dataStoreQuerier = new DataStoreQuerier(
                NAMESPACE,
                prefixes
        );
    }

}
