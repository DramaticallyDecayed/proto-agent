package dd.translator.programgeneration.nodecreators;

import com.sun.codemodel.*;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.translator.owlinterplay.SelectQueryFabric;
import dd.translator.programgeneration.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey on 21.05.2016.
 */
public class AssociativeRefiningNodeExtender extends ProgramElementGenerator {

    public AssociativeRefiningNodeExtender(ProgramStructureGenerator psg) {
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
                        (String) record[0])
                )
                .collect(Collectors.toList())
                .stream()
                .map(name -> treatInverse(jdc, name))
                .collect(Collectors.toList())
        ;
        return jdc;
    }

    private JDefinedClass treatInverse(JDefinedClass jdc, String derivativeName) {
        SelectQueryHolder sqh = executeQuery(SelectQueryFabric.findInverseRelationDerivative(
                ProgramGenerationUtils.makeFirsLetterLow(jdc.name()),
                derivativeName));

        if (!sqh.isEmpty()) {
            JDefinedClass relationClass = getRelationClass(sqh, "r");
            JDefinedClass inverseRelationClass = getRelationClass(sqh, "inverser");

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


            JFieldVar derivativeList = jdc.fields().get(derivativeName+"List");

            initRelationMethod.body().add(derivativeList.invoke("add").arg(relationVar));

        }
        return jdc;
    }

    private String addDerivativeCreator(JDefinedClass jdc, String derivativeName) {
        String derivativeClassName = ObjectPropertyGenerator
                .composeName(ProgramGenerationUtils.makeFirsLetterUp(derivativeName));
        JDefinedClass derivativeClass = getPsg().getCm()._getClass(derivativeClassName);
        JMethod newRelationMethod = addNewDerivativeMethod(jdc, derivativeName, derivativeClass);
        //----------------------------------------------------------------------------------------
        addNewDerivative(jdc, derivativeName, derivativeClass, newRelationMethod);
        return derivativeName;
    }

    private void addNewDerivative(JDefinedClass jdc,
                                  String derivativeName,
                                  JDefinedClass derivativeClass,
                                  JMethod newRelationMethod) {
        SelectQueryHolder sqh = executeQuery(SelectQueryFabric.collectRelationAxiomParts(
                ProgramGenerationUtils.makeFirsLetterLow(jdc.name()),
                derivativeName)
        );
        if (!sqh.isEmpty()) {
            JDefinedClass fRelationClass = getRelationClass(sqh, "first");
            JDefinedClass sRelationClass = getRelationClass(sqh, "second");

            JMethod newDerivativeMethod = jdc.method(JMod.PRIVATE, derivativeClass, "newDerivative");
            JVar firstVar = newDerivativeMethod.param(fRelationClass, "first");
            JVar secondVar = newDerivativeMethod.param(sRelationClass, "second");

            JVar derivativeVar = newDerivativeMethod.body()
                    .decl(derivativeClass, derivativeName)
                    .init(JExpr.invoke(newRelationMethod));

            JDefinedClass domainClass = getRelationDefinerClass(
                    executeQuery(SelectQueryFabric.collectDomains(derivativeName))
            );

            JDefinedClass rangeClass = getRelationDefinerClass(
                    executeQuery(SelectQueryFabric.collectRanges(derivativeName))
            );

            newDerivativeMethod.body().invoke(
                    derivativeVar,
                    derivativeClass.getMethod("setDomain", new JType[]{domainClass})
            ).arg(JExpr.invoke(firstVar, fRelationClass.getMethod("getDomain", new JType[]{})));

            newDerivativeMethod.body().invoke(
                    derivativeVar,
                    derivativeClass.getMethod("setRange", new JType[]{rangeClass})
            ).arg(JExpr.invoke(secondVar, sRelationClass.getMethod("getRange", new JType[]{})));

            JType ret = sRelationClass.getMethod("getRange", new JType[]{}).type();

            if(rangeClass.isAssignableFrom(ret.boxify())){
                System.out.println(rangeClass.name() + " is assignable from " + ret.boxify().name());
            }


            sqh = executeQuery(SelectQueryFabric.findInverseRelation(derivativeName));
            if (!sqh.isEmpty()) {
                JDefinedClass inverseRelation = getRelationClass(sqh, "r");
                JMethod iniverseRelationInit = jdc.method(JMod.PRIVATE, void.class, "init" + inverseRelation.name());
                iniverseRelationInit.param(derivativeClass, "relation");
                newDerivativeMethod.body().invoke(iniverseRelationInit).arg(derivativeVar);
            }


            JFieldVar derivativeList = jdc.fields().get(derivativeName+"List");
            newDerivativeMethod.body().add(derivativeList.invoke("add").arg(derivativeVar));

            newDerivativeMethod.body()._return(derivativeVar);

        }
    }

    private JDefinedClass getRelationClass(SelectQueryHolder sqh, String field) {
        String relationName = (String) sqh.firstSlice().get(field);
        String relationClassName = ObjectPropertyGenerator.composeName(ProgramGenerationUtils.makeFirsLetterUp(relationName));
        return getPsg().getCm()._getClass(relationClassName);
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

    //TODO:move to class working with ontology objects
    private JDefinedClass getRelationDefinerClass(SelectQueryHolder sqh) {
        String featureName = (String) sqh.firstSlice().get("f");
        String domainClassName = ProgramGenerationUtils.composeName(featureName);
        return getPsg().getCm()._getClass(domainClassName);
    }
}
