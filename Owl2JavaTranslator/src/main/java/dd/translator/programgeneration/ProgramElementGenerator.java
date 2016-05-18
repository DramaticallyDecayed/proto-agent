package dd.translator.programgeneration;

import com.sun.codemodel.JDefinedClass;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.sas.presentation.WorldEntity;
import dd.translator.WrappedTranslatorException;
import dd.translator.owlinterplay.OwlInterplayException;
import dd.translator.owlinterplay.OwlTranlationUtils;
import dd.translator.owlinterplay.TranslatorOntologyHandler;

import java.util.List;
import java.util.function.Function;

/**
 * Created by Sergey on 15.05.2016.
 */
public abstract class ProgramElementGenerator {

    private final ProgramStructureGenerator psg;

    public abstract void generate(List<String> elementNames);

    public ProgramElementGenerator(ProgramStructureGenerator psg) {
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

    public JDefinedClass addSetterAndGetter4Interface(JDefinedClass getterHolder, Object getterName, Object getterType) {
        try {
            return ProgramGenerationUtils.addSettersAndGetters4Interface(getterHolder,
                    getterName.toString(),
                    OwlTranlationUtils.decodeStringType((String) getterType));
        } catch (OwlInterplayException e) {
            throw new WrappedTranslatorException(WorldEntityInterfaceGeneration.class.getSimpleName(), e);
        }
    }

    public JDefinedClass addSetterAndGetter4Class(JDefinedClass setterHolder, Object setterName, Object setterArgType) {
        try {
            return ProgramGenerationUtils.addSettersAndGetters4Class(setterHolder,
                    setterName.toString(),
                    OwlTranlationUtils.decodeStringType((String) setterArgType));
        } catch (OwlInterplayException e) {
            throw new WrappedTranslatorException(WorldEntityInterfaceGeneration.class.getSimpleName(), e);
        }
    }

    public JDefinedClass link2SAS(JDefinedClass jdc) {
        return jdc._implements(WorldEntity.class);
    }


}
