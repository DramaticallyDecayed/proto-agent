package dd.translator.owlinterplay;

import com.hp.hpl.jena.util.FileUtils;
import dd.ontologyinterchanger.BareModelInterchanger;
import org.topbraid.spin.system.SPINModuleRegistry;

/**
 * Created by Sergey on 10.05.2016.
 */
public class OntologyHandler {

    public void init(String ontologyFileName) {
        SPINModuleRegistry.get().init();
        BareModelInterchanger bmi = new BareModelInterchanger(
                getClass().getClassLoader().getResource(ontologyFileName).toString(),
                FileUtils.langTurtle);
        System.out.println("START");
        bmi.runInference();
        bmi.commitInference();

    }


}
