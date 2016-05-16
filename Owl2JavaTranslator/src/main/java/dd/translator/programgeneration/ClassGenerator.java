package dd.translator.programgeneration;

import com.sun.codemodel.ClassType;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JDefinedClass;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.translator.WrappedTranslatorException;
import dd.translator.owlinterplay.SelectQueryFabric;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Sergey on 13.05.2016.
 */
public class ClassGenerator extends ProgramElelementGenerator {

    private ProgramStructureGenerator generator;

    public ClassGenerator(ProgramStructureGenerator psg) {
        super(psg);
    }

    @Override
    public void generate(List<String> classNames) {
        classNames.stream()
                .map(name -> ProgramGenerationUtils.composeName(name))
                .map(name -> ProgramGenerationUtils.interfaceName2ClassName(name))
                .map(name -> createClass(name))
                .map(jdc -> implementInterface(jdc))
                .map(jdc -> collectAllInterfaces(jdc))
                .map(entry -> collectParentClasses(entry))
                .map(entry -> collectAllElaborations(entry))
                .map(entry -> fillWithFieldsAndSetGet(entry))
                .collect(Collectors.toList());

    }

    private Map.Entry<JDefinedClass, List<String>> collectAllElaborations
            (Map.Entry<JDefinedClass, List<String>> entry) {
        String parentInterface = getParentInterface(entry.getKey()).name();
        SelectQueryHolder sqh = executeQuery(
                SelectQueryFabric.collectElaborations4Interface(parentInterface)
        );
        sqh.getDisk("c")
                .stream()
                .map(name -> entry.getKey()._implements(getPsg().getCm()._getClass((String)name)));
        entry.getValue().addAll(sqh.getDisk("c"));
        return new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue());
    }

    private JDefinedClass fillWithFieldsAndSetGet(Map.Entry<JDefinedClass, List<String>> entry) {
        System.out.println(entry.getKey().name() + " " + entry.getValue());
        JDefinedClass jdc = entry.getKey();
        for (String implInterface : entry.getValue()) {
            SelectQueryHolder sqh = executeQuery(
                    SelectQueryFabric.collectClassAttributes(implInterface)
            );
            apply(jdc, sqh, x -> addGetter(jdc, x[0], x[1]));
        }
        return entry.getKey();
    }

    private Map.Entry<JDefinedClass, List<String>> collectAllInterfaces(JDefinedClass jdc) {
        JDefinedClass parentInterface = getParentInterface(jdc);
        List<String> interfaces = collectAllInterfaces(parentInterface, new ArrayList<>());
        return new AbstractMap.SimpleEntry<>(jdc, interfaces);
    }

    private Map.Entry<JDefinedClass, List<String>> collectParentClasses(
            Map.Entry<JDefinedClass, List<String>> entry) {
        String parentInterface = getParentInterface(entry.getKey()).name();
        SelectQueryHolder sqh = executeQuery(
                SelectQueryFabric.collectAllParents(parentInterface)
        );
        sqh.getDisk("pc")
                .stream()
                .map(name -> entry.getKey()._implements(getPsg().getCm()._getClass((String)name)));
        entry.getValue().addAll(sqh.getDisk("pc"));
        return new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue());
    }

    private JDefinedClass implementInterface(JDefinedClass jdc) {
        return jdc._implements(getParentInterface(jdc));
    }

    private JDefinedClass getParentInterface(JDefinedClass jdc) {
        String interfaceName = ProgramGenerationUtils.className2InterfaceName(jdc.fullName());
        return getPsg().getCm()._getClass(interfaceName);
    }

    private List<String> collectAllInterfaces(JClass parentInterface, List<String> interfaceAccum) {
        interfaceAccum.add(parentInterface.name());
        Iterator<JClass> it = parentInterface._implements();
        for (; it.hasNext(); ) {
            collectAllInterfaces(it.next(), interfaceAccum);
        }
        return interfaceAccum;
    }


    private JDefinedClass createClass(String className) {
        try {
            return getPsg().getCm()._class(className, ClassType.CLASS);
        } catch (JClassAlreadyExistsException e) {
            throw new WrappedTranslatorException(ClassGenerator.class.getSimpleName(), e);
        }
    }
}
