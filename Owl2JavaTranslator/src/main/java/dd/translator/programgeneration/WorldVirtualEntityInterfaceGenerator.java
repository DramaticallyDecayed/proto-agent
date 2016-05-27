package dd.translator.programgeneration;

import dd.ontologyinterchanger.SelectQueryHolder;
import dd.translator.owlinterplay.SelectQueryFabric;
import dd.translator.owlinterplay.TranslatorOntologyHandler;

import java.util.List;

/**
 * Created by Sergey on 27.05.2016.
 */
public class WorldVirtualEntityInterfaceGenerator extends WorldEntityInterfaceGeneration {
    public WorldVirtualEntityInterfaceGenerator(ProgramStructureGenerator psg) {
        super(psg);
    }

    @Override
    public List<String> receiveData() {
        SelectQueryHolder sqh = TranslatorOntologyHandler.INSTANCE.executeQuery(
                SelectQueryFabric.collectVirtualEntities());
        return sqh.getDisk("c");
    }
}
