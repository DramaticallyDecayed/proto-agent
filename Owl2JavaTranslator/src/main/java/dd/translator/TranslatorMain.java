package dd.translator;


import dd.translator.owlinterplay.TranslatorOntologyHandler;
import dd.translator.programgeneration.*;
import dd.translator.programgeneration.nodecreators.*;

import java.io.IOException;

/**
 * Created by Sergey on 10.05.2016.
 */
public class TranslatorMain {

    public static void main(String... args) throws IOException {
        TranslatorOntologyHandler.INSTANCE.checkOntologyInference();
        ProgramStructureGenerator psg = new ProgramStructureGenerator();

        new WorldVirtualEntityInterfaceGenerator(psg).generate();
        new WorldActualEntityInterfaceGenerator(psg).generate();
        new WorldEntityClassGenerator(psg).generate();
        new ObjectPropertyGenerator(psg).generate();
        new NodeGenerator(psg).generate();
        new GenerativeInitialExtender(psg).generate();
        new AssociativePlainNodeExtender(psg).generate();
        new AssociativeRefiningNodeExtender(psg).generate();
        new AssociativeRelationRefiningNodeExtender(psg).generate();
        new GenerativeCompositeNode(psg).generate();
        new GenerativeComplexNodeExtender(psg).generate();

        psg.generate();


    }


}
