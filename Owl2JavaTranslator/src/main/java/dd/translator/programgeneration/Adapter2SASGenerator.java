package dd.translator.programgeneration;

import com.sun.codemodel.*;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.sas.annotations.NodeFieldMarkup;
import dd.sas.annotations.NodePart;
import dd.sas.computation.Level;
import dd.sas.computation.Node;
import dd.translator.WrappedTranslatorException;
import dd.translator.owlinterplay.SelectQueryFabric;
import dd.translator.owlinterplay.TranslatorOntologyHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey on 28.05.2016.
 */
public class Adapter2SASGenerator extends ProgramElementGenerator {

    public Adapter2SASGenerator(ProgramStructureGenerator psg) {
        super(psg);
    }

    @Override
    public List<String> receiveData() {
        SelectQueryHolder sqh = TranslatorOntologyHandler.INSTANCE.executeQuery(
                SelectQueryFabric.collectAllInitialEntities());
        return sqh.getDisk("c");
    }

    @Override
    public void generate() {
        JDefinedClass adapter = createAdapter();
        castAdapter2FakeNode(adapter);
        List<String> elementNames = receiveData();
        elementNames.stream()
                .map(name -> addGetters(adapter, name))
                .collect(Collectors.toList());
        initPerceptionNodes(adapter);
    }

    private void initPerceptionNodes(JDefinedClass adapter) {
        SelectQueryHolder sqh = TranslatorOntologyHandler.INSTANCE.executeQuery(
                SelectQueryFabric.collectInitialNode());
        sqh.asStream()
                .map(result -> initPerceptionNode((String) result[0], (String) result[1], adapter))
                .collect(Collectors.toList());

    }

    private JDefinedClass initPerceptionNode(String nodeName, String derivativeName, JDefinedClass adapter) {
        String nodeClassName = NameService.nodeClassName(nodeName);
        JDefinedClass nodeClass = getPsg().getCm()._getClass(nodeClassName);
        JFieldVar donorField = addDonorStaff(adapter, nodeClass);
        fillPullMethod(derivativeName, adapter, nodeClass, donorField);
        return adapter;
    }

    private JFieldVar addDonorStaff(JDefinedClass adapter, JDefinedClass nodeClass) {
        String nodeFieldName = ProgramGenerationUtils.makeFirsLetterLow(adapter.name());
        ProgramGenerationUtils.addSettersAndGetters4Class(
                nodeClass,
                nodeFieldName,
                adapter);
        JFieldVar donorField = nodeClass.fields().get(nodeFieldName);
        donorField.annotate(NodeFieldMarkup.class).param("present", NodePart.DONOR.getValue());
        return donorField;
    }

    private void fillPullMethod(String derivativeName, JDefinedClass adapter, JDefinedClass nodeClass, JFieldVar donorField) {
        JMethod derivativeGetter = adapter.getMethod("get" + derivativeName + "List", new JType[]{});
        JFieldVar derivativeField = nodeClass.fields().get(ProgramGenerationUtils.makeFirsLetterLow(derivativeName) + "List");
        JMethod pullDataMethod = nodeClass.getMethod("pullData", new JType[]{});
        if (pullDataMethod == null) {
            pullDataMethod = nodeClass.method(JMod.PUBLIC, void.class, "pullData");
            pullDataMethod.annotate(Override.class);
        }
        pullDataMethod.body().assign(derivativeField, JExpr.invoke(donorField, derivativeGetter));
    }

    private void castAdapter2FakeNode(JDefinedClass adapter) {
        adapter._extends(Node.class);
        JMethod constructor = adapter.constructor(JMod.PUBLIC);
        constructor
                .body()
                .invoke("super")
                .arg(JExpr._new(getPsg().getCm().ref(Level.class)).arg(JExpr.lit(0)));
    }

    private JDefinedClass addGetters(JDefinedClass adapter, String name) {
        String worldEntityClassName = NameService.worldEntityClassName(name);
        JDefinedClass wordEntityClass = getPsg().getCm()._getClass(worldEntityClassName);
        JClass returnGenericClass = getPsg().getCm().ref(List.class);
        JClass returnFinalClass = returnGenericClass.narrow(wordEntityClass);
        adapter.method(JMod.PUBLIC | JMod.ABSTRACT, returnFinalClass, "get" + wordEntityClass.name() + "List");
        return adapter;
    }

    private JDefinedClass createAdapter() {
        try {
            return getPsg().getCm()._class(JMod.PUBLIC | JMod.ABSTRACT, NameService.PACKAGE_PREFIX + "." + "Perceptor2SASAdapter", ClassType.CLASS);
        } catch (JClassAlreadyExistsException e) {
            throw new WrappedTranslatorException(WorldEntityInterfaceGeneration.class.getSimpleName(), e);
        }
    }
}