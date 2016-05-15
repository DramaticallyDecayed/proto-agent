package dd.translator.programgeneration;

import com.sun.codemodel.JDefinedClass;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.translator.owlinterplay.TranslatorOntologyHandler;

import java.util.List;
import java.util.function.Function;

/**
 * Created by Sergey on 15.05.2016.
 */
public abstract class ProgramElelementGenerator {

    private final ProgramStructureGenerator psg;

    public abstract void generate(List<String> elementNames);

    public ProgramElelementGenerator(ProgramStructureGenerator psg) {
        this.psg = psg;
    }

    public ProgramStructureGenerator getPsg() {
        return psg;
    }

    public SelectQueryHolder executeQuery(SelectQueryHolder sqh) {
        return TranslatorOntologyHandler.INSTANCE.executeQuery(sqh);
    }

    public JDefinedClass apply(JDefinedClass jdc,
                                SelectQueryHolder sqh,
                                Function<Object[], JDefinedClass> function) {
        return sqh.isEmpty() ? jdc : sqh.asStream()
                .map(function)
                .reduce((f1, f2) -> f2)
                .get();
    }
}
