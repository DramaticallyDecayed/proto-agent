package dd.translator.programgeneration;

import com.sun.codemodel.JCodeModel;

import java.io.File;
import java.io.IOException;

/**
 * Created by Sergey on 13.05.2016.
 */
public class ProgramStructureGenerator {

    //public final static String PACKAGE_PREFIX = "dd.smda.sas";
    //public final static String PACKAGE_PREFIX = "dd.tcc.sas";
    //public final static String PACKAGE_PREFIX = "dd.union.sas";
    public final static String PACKAGE_PREFIX = "dd.airdefence.sas";

    private final static String OUTPUT_FOLDER_PATH = ".\\Owl2JavaTranslator\\target\\generated-classes";

    private JCodeModel cm = new JCodeModel();

    public void generate() throws IOException {
        File outputFolder = new File(OUTPUT_FOLDER_PATH);
        outputFolder.mkdirs();
        cm.build(outputFolder);
    }

    public JCodeModel getCm() {
        return cm;
    }
}
