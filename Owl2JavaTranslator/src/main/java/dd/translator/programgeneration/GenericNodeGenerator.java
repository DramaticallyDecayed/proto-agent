package dd.translator.programgeneration;

import com.sun.codemodel.*;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.sas.annotations.NodeFieldMarkup;
import dd.sas.annotations.NodeMethodMarkUp;
import dd.sas.annotations.NodePart;
import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.sas.computation.Node;
import dd.translator.owlinterplay.SelectQueryFabric;
import dd.translator.owlinterplay.TranslatorOntologyHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey on 06.06.2016.
 */
public class GenericNodeGenerator extends ProgramElementGenerator {

    public GenericNodeGenerator(ProgramStructureGenerator psg) {
        super(psg);
    }

    @Override
    public List<String> receiveData() {
        SelectQueryHolder sqh = TranslatorOntologyHandler.INSTANCE.executeQuery(
                SelectQueryFabric.collectNodes());
        return sqh.getDisk("nd");
    }

    @Override
    public void generate() {
        List<String> elementNames = receiveData();
        elementNames.stream()
                .map(name -> ProgramGenerationUtils.makeFirsLetterUp(name))
                .map(name -> composeName(name))
                .map(name -> ProgramGenerationUtils.createClass(getPsg().getCm(), name))
                .map(jdc -> createOxidizingInterface(jdc))
                .map(jdc -> link2SAS(jdc))
                .collect(Collectors.toList())
                .stream()
                .map(jdc -> addDerivatives(jdc))
                .collect(Collectors.toList())
                .stream()
                .map(jdc -> addBases(jdc))
                .map(jdc -> addGenericBaseGetters(jdc))
                .collect(Collectors.toList())
        ;
    }

    private JDefinedClass addGenericBaseGetters(JDefinedClass jdc) {
        SelectQueryHolder sqh = executeQuery(SelectQueryFabric.collectGenericBases(
                ProgramGenerationUtils.makeFirsLetterLow(jdc.name()))
        );
        sqh.asStream().forEach(result -> addGenericBaseGetter(jdc, (String) result[0], (String) result[1]));
        return jdc;
    }

    private void addGenericBaseGetter(JDefinedClass jdc, String genericBaseName, String genericBaseType) {
        JDefinedClass entityGenericClass = getGenericEntityClass(genericBaseName, genericBaseType);
        JClass returnCommonClass = getPsg().getCm().ref(List.class);
        JClass returnFinalClass = returnCommonClass.narrow(entityGenericClass);
        JMethod genericBaseGetter = jdc.method(JMod.PUBLIC, returnFinalClass, "get" + ProgramGenerationUtils.makeFirsLetterUp(genericBaseName) + "List");
        genericBaseGetter
                .body()
                ._return(JExpr.cast(returnFinalClass,
                        JExpr.invoke(
                                JExpr.invoke("getBaseMap"), "get")
                                .arg(entityGenericClass.dotclass())
                        )
                );

    }

    private JDefinedClass createOxidizingInterface(JDefinedClass jdc) {
        JDefinedClass oxidizingInterface = ProgramGenerationUtils.createInterface(getPsg().getCm(), jdc.fullName() + "Oxidizing");
        oxidizingInterface.method(JMod.PUBLIC, void.class, "setDonor").param(jdc, "donor");
        JMethod pushAsDonor = jdc.method(JMod.PUBLIC, void.class, "pushAsDonor");
        JVar acceptor = pushAsDonor.param(Node.class, "acceptor");
        pushAsDonor.body().invoke(JExpr.cast(oxidizingInterface, acceptor), "setDonor").arg(JExpr._this());
        pushAsDonor.annotate(Override.class);
        return jdc;
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
        derivativeField.annotate(NodeFieldMarkup.class).param("present", NodePart.DERIVATIVE.getValue());
        JMethod getter = ProgramGenerationUtils.addGetter(
                jdc,
                derivativeField.name(),
                derivativeField.type(),
                JMod.PUBLIC,
                derivativeField);
        getter.annotate(NodeMethodMarkUp.class).param("present", NodePart.DERIVATIVE.getValue());
        addDerivativeClearRecord(jdc, derivativeField);
        return jdc;
    }

