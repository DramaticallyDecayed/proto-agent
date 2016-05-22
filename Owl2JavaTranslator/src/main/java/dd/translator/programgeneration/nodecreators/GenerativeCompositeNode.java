package dd.translator.programgeneration.nodecreators;

import com.sun.codemodel.*;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.sas.annotations.NodeWarning;
import dd.sas.annotations.NodeWarningMessage;
import dd.translator.owlinterplay.SelectQueryFabric;
import dd.translator.programgeneration.*;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Sergey on 22.05.2016.
 */
public class GenerativeCompositeNode extends ProgramElementGenerator {

    public GenerativeCompositeNode(ProgramStructureGenerator psg) {
        super(psg);
    }

    @Override
    public void generate(List<String> elementNames) {
        elementNames.stream()
                .map(name -> getNodeClass(name))
                .map(jdc -> addCreator(jdc))
                .collect(Collectors.toList());
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
                .collect(Collectors.toList())
                .stream()
                .map(name -> generateObjectPropertyCreator(jdc, name))
                .collect(Collectors.toList())
                .stream()
                .map(name -> addNewDerivative(jdc, name))
                .map(name -> treatInverse(jdc, name))
                .collect(Collectors.toList())
        ;
        return jdc;
    }

    private String addNewDerivative(JDefinedClass jdc, String compositeName) {
        SelectQueryHolder sqh = executeQuery(SelectQueryFabric.findCompositeDerivative(
                ProgramGenerationUtils.makeFirsLetterLow(jdc.name()),
                compositeName)
        );
        if (!sqh.isEmpty()) {
            Map slice = sqh.firstSlice();

            JDefinedClass compositeClass = getRelationClass(compositeName);
            JMethod newDerivativeMethod = jdc.method(
                    JMod.PRIVATE,
                    compositeClass,
                    "newDerivative");

            JDefinedClass baseClass = getRelationClass((String) slice.get("base"));

            String secondName = (String) slice.get("second");
            JDefinedClass secondClass = getRelationClass(secondName);

            JVar baseParam = newDerivativeMethod.param(baseClass, "first");

            JVar classVar = newDerivativeMethod
                    .body()
                    .decl(
                            secondClass,
                            secondName,
                            JExpr.invoke(jdc.getMethod("create" + secondClass.name(), new JType[]{baseClass})).arg(baseParam));


            newDerivativeMethod.body()
                    ._return(
                            JExpr.invoke(jdc.getMethod("create" + compositeClass.name(), new JType[]{baseClass, secondClass})
                            ).arg(baseParam).arg(classVar)
                    );

        }
        return compositeName;
    }

    private Map.Entry<String, String> addDerivativeCreator(
            JDefinedClass jdc, String derivativeName, String type) {
        String derivativeClassName = null;
        JDefinedClass derivativeClass = null;
        switch (type) {
            case "Class":
                derivativeClassName = ProgramGenerationUtils.composeName(derivativeName);
                JDefinedClass derivativeInterface = getPsg().getCm()._getClass(derivativeClassName);
                derivativeClass = getPsg().getCm()._getClass(derivativeClassName + "C");
                addNewDerivativeMethod(jdc, derivativeName, derivativeInterface, derivativeClass);
                break;
            case "ObjectProperty":
                derivativeClassName = ObjectPropertyGenerator
                        .composeName(ProgramGenerationUtils.makeFirsLetterUp(derivativeName));
                derivativeClass = getPsg().getCm()._getClass(derivativeClassName);
                addNewDerivativeMethod(jdc, derivativeName, derivativeClass);
                break;
        }
        return new AbstractMap.SimpleEntry(type, derivativeName);
    }

