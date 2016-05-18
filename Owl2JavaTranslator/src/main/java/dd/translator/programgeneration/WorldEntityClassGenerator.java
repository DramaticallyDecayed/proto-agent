package dd.translator.programgeneration;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JDefinedClass;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.translator.owlinterplay.SelectQueryFabric;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Sergey on 13.05.2016.
 */
public class WorldEntityClassGenerator extends ProgramElementGenerator {

    private ProgramStructureGenerator generator;

    public WorldEntityClassGenerator(ProgramStructureGenerator psg) {
        super(psg);
    }

    @Override
    public void generate(List<String> classNames) {
        classNames.stream()
                .map(name -> ProgramGenerationUtils.composeName(name))
                .map(name -> ProgramGenerationUtils.interfaceName2ClassName(name))
                .map(name -> ProgramGenerationUtils.createClass(getPsg().getCm(), name))
                .map(jdc -> implementInterface(jdc))
                .map(jdc -> collectAllInterfaces(jdc))
                .map(entry -> fillWithFieldsAndSetGet(entry))
                .collect(Collectors.toList());

    }

    private JDefinedClass fillWithFieldsAndSetGet(Map.Entry<JDefinedClass, Set<String>> entry) {
        System.out.println(entry.getKey().name() + " " + entry.getValue());
        JDefinedClass jdc = entry.getKey();
        for (String implInterface : entry.getValue()) {
            SelectQueryHolder sqh = executeQuery(
                    SelectQueryFabric.collectClassAttributes(implInterface)
            );
            apply(jdc, sqh, x -> addSetterAndGetter4Class(jdc, x[0], x[1]));
        }
        return entry.getKey();
    }

    private Map.Entry<JDefinedClass, Set<String>> collectAllInterfaces(JDefinedClass jdc) {
        JDefinedClass parentInterface = getParentInterface(jdc);
        Set<String> interfaces = collectAllInterfaces(parentInterface, new HashSet<>());
        System.out.println(jdc.name() + " " + interfaces);
        return new AbstractMap.SimpleEntry<>(jdc, interfaces);
    }

    private JDefinedClass implementInterface(JDefinedClass jdc) {
        return jdc._implements(getParentInterface(jdc));
    }

    private JDefinedClass getParentInterface(JDefinedClass jdc) {
        String interfaceName = ProgramGenerationUtils.className2InterfaceName(jdc.fullName());
        return getPsg().getCm()._getClass(interfaceName);
    }

    private Set<String> collectAllInterfaces(JClass parentInterface, Set<String> interfaceAccum) {
        interfaceAccum.add(parentInterface.name());
        Iterator<JClass> it = parentInterface._implements();
        for (; it.hasNext(); ) {
            collectAllInterfaces(it.next(), interfaceAccum);
        }
        return interfaceAccum;
    }



}
