package dd.translator.programgeneration;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JMod;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.sas.computation.Node;
import dd.translator.owlinterplay.SelectQueryFabric;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey on 18.05.2016.
 */
public class NodeGenerator extends ProgramElementGenerator {

    public NodeGenerator(ProgramStructureGenerator psg) {
        super(psg);
    }

    @Override
    public void generate(List<String> elementNames) {
        elementNames.stream()
                .map(name -> ProgramGenerationUtils.makeFirsLetterUp(name))
                .map(name -> composeName(name))
                .map(name -> ProgramGenerationUtils.createClass(getPsg().getCm(), name))
                .map(jdc -> link2SAS(jdc))
                .map(jdc -> addBases(jdc))
                .collect(Collectors.toList());
    }

    private JDefinedClass addBases(JDefinedClass jdc) {
        SelectQueryHolder sqh = executeQuery(SelectQueryFabric.collectNodeBases(
                ProgramGenerationUtils.makeFirsLetterLow(jdc.name()))
        );

        if (!sqh.isEmpty()) {
            sqh.asStream()
                    .map(record -> addBase(jdc, (String) record[0], (String) record[1]))
                    .collect(Collectors.toList());
        }

        return jdc;
    }

    private JDefinedClass addBase(JDefinedClass jdc, String name, String type) {
        String className = "";
        switch (type) {
            case "Class":
                className = ProgramGenerationUtils
                        .composeName(ProgramGenerationUtils.makeFirsLetterUp(name));
                break;
            case "ObjectProperty":
                className = ObjectPropertyGenerator
                        .composeName(ProgramGenerationUtils.makeFirsLetterUp(name));
                break;
        }
        JClass fieldCommonClass = getPsg().getCm().ref(List.class);
        JDefinedClass fieldNarrowClass = getPsg().getCm()._getClass(className);
        JClass fieldFinalClass = fieldCommonClass.narrow(fieldNarrowClass);
        JClass fieldCommonImplementationClass = getPsg().getCm().ref(ArrayList.class);
        JClass fieldNarrowedImplementationClass = fieldCommonImplementationClass.narrow(fieldNarrowClass);
        jdc.field(JMod.PRIVATE,
                fieldFinalClass,
                ProgramGenerationUtils.makeFirsLetterLow(name),
                JExpr._new(fieldNarrowedImplementationClass));
        return jdc;
    }

    @Override
    public JDefinedClass link2SAS(JDefinedClass jdc) {
        jdc._extends(Node.class);
        jdc.method(JMod.PUBLIC, void.class, "process").annotate(Override.class);
        return jdc;
    }

    private String composeName(String name) {
        return ProgramStructureGenerator.PACKAGE_PREFIX + "." + "computation.node" + "." + name;
    }
}
