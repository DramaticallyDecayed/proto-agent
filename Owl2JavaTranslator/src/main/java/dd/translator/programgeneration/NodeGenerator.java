package dd.translator.programgeneration;

import com.sun.codemodel.*;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.sas.annotations.NodeMarkup;
import dd.sas.annotations.NodePart;
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
                .collect(Collectors.toList())
                .stream()
                .map(jdc -> addDerivatives(jdc))
                .collect(Collectors.toList())
                .stream()
                .map(jdc -> addBases(jdc))
                .collect(Collectors.toList());
    }

    private JDefinedClass addDerivatives(JDefinedClass jdc) {
        SelectQueryHolder sqh = executeQuery(SelectQueryFabric.collectNodeDerivatives(
                ProgramGenerationUtils.makeFirsLetterLow(jdc.name()))
        );
        sqh.asStream()
                .map(record -> addDerivative(jdc,
                        (String) record[0],
                        (String) record[1])
                )
                .collect(Collectors.toList());
        return jdc;
    }

    private JDefinedClass addDerivative(JDefinedClass jdc, String name, String type) {
        JFieldVar derivativeField = generateBaseField(jdc, name, type);
        derivativeField.annotate(NodeMarkup.class).param("present", NodePart.DERIVATIVE.getValue());
        ProgramGenerationUtils.addGetter(
                jdc,
                name,
                derivativeField.type(),
                JMod.PUBLIC,
                derivativeField);
        return jdc;
    }

    private JDefinedClass addBases(JDefinedClass jdc) {
        SelectQueryHolder sqh = executeQuery(SelectQueryFabric.collectNodeBases(
                ProgramGenerationUtils.makeFirsLetterLow(jdc.name()))
        );
        if (!sqh.isEmpty()) {
            sqh.asStream()
                    .map(record -> addBase(jdc,
                            (String) record[0],
                            (String) record[1],
                            (String) record[2])
                    )
                    .collect(Collectors.toList());
        }
        return jdc;
    }

    private JDefinedClass addBase(JDefinedClass jdc, String donor, String name, String type) {
        JFieldVar baseField = generateBaseField(jdc, name, type);
        baseField.annotate(NodeMarkup.class).param("present", NodePart.BASE.getValue());
        JFieldVar donorField = generateDonorField(jdc, donor);
        donorField.annotate(NodeMarkup.class).param("present", NodePart.DONOR.getValue());
        generateDonorPullStage(jdc, donorField, baseField);
        return jdc;
    }

    private void generateDonorPullStage(JDefinedClass jdc, JFieldVar donor, JFieldVar base) {
        JMethod method = jdc.getMethod("pullData", new JType[]{});
        JDefinedClass donorClass = getPsg().getCm()._getClass(donor.type().fullName());
        JMethod getter = donorClass.getMethod("get" + ProgramGenerationUtils.makeFirsLetterUp(base.name()),
                new JType[]{});
        if(getter == null){
            System.out.println("");
        }
        method.body().assign(base, donor.invoke(getter));
    }

    private JFieldVar generateDonorField(JDefinedClass jdc, String donor) {
        String donorClassName = composeName(ProgramGenerationUtils.makeFirsLetterUp(donor));
        JType donorClass = getPsg().getCm()._getClass(donorClassName);
        return jdc.field(JMod.PRIVATE,
                donorClass,
                donor);
    }

    private JFieldVar generateBaseField(JDefinedClass jdc, String name, String type) {
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
        return jdc.field(JMod.PRIVATE,
                fieldFinalClass,
                ProgramGenerationUtils.makeFirsLetterLow(name),
                JExpr._new(fieldNarrowedImplementationClass));
    }

    @Override
    public JDefinedClass link2SAS(JDefinedClass jdc) {
        jdc._extends(Node.class);
        jdc.method(JMod.PUBLIC, void.class, "pullData").annotate(Override.class);
        jdc.method(JMod.PUBLIC, void.class, "dropDerivative").annotate(Override.class);
        jdc.method(JMod.PUBLIC, void.class, "customProcess").annotate(Override.class);
        jdc.method(JMod.PUBLIC, void.class, "buildDerivative").annotate(Override.class);
        return jdc;
    }

    public static String composeName(String name) {
        return ProgramStructureGenerator.PACKAGE_PREFIX + "." + "computation.node" + "." + name;
    }
}
