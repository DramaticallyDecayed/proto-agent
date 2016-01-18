package dd.ontologyinterchanger;

import com.hp.hpl.jena.rdf.model.Model;
import org.topbraid.spin.inference.SPINInferences;

/**
 * Created by Sergey on 22.11.2015.
 */
public class BareInferenceRunner {

    public static Model run(Model ontModel, Model newTriples){
        SPINInferences.run(ontModel, newTriples, null, null, false, null);
        return newTriples;
    }
}
