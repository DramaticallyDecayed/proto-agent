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
public class WorldEntityInterfaceGeneration extends ProgramElementGenerator {

    public WorldEntityInterfaceGeneration(ProgramStructureGenerator psg) {
        super(psg);
    }

    @Override
    public void generate(List<String> interfaceNames) {
        interfaceNames.stream()
                .map(name -> ProgramGenerationUtils.composeName(name))
                .map(name -> createInterface(name))
                .map(jdc -> fillWithGetters(jdc))
                .map(jdc -> elaborate(jdc))
                .map(jdc -> subclass(jdc))
                .map(jdc -> link2SAS(jdc))
                .collect(Collectors.toList());
    }

    private JDefinedClass subclass(JDefinedClass jdc) {
        SelectQueryHolder sqh = executeQuery(
                SelectQueryFabric.collectAllParents(jdc.name())
        );
        return apply(
                jdc,
                sqh,
                x -> jdc._implements(
                        getPsg().getCm()._getClass(ProgramGenerationUtils.composeName((String)x[0]))
                )
        );
    }

    private JDefinedClass createInterface(String interfaceName) {
        try {
            return getPsg().getCm()._class(interfaceName, ClassType.INTERFACE);
        } catch (JClassAlreadyExistsException e) {
            throw new WrappedTranslatorException(WorldEntityInterfaceGeneration.class.getSimpleName(), e);
        }
    }

    private JDefinedClass fillWithGetters(JDefinedClass jdc) {
        SelectQueryHolder sqh = executeQuery(
                SelectQueryFabric.collectClassAttributes(jdc.name())
        );
        return apply(jdc, sqh, x -> addSetterAndGetter4Interface(jdc, x[0], x[1]));
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
