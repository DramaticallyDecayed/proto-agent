package dd.translator.programgeneration;

import com.sun.codemodel.ClassType;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JDefinedClass;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.translator.WrappedTranslatorException;
import dd.translator.owlinterplay.SelectQueryFabric;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey on 14.05.2016.
 */
public class InterfaceGeneration extends ProgramElelementGenerator{

    public InterfaceGeneration(ProgramStructureGenerator psg) {
        super(psg);
    }

    @Override
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
            return getPsg().getCm()._class(interfaceName, ClassType.INTERFACE);
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

    private JDefinedClass elaborate(JDefinedClass jdc) {
        SelectQueryHolder sqh = executeQuery(
                SelectQueryFabric.collectElaborations4Interface(jdc.name())
        );
        return apply(jdc, sqh, x -> elaborateInterface(jdc, x[0]));
    }


    private JDefinedClass elaborateInterface(JDefinedClass interfaceClass, Object elaborationName) {
        JDefinedClass elaborationClass = getPsg()
                .getCm()
                ._getClass(ProgramGenerationUtils.composeName(elaborationName.toString()));
        return interfaceClass._implements(elaborationClass);
    }






}