    private String generateObjectPropertyCreator(JDefinedClass jdc, String derivativeName) {
        SelectQueryHolder sqh = executeQuery(SelectQueryFabric.findCompositeDerivative(
                ProgramGenerationUtils.makeFirsLetterLow(jdc.name()),
                derivativeName)
        );
        if (!sqh.isEmpty()) {
            Map slice = sqh.firstSlice();
            addCompositeRelationCreator(
                    jdc,
                    (String) slice.get("r"),
                    (String) slice.get("base"),
                    (String) slice.get("second"));
        } else {
            sqh = executeQuery(SelectQueryFabric.findSecondPartOfCompositeDerivative(
                    ProgramGenerationUtils.makeFirsLetterLow(jdc.name()),
                    derivativeName));
            if (!sqh.isEmpty()) {
                Map slice = sqh.firstSlice();
                addSecondRelationCreator(jdc, (String) slice.get("r"), (String) slice.get("base"));
            }
        }


        return derivativeName;
    }

    private void addSecondRelationCreator(JDefinedClass jdc, String secondName, String baseName) {
        JDefinedClass secondClass = getRelationClass(secondName);
        JMethod createSecondMethod = jdc.method(JMod.PRIVATE, secondClass, "create" + secondClass.name());

        JDefinedClass baseClass = getRelationClass(baseName);
        JVar baseParam = createSecondMethod.param(baseClass, "base");


        SelectQueryHolder sqh = executeQuery(
                SelectQueryFabric.retrieveClassDerivative(
                        ProgramGenerationUtils.makeFirsLetterLow(jdc.name()),
                        secondName)
        );

        String className = (String) sqh.firstSlice().get("c");
        String classClassName = ProgramGenerationUtils.composeName(className);
        JDefinedClass classClass = getPsg().getCm()._getClass(classClassName);

        JVar classVar = createSecondMethod
                .body()
                .decl(
                        classClass,
                        ProgramGenerationUtils.makeFirsLetterLow(className),
                        JExpr.invoke(jdc.getMethod("new" + className, new JType[]{})));

        JFieldVar classList = jdc.fields().get(ProgramGenerationUtils.makeFirsLetterLow(className) + "List");
        createSecondMethod.body().add(classList.invoke("add").arg(classVar));

        JVar secondVar = createSecondMethod
                .body()
                .decl(
                        secondClass,
                        secondName,
                        JExpr.invoke(jdc.getMethod("new" + secondClass.name(), new JType[]{})));

        //-----------------------------------------------------------------------
        boolean unsafeCasting = false;

        JDefinedClass secondDomainClass = getRelationDefinerClass(
                executeQuery(SelectQueryFabric.collectDomains(secondName))
        );

        JMethod baseRange = baseClass.getMethod("getRange", new JType[]{});
        if (baseRange.type().boxify().isAssignableFrom(secondDomainClass)
                && baseRange.type().boxify() != secondDomainClass) {
            createSecondMethod.body().invoke(
                    secondVar,
                    secondClass.getMethod("setDomain", new JType[]{secondDomainClass})
            ).arg(JExpr.cast(secondDomainClass, JExpr.invoke(baseParam, baseRange)));
            unsafeCasting = true;
        } else {
            createSecondMethod.body().invoke(
                    secondVar,
                    secondClass.getMethod("setDomain", new JType[]{secondDomainClass})
            ).arg(JExpr.invoke(baseParam, baseRange));
        }


        //---------------------------------------------------------------------------------------
        JDefinedClass secondRangeClass = getRelationDefinerClass(
                executeQuery(SelectQueryFabric.collectRanges(secondName))
        );

        if (classVar.type().boxify().isAssignableFrom(secondRangeClass)
                && classVar.type().boxify() != secondRangeClass) {
            createSecondMethod.body().invoke(
                    secondVar,
                    secondClass.getMethod("setRange", new JType[]{secondRangeClass})
            ).arg(JExpr.cast(secondRangeClass, classVar));
        } else {
            createSecondMethod.body().invoke(
                    secondVar,
                    secondClass.getMethod("setRange", new JType[]{secondRangeClass})
            ).arg(classVar);
        }

        if (unsafeCasting) {
            createSecondMethod.annotate(NodeWarning.class).param("warning", NodeWarningMessage.UNSAFE_CASTING.getValue());
        }

        //----------------------------------------------------------------------------------------

        JFieldVar secondList = jdc.fields().get(secondName + "List");
        createSecondMethod.body().add(secondList.invoke("add").arg(secondVar));

        sqh = executeQuery(SelectQueryFabric.findInverseRelation(secondName));
        if (!sqh.isEmpty()) {
            JDefinedClass inverseRelation = retriveClass(sqh, "r");
            JMethod iniverseRelationInit = jdc.method(JMod.PRIVATE, void.class, "init" + inverseRelation.name());
            iniverseRelationInit.param(secondClass, "relation");
            createSecondMethod.body().invoke(iniverseRelationInit).arg(secondVar);
        }


        createSecondMethod.body()._return(secondVar);
    }

