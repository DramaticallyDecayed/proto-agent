package dd.smda.perception;

import java.io.FileWriter;
import java.io.IOException;
import com.hp.hpl.jena.rdf.model.Model;
import org.apache.jena.riot.RDFLanguages;

/**
 * Created by Sergey on 16.02.2017.
 */
public class ModelWriter {


    public static final String TARGET_FILENAME = "./smda/target/model.ttl";

    public static void writeModel(Model model) throws IOException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(TARGET_FILENAME);
            model.write(fileWriter, RDFLanguages.strLangTurtle);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileWriter.close();
        }
    }
}
