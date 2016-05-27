package dd.translator.programgeneration.nodecreators;

import com.sun.codemodel.*;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.translator.owlinterplay.SelectQueryFabric;
import dd.translator.programgeneration.*;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Sergey on 22.05.2016.
 */
public class GenerativeComplexNodeExtender extends NodeExtender {

    public GenerativeComplexNodeExtender(ProgramStructureGenerator psg) {
        super(psg);
    }

    @Override
    public void generate(List<String> elementNames) {
        elementNames.stream()
                .map(name -> getNodeClass(name))
                .map(jdc -> addCreator(jdc))
                .map(jdc -> addDerivative(jdc))
                .collect(Collectors.toList());
    }

    private JDefinedClass addDerivative(JDefinedClass jdc) {
        JMethod newDerivative = jdc.method(JMod.PRIVATE, void.class, "newDerivative");

        SelectQueryHolder sqh = executeQuery(SelectQueryFabric.retrieveClassDerivative(
                ProgramGenerationUtils.makeFirsLetterLow(jdc.name()))
        );

        String className = ProgramGenerationUtils.composeName((String) sqh.firstSlice().get("c"));
        JDefinedClass classClass = getPsg().getCm()._getClass(className);

        JVar classVar = newDerivative
                .body()
                .decl(
                        classClass,
                        ProgramGenerationUtils.makeFirsLetterLow(classClass.name()),
                        JExpr.invoke(jdc.getMethod("new" + classClass.name(), new JType[]{})));

        JFieldVar classList = jdc.fields().get(ProgramGenerationUtils.makeFirsLetterLow(classClass.name()) + "List");
        newDerivative.body().add(classList.invoke("add").arg(classVar));

        sqh = executeQuery(SelectQueryFabric.collectRalationDomainPairs(
                ProgramGenerationUtils.makeFirsLetterLow(jdc.name()))
        );
        sqh.asStream()
                .map(record -> callRelationInitializer(
                        jdc,
                        newDerivative,
                        classVar,
                        (String) record[0],
                        (String) record[1]
                        )
                )
                .collect(Collectors.toList());
        return jdc;
    }

    private JDefinedClass callRelationInitializer(
            JDefinedClass jdc,
            JMethod newDerivative,
            JVar rangeVar,
            String relationName,
            String domainName) {

        JDefinedClass relationClass = getRelationClass(relationName);

        String domainClassName = ProgramGenerationUtils.composeName(domainName);
        JDefinedClass domainClass = getPsg().getCm()._getClass(domainClassName);

        JVar domainParam = newDerivative.param(domainClass, ProgramGenerationUtils.makeFirsLetterLow(domainName));

        JVar relationVar = newDerivative
                .body()
                .decl(
                        relationClass,
                        relationName,
                        JExpr.invoke(jdc.getMethod("init" + relationClass.name(), new JType[]{domainClass, rangeVar.type()}))
                                .arg(domainParam).arg(rangeVar)
                );

        JFieldVar relationList = jdc.fields().get(relationName + "List");

        newDerivative.body().add(relationList.invoke("add").arg(relationVar));

        return jdc;
    }

    private JDefinedClass addCreator(JDefinedClass jdc) {
        SelectQueryHolder sqh = executeQuery(SelectQueryFabric.collectNodeDerivatives(
                ProgramGenerationUtils.makeFirsLetterLow(jdc.name()))
        );
        sqh.asStream()
                .map(record -> addDerivativeCreator(jdc,
                        (String) record[0],
                        (String) record[1]
                        )
                )
                .filter(entry -> entry.getKey().equals("ObjectProperty"))
                .map(entry -> entry.getValue())
                .map(name -> generateObjectPropertyCreator(jdc, name))
                .collect(Collectors.toList());
        return jdc;
    }

    private JDefinedClass generateObjectPropertyCreator(JDefinedClass jdc, String derivativeName) {
        JDefinedClass derivativeClass = getRelationClass(derivativeName);

        JDefinedClass domainClass = getDomainClass(derivativeName);

        JDefinedClass rangeClass = getRangeClass(derivativeName);

        JMethod derivativeMethod = jdc.method(JMod.PRIVATE, derivativeClass, "init" + derivativeClass.name());
        JVar domainVar = derivativeMethod.param(domainClass, "domain");
        JVar rangeVar = derivativeMethod.param(rangeClass, "range");

        JVar derivativeVar = derivativeMethod
                .body()
                .decl(
                        derivativeClass,
                        derivativeName,
                        JExpr.invoke(jdc.getMethod("new" + derivativeClass.name(), new JType[]{})));

        derivativeMethod.body().invoke(
                derivativeVar,
                derivativeClass.getMethod("setDomain", new JType[]{domainClass})
        ).arg(domainVar);


        derivativeMethod.body().invoke(
                derivativeVar,
                derivativeClass.getMethod("setRange", new JType[]{rangeClass})
        ).arg(rangeVar);

        derivativeMethod.body()._return(derivativeVar);

        return jdc;
    }

    private JDefinedClass getRelationClass(String derivativeName) {
        String derivativeClassName = ObjectPropertyGenerator
                .composeName(ProgramGenerationUtils.makeFirsLetterUp(derivativeName));
        return getPsg().getCm()._getClass(derivativeClassName);
    }

    private Map.Entry<String, String> addDerivativeCreator(JDefinedClass jdc, String derivativeName, String type) {
        String derivativeClassName = null;
        JDefinedClass derivativeClass = null;
        switch (type) {
            case "Class":
                derivativeClassName = ProgramGenerationUtils.composeName(derivativeName);
                JDefinedClass derivativeInterface = getPsg().getCm()._getClass(derivativeClassName);
                derivativeClass = getPsg().getCm()._getClass(derivativeClassName + "C");
                addNewDerivativeMethod(jdc, derivativeInterface, derivativeClass);
                break;
            case "ObjectProperty":
                derivativeClassName = ObjectPropertyGenerator
                        .composeName(ProgramGenerationUtils.makeFirsLetterUp(derivativeName));
                derivativeClass = getPsg().getCm()._getClass(derivativeClassName);
                addNewDerivativeMethod(jdc, derivativeClass);
                break;
        }

        return new AbstractMap.SimpleEntry<>(type, derivativeName);
    }

    private JMethod addNewDerivativeMethod(JDefinedClass jdc, JDefinedClass derivativeClass) {
        JMethod newRelationMethod = jdc.method(JMod.PRIVATE, derivativeClass, "new" + derivativeClass.name());
        newRelationMethod.body()
                ._return(JExpr._new(derivativeClass));
        return newRelationMethod;
    }

    private JMethod addNewDerivativeMethod(JDefinedClass jdc, JDefinedClass derivativeInterface, JDefinedClass derivativeClass) {
        JMethod newRelationMethod = jdc.method(JMod.PRIVATE, derivativeInterface, "new" + derivativeInterface.name());
        newRelationMethod.body()
                ._return(JExpr._new(derivativeClass));
        return newRelationMethod;
    }




}
