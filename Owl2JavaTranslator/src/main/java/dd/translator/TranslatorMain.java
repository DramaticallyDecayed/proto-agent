package dd.translator;


import dd.ontologyinterchanger.SelectQueryHolder;
import dd.translator.owlinterplay.SelectQueryFabric;
import dd.translator.owlinterplay.TranslatorOntologyHandler;
import dd.translator.programgeneration.*;
import dd.translator.programgeneration.nodecreators.AssociativePlainNodeExtender;
import dd.translator.programgeneration.nodecreators.AssociativeRefiningNodeExtender;
import dd.translator.programgeneration.nodecreators.GenerativeInitialExtender;

import java.io.IOException;

/**
 * Created by Sergey on 10.05.2016.
 */
public class TranslatorMain {

    public static void main(String... args) throws IOException {
        TranslatorOntologyHandler.INSTANCE.checkOntologyInference();
        ProgramStructureGenerator psg = new ProgramStructureGenerator();

        WorldEntityInterfaceGeneration weInterfaceGenerator = new WorldEntityInterfaceGeneration(psg);

        generateInterfaces4VirtualEntities(weInterfaceGenerator);

        generateClasses4ActualEntities(psg, weInterfaceGenerator);


        SelectQueryHolder sqh = TranslatorOntologyHandler.INSTANCE.executeQuery(
                SelectQueryFabric.collectObjectProperties());

        new ObjectPropertyGenerator(psg).generate(sqh.getDisk("r"));


        sqh = TranslatorOntologyHandler.INSTANCE.executeQuery(
                SelectQueryFabric.collectNodes());

        new NodeGenerator(psg).generate(sqh.getDisk("nd"));


        sqh = TranslatorOntologyHandler.INSTANCE.executeQuery(
                SelectQueryFabric.collectGenerativeInitialNodes());
        new GenerativeInitialExtender(psg).generate(sqh.getDisk("nd"));

        sqh = TranslatorOntologyHandler.INSTANCE.executeQuery(
                SelectQueryFabric.collectAssociativePlainNodes());
        new AssociativePlainNodeExtender(psg).generate(sqh.getDisk("nd"));

        sqh = TranslatorOntologyHandler.INSTANCE.executeQuery(
                SelectQueryFabric.collectAssociativeRefiningNodes());
        new AssociativeRefiningNodeExtender(psg).generate(sqh.getDisk("nd"));

        psg.generate();


    }

    private static void generateClasses4ActualEntities(
            ProgramStructureGenerator psg,
            WorldEntityInterfaceGeneration weInterfaceGenerator) {
        SelectQueryHolder sqh;
        sqh = TranslatorOntologyHandler.INSTANCE.executeQuery(
                SelectQueryFabric.collectEffectiveEntities());
        try {
            weInterfaceGenerator.generate(sqh.getDisk("c"));
        } catch (WrappedTranslatorException e) {
            e.printStackTrace();
        }
        WorldEntityClassGenerator weClassGenerator = new WorldEntityClassGenerator(psg);
        weClassGenerator.generate(sqh.getDisk("c"));
    }

    private static void generateInterfaces4VirtualEntities(WorldEntityInterfaceGeneration weInterfaceGenarator) {
        SelectQueryHolder sqh = TranslatorOntologyHandler.INSTANCE.executeQuery(
                SelectQueryFabric.collectVirtualEntities());
        try {
            weInterfaceGenarator.generate(sqh.getDisk("c"));
        } catch (WrappedTranslatorException e) {
            e.printStackTrace();
        }
    }


}
