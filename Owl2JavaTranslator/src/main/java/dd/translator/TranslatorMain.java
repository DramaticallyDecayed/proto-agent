package dd.translator;


import dd.ontologyinterchanger.SelectQueryHolder;
import dd.translator.owlinterplay.SelectQueryFabric;
import dd.translator.owlinterplay.TranslatorOntologyHandler;
import dd.translator.programgeneration.InterfaceGeneration;
import dd.translator.programgeneration.ProgramStructureGenerator;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Sergey on 10.05.2016.
 */
public class TranslatorMain {

    public static void main(String... args) throws IOException {
        TranslatorOntologyHandler.INSTANCE.checkOntologyInference();
        ProgramStructureGenerator psg = new ProgramStructureGenerator();

        InterfaceGeneration ig = new InterfaceGeneration(psg);

        SelectQueryHolder sqh = TranslatorOntologyHandler.INSTANCE.executeQuery(
                SelectQueryFabric.generateQueryForInterfaces());
        for (Map<String, Object> slice : sqh) {
            System.out.println(slice.get("c"));
            SelectQueryHolder attr = TranslatorOntologyHandler.INSTANCE.executeQuery(
                    SelectQueryFabric.collectClassAttributes((String) slice.get("c")));
            for (Map<String, Object> attrSlice : attr) {
                System.out.println(attrSlice);
            }
        }

        try {
            ig.generate(sqh.getDisk("c"));
        } catch (WrappedTranslatorException e) {
            e.printStackTrace();
        }

        psg.generate();
    }
}