    private void addDerivativeClearRecord(JDefinedClass jdc, JFieldVar derivativeField) {
        jdc.getMethod("dropDerivative", new JType[]{})
                .body().invoke(derivativeField, "clear");
    }

    private JDefinedClass addBases(JDefinedClass jdc) {
        SelectQueryHolder sqh = executeQuery(SelectQueryFabric.collectNodeGenericBases(
                ProgramGenerationUtils.makeFirsLetterLow(jdc.name()))
        );
        if (!sqh.isEmpty()) {
            sqh.asStream()
                    .map(record -> addBase(jdc,
                            (String) record[0],
                            (String) record[1],
                            (String) record[2],
                            (String) record[3])
                    )
                    .collect(Collectors.toList());
        }
        return jdc;
    }

    private JDefinedClass addBase(
            JDefinedClass jdc,
            String donorName,
            String entityGenericName,
            String entityConcreteName,
            String entityType) {

        JDefinedClass donorClass = getPsg().getCm()._getClass(NameService.nodeClassName(donorName));

        JMethod setDonorMethod = jdc.getMethod("setDonor", new JType[]{donorClass});
        if (setDonorMethod == null) {
            setDonorMethod = jdc.method(JMod.PUBLIC, void.class, "setDonor");
            setDonorMethod.annotate(Override.class);
            setDonorMethod.param(donorClass, "node");
        }

        JDefinedClass entityGenericClass = getGenericEntityClass(entityGenericName, entityType);

        JDefinedClass oxidizingInterface = getPsg().getCm()._getClass(donorClass.fullName() + "Oxidizing");
        jdc._implements(oxidizingInterface);


        setDonorMethod
                .body()
                .invoke("fillDonor")
                .arg(entityGenericClass.dotclass())
                .arg(JExpr.direct("node::get" + ProgramGenerationUtils.makeFirsLetterUp(entityConcreteName) + "List"));
        return jdc;
    }

    private JDefinedClass getGenericEntityClass(String entityGenericName, String entityType) {
        JDefinedClass entityGenericClass;
        if (entityType.equals("Class")) {
            entityGenericClass = getPsg().getCm()._getClass(NameService.worldEntityClassName(ProgramGenerationUtils.makeFirsLetterUp(entityGenericName)));
        } else {
            entityGenericClass = getPsg().getCm()._getClass(NameService.objectPropertyClassName(ProgramGenerationUtils.makeFirsLetterUp(entityGenericName)));
        }
        return entityGenericClass;
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
                ProgramGenerationUtils.makeFirsLetterLow(name) + "List",
                JExpr._new(fieldNarrowedImplementationClass));
    }

    @Override
    public JDefinedClass link2SAS(JDefinedClass jdc) {
        jdc._extends(Node.class);
        jdc.method(JMod.PUBLIC, void.class, "dropDerivative").annotate(Override.class);

        addCustomProcessMethod(jdc);
        addGetNameMethod(jdc);
        addNameStaticField(jdc);

        JMethod constructor = jdc.constructor(JMod.PUBLIC);
        JVar level = constructor.param(Level.class, "level");
        constructor.body().invoke("super").arg(level);
        return jdc;
    }

    private void addNameStaticField(JDefinedClass jdc) {
        jdc.field(
                JMod.PUBLIC | JMod.STATIC | JMod.FINAL,
                getPsg().getCm()._ref(String.class),
                "NAME",
                JExpr.lit(ProgramGenerationUtils.makeFirsLetterLow(jdc.name()))
        );
    }

    private void addGetNameMethod(JDefinedClass jdc) {
        JMethod getNameMethod = jdc.method(JMod.PUBLIC, void.class, "name");
        getNameMethod.type(getPsg().getCm()._ref(String.class));
        getNameMethod.body()._return(JExpr.lit(ProgramGenerationUtils.makeFirsLetterLow(jdc.name())));
        getNameMethod.annotate(Override.class);
    }

    private void addCustomProcessMethod(JDefinedClass jdc) {
        JMethod customProcessMethod = jdc.method(JMod.PUBLIC, CalculationResult.class, "customProcess");
        customProcessMethod.annotate(Override.class);
        customProcessMethod.body()._return(JExpr.direct("CalculationResult.UNKNOWN"));

    }

    public static String composeName(String name) {
        return ProgramStructureGenerator.PACKAGE_PREFIX + "." + "computation.node" + "." + name;
    }
}