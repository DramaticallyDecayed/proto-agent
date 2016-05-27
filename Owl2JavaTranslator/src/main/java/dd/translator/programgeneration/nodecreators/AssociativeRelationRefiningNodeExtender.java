package dd.translator.programgeneration.nodecreators;

import dd.ontologyinterchanger.SelectQueryHolder;
import dd.translator.owlinterplay.SelectQueryFabric;
import dd.translator.owlinterplay.TranslatorOntologyHandler;
import dd.translator.programgeneration.ProgramStructureGenerator;

import java.util.List;

/**
 * Created by Sergey on 22.05.2016.
 */
public class AssociativeRelationRefiningNodeExtender extends AssociativeRefiningNodeExtender {
    public AssociativeRelationRefiningNodeExtender(ProgramStructureGenerator psg) {
        super(psg);
    }

    @Override
    public List<String> receiveData() {
        SelectQueryHolder sqh = TranslatorOntologyHandler.INSTANCE.executeQuery(
                SelectQueryFabric.collectAssociativeRelationRefiningNodes());
        return sqh.getDisk("nd");
    }

    @Override
    public void generate() {
        super.generate();
    }
}
