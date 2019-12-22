package dd.ontologyinterchanger;


import org.apache.jena.rdf.model.Model;

/**
 * Created by Sergey on 22.11.2015.
 */
public interface KBLoader {
    Model loadKBModel(String path, String type);
}