    private void addCompositeRelationCreator(
            JDefinedClass jdc,
            String compositeName,
            String baseName,
            String secondName) {
        JDefinedClass compositeClass = getRelationClass(compositeName);
        JMethod createCompositeMethod = jdc.method(JMod.PRIVATE, compositeClass, "create" + compositeClass.name());

        JDefinedClass baseClass = getRelationClass(baseName);
        JVar firstVar = createCompositeMethod.param(baseClass, "first");

        JDefinedClass secondClass = getRelationClass(secondName);
        JVar secondVar = createCompositeMethod.param(secondClass, "second");

        JMethod newCompositeMethod = jdc.getMethod("new" + compositeClass.name(), new JType[]{});
        JVar compositeVar = createCompositeMethod.body()
                .decl(compositeClass, compositeName)
                .init(JExpr.invoke(newCompositeMethod));

        //---------------------------------------------------------------------

        boolean unsafeCasting = false;
        JDefinedClass compositeDomainClass = getRelationDefinerClass(
                executeQuery(SelectQueryFabric.collectDomains(compositeName))
        );
        JMethod baseDomain = baseClass.getMethod("getDomain", new JType[]{});

        if (baseDomain.type().boxify().isAssignableFrom(compositeDomainClass)
                && baseDomain.type().boxify() != compositeDomainClass) {
            createCompositeMethod.body().invoke(
                    compositeVar,
                    compositeClass.getMethod("setDomain", new JType[]{compositeDomainClass})
            ).arg(JExpr.cast(compositeDomainClass, JExpr.invoke(firstVar, baseDomain)));
            unsafeCasting = true;
        } else {
            createCompositeMethod.body().invoke(
                    compositeVar,
                    compositeClass.getMethod("setDomain", new JType[]{compositeDomainClass})
            ).arg(JExpr.invoke(firstVar, baseDomain));
        }


        //------------------------------------------------------------------------
        JDefinedClass compositeRangeClass = getRelationDefinerClass(
                executeQuery(SelectQueryFabric.collectRanges(compositeName))
        );
        JMethod secondRange = secondClass.getMethod("getRange", new JType[]{});

        if (secondRange.type().boxify().isAssignableFrom(compositeRangeClass)
                && secondRange.type().boxify() != compositeRangeClass) {
            createCompositeMethod.body().invoke(
                    compositeVar,
                    compositeClass.getMethod("setRange", new JType[]{compositeRangeClass})
            ).arg(JExpr.cast(compositeRangeClass, JExpr.invoke(secondVar, secondRange)));
            unsafeCasting = true;
        } else {
            createCompositeMethod.body().invoke(
                    compositeVar,
                    compositeClass.getMethod("setRange", new JType[]{compositeRangeClass})
            ).arg(JExpr.invoke(secondVar, secondRange));
        }

        if (unsafeCasting) {
            createCompositeMethod.annotate(NodeWarning.class).param("warning", NodeWarningMessage.UNSAFE_CASTING.getValue());
        }
        //-------------------------------------------------------------------------------

        SelectQueryHolder sqh = executeQuery(SelectQueryFabric.findInverseRelation(compositeName));
        if (!sqh.isEmpty()) {
            JDefinedClass inverseRelation = retriveClass(sqh, "r");
            JMethod iniverseRelationInit = jdc.method(JMod.PRIVATE, void.class, "init" + inverseRelation.name());
            iniverseRelationInit.param(compositeClass, "relation");
            createCompositeMethod.body().invoke(iniverseRelationInit).arg(compositeVar);
        }

        JFieldVar derivativeList = jdc.fields().get(compositeName + "List");
        createCompositeMethod.body().add(derivativeList.invoke("add").arg(compositeVar));

        createCompositeMethod.body()._return(compositeVar);

    }

