package dd.translator.programgeneration;

import com.sun.codemodel.ClassType;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JDefinedClass;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.translator.WrappedTranslatorException;
import dd.translator.owlinterplay.OwlInterplayException;
import dd.translator.owlinterplay.OwlTranlationUtils;
import dd.translator.owlinterplay.SelectQueryFabric;
import dd.translator.owlinterplay.TranslatorOntologyHandler;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Sergey on 14.05.2016.
 */
public class InterfaceGeneration {

    private final ProgramStructureGenerator psg;

    public InterfaceGeneration(ProgramStructureGenerator psg) {
        this.psg = psg;
    }

    public void generate(List<String> interfaceNames) {
        interfaceNames.stream()
                .map(name -> ProgramGenerationUtils.composeName(name))
                .map(name -> createInterface(name))
                .map(jdc -> fillWithGetters(jdc))
                .map(jdc -> elaborate(jdc))
                .collect(Collectors.toList());
    }

    private JDefinedClass createInterface(String interfaceName) {
        try {
            return psg.getCm()._class(interfaceName, ClassType.INTERFACE);
        } catch (JClassAlreadyExistsException e) {
            throw new WrappedTranslatorException(InterfaceGeneration.class.getSimpleName(), e);
        }
    }

    private JDefinedClass fillWithGetters(JDefinedClass jdc) {
        SelectQueryHolder sqh = executeQuery(
                SelectQueryFabric.collectClassAttributes(jdc.name())
        );
        return apply(jdc, sqh, x -> addGetter(jdc, x[0], x[1]));
    }

    private JDefinedClass elaborate(JDefinedClass jdc){
        SelectQueryHolder sqh = executeQuery(
                SelectQueryFabric.collectElaborations4Interface(jdc.name())
        );
        return apply(jdc, sqh, x -> elaborateInterface(jdc, x[0]));
    }

    private JDefinedClass apply(JDefinedClass jdc,
                                SelectQueryHolder sqh,
                                Function<Object[], JDefinedClass> function) {
        return sqh.isEmpty() ? jdc : sqh.asStream()
                .map(function)
                .reduce((f1, f2) -> f2)
                .get();
    }


    private JDefinedClass elaborateInterface(JDefinedClass interfaceClass, Object elaborationName){
        JDefinedClass elaborationClass = psg
                .getCm()
                ._getClass(ProgramGenerationUtils.composeName(elaborationName.toString()));
        return interfaceClass._implements(elaborationClass);
    }


    private JDefinedClass addGetter(JDefinedClass getterHolder, Object getterName, Object getterType) {
        try {
            return ProgramGenerationUtils.addGetter(getterHolder,
                    getterName.toString(),
                    OwlTranlationUtils.decodeStringType((String) getterType));
        } catch (OwlInterplayException e) {
            throw new WrappedTranslatorException(InterfaceGeneration.class.getSimpleName(), e);
        }
    }

    private SelectQueryHolder executeQuery(SelectQueryHolder sqh){
        return TranslatorOntologyHandler.INSTANCE.executeQuery(sqh);
    }

}
