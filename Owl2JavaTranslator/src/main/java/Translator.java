
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileUtils;
import dd.soccer.perception.perceptingobjects.BodyState;
import org.topbraid.spin.system.SPINModuleRegistry;


/**
 * Created by Sergey on 17.01.2016.
 */
public class Translator {

    public static void main(String... args) {
        SPINModuleRegistry.get().init();
        BareModelInterchanger bmi = new BareModelInterchanger(Translator.class.getClassLoader().getResource("soccer.rdf").toString(), FileUtils.langTurtle);
        bmi.insertIndividual(BodyState.class, "some_body_state");
        bmi.runInference();
        showNodes(bmi.getOntModel());
        System.out.println(bmi.getNewTriples().size());
    }

    private static void showNodes(Model ontModel){
        QuieringUtils.showQueryResults(
                ontModel,
                "SELECT DISTINCT ?donor ?recipient ?level\n" +
                        "WHERE {\n" +
                        "    ?recipient a core:CalculationDependency .\n" +
                        "\t?recipient core:hasBase ?base .\n" +
                        "    ?donor a core:CalculationDependency .\n" +
                        "\t?donor core:hasDerivative ?base .\n" +
                        "\t?recipient core:level ?level .\n" +
                        "} ORDER BY ?level ASC(?recipient)"
        );
    }

}
