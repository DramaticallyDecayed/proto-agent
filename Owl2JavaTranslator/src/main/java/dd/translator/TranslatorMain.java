package dd.translator;


import dd.ontologyinterchanger.SelectQueryHolder;
import dd.translator.owlinterplay.SelectQueryFabric;
import dd.translator.owlinterplay.TranslatorOntologyHandler;
import dd.translator.programgeneration.ClassGenerator;
import dd.translator.programgeneration.InterfaceGeneration;
import dd.translator.programgeneration.ProgramStructureGenerator;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey on 10.05.2016.
 */
public class TranslatorMain {

    public static void main(String... args) throws IOException {
        TranslatorOntologyHandler.INSTANCE.checkOntologyInference();
        ProgramStructureGenerator psg = new ProgramStructureGenerator();

        InterfaceGeneration ig = new InterfaceGeneration(psg);

        SelectQueryHolder sqh = TranslatorOntologyHandler.INSTANCE.executeQuery(
                SelectQueryFabric.collectVirtualEntities());


        try {
            ig.generate(sqh.getDisk("c"));
        } catch (WrappedTranslatorException e) {
            e.printStackTrace();
        }


        sqh = TranslatorOntologyHandler.INSTANCE.executeQuery(
                SelectQueryFabric.collectEffecitiveEntities());
        try {
            ig.generate(sqh.getDisk("c"));
        } catch (WrappedTranslatorException e) {
            e.printStackTrace();
        }

        ClassGenerator cg = new ClassGenerator(psg);


        cg.generate(((List<String>) sqh.getDisk("c"))
                .stream()
                .map(name -> name + "C" )
                .collect(Collectors.toList())
        );

        psg.generate();
    }

}