    private JDefinedClass retriveClass(SelectQueryHolder sqh, String field) {
        String relationName = (String) sqh.firstSlice().get(field);
        String relationClassName = ObjectPropertyGenerator.composeName(ProgramGenerationUtils.makeFirsLetterUp(relationName));
        return getPsg().getCm()._getClass(relationClassName);
    }

    private JDefinedClass getRelationClass(String derivativeName) {
        String derivativeClassName = ObjectPropertyGenerator
                .composeName(ProgramGenerationUtils.makeFirsLetterUp(derivativeName));
        return getPsg().getCm()._getClass(derivativeClassName);
    }


    private JDefinedClass treatInverse(JDefinedClass jdc, String derivativeName) {
        SelectQueryHolder sqh = executeQuery(SelectQueryFabric.findInverseRelationDerivative(
                ProgramGenerationUtils.makeFirsLetterLow(jdc.name()),
                derivativeName));

        if (!sqh.isEmpty()) {
            JDefinedClass relationClass = retriveClass(sqh, "r");
            JDefinedClass inverseRelationClass = retriveClass(sqh, "inverser");

            JMethod newRelationMethod = jdc.getMethod("new" + relationClass.name(), new JType[]{});
            JMethod initRelationMethod = jdc.getMethod("init" + relationClass.name(), new JType[]{inverseRelationClass});

            JVar relationVar = initRelationMethod
                    .body()
                    .decl(relationClass, derivativeName)
                    .init(JExpr.invoke(newRelationMethod));

            JDefinedClass domainClass = getRelationDefinerClass(
                    executeQuery(SelectQueryFabric.collectDomains(derivativeName))
            );

            JDefinedClass rangeClass = getRelationDefinerClass(
                    executeQuery(SelectQueryFabric.collectRanges(derivativeName))
            );

            JVar param = initRelationMethod.params().get(0);


            initRelationMethod.body().invoke(
                    relationVar,
                    relationClass.getMethod("setDomain", new JType[]{domainClass})
            ).arg(JExpr.invoke(param, inverseRelationClass.getMethod("getRange", new JType[]{})));

            initRelationMethod.body().invoke(
                    relationVar,
                    relationClass.getMethod("setRange", new JType[]{rangeClass})
            ).arg(JExpr.invoke(param, inverseRelationClass.getMethod("getDomain", new JType[]{})));


            JFieldVar derivativeList = jdc.fields().get(derivativeName + "List");

            initRelationMethod.body().add(derivativeList.invoke("add").arg(relationVar));

        }
        return jdc;
    }

    private JDefinedClass getNodeClass(String name) {
        String nodeClassName = NodeGenerator.composeName(ProgramGenerationUtils.makeFirsLetterUp(name));
        return getPsg().getCm()._getClass(nodeClassName);
    }

    private JMethod addNewDerivativeMethod(JDefinedClass jdc, String derivativeName, JDefinedClass derivativeClass) {
        JMethod newRelationMethod = jdc.method(JMod.PRIVATE, derivativeClass, "new" + ProgramGenerationUtils.makeFirsLetterUp(derivativeName));
        newRelationMethod.body()
                ._return(JExpr._new(derivativeClass));
        return newRelationMethod;
    }

    private JMethod addNewDerivativeMethod(JDefinedClass jdc, String derivativeName, JDefinedClass derivativeInterface, JDefinedClass derivativeClass) {
        JMethod newRelationMethod = jdc.method(JMod.PRIVATE, derivativeInterface, "new" + ProgramGenerationUtils.makeFirsLetterUp(derivativeName));
        newRelationMethod.body()
                ._return(JExpr._new(derivativeClass));
        return newRelationMethod;
    }

    //TODO:move to class working with ontology objects
    private JDefinedClass getRelationDefinerClass(SelectQueryHolder sqh) {
        String featureName = (String) sqh.firstSlice().get("f");
        String domainClassName = ProgramGenerationUtils.composeName(featureName);
        return getPsg().getCm()._getClass(domainClassName);
    }
}
