package dd.translator.programgeneration.nodecreators;

import com.sun.codemodel.*;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.translator.owlinterplay.SelectQueryFabric;
import dd.translator.programgeneration.*;

/**
 * Created by Sergey on 25.05.2016.
 */
public abstract class NodeExtender extends ProgramElementGenerator {

    public NodeExtender(ProgramStructureGenerator psg) {
        super(psg);
    }

    public JDefinedClass getWorldObjectClass(SelectQueryHolder sqh) {
        String featureName = (String) sqh.getTheFirstResult();
        String domainClassName = NameService.worldEntityClassName(featureName);
        return getPsg().getCm()._getClass(domainClassName);
    }


    public JDefinedClass getRelationClass(SelectQueryHolder sqh, String field) {
        String relationName = (String) sqh.firstSlice().get(field);
        String relationClassName = NameService.objectPropertyClassName(relationName);
        return getPsg().getCm()._getClass(relationClassName);
    }



    public JDefinedClass getNodeClass(String name) {
        String nodeClassName = NameService.nodeClassName(name);
        return getPsg().getCm()._getClass(nodeClassName);
    }

    public JDefinedClass getDomainClass(String relationName) {
        return getWorldObjectClass(
                executeQuery(SelectQueryFabric.collectDomains(relationName))
        );
    }

    public JDefinedClass getRangeClass(String relationName){
        return getWorldObjectClass(
                executeQuery(SelectQueryFabric.collectRanges(relationName))
        );
    }

    public static void initInverseRelation(JDefinedClass jdc, ObjectProperty cop, JMethod newDerivativeMethod, JVar derivativeVar) {
        if (cop.hasInverse()) {
            JMethod inverseRelationInit = jdc.method(JMod.PRIVATE, void.class, "init" + cop.getInverseProperty().name());
            inverseRelationInit.param(cop.getPropertyClass(), "relation");
            newDerivativeMethod.body().invoke(inverseRelationInit).arg(derivativeVar);
        }
    }

    public JDefinedClass treatInverse(JDefinedClass jdc, String derivativeName) {
        SelectQueryHolder sqh = executeQuery(SelectQueryFabric.findInverseRelationDerivative(
                ProgramGenerationUtils.makeFirsLetterLow(jdc.name()),
                derivativeName));
        if (!sqh.isEmpty()) {
            ObjectProperty relation = new ObjectProperty(derivativeName);
            relation = new ObjectPropertyFiller(getPsg()).fillObjectPropertyWithData(relation);
            JDefinedClass relationClass = relation.getPropertyClass();
            JDefinedClass inverseRelationClass = getRelationClass(sqh, "inverser");
            JMethod newRelationMethod = jdc.getMethod("new" + relationClass.name(), new JType[]{});
            JMethod initRelationMethod = jdc.getMethod("init" + relationClass.name(), new JType[]{inverseRelationClass});
            JVar relationVar = initRelationMethod
                    .body()
                    .decl(relationClass, derivativeName)
                    .init(JExpr.invoke(newRelationMethod));
            JVar param = initRelationMethod.params().get(0);
            initRelationMethod.body().invoke(
                    relationVar,
                    relation.getDomainSetMethod()
            ).arg(JExpr.invoke(param, inverseRelationClass.getMethod("getRange", new JType[]{})));
            initRelationMethod.body().invoke(
                    relationVar,
                    relation.getRangeSetMethod()
            ).arg(JExpr.invoke(param, inverseRelationClass.getMethod("getDomain", new JType[]{})));
            updateDerivativeList(jdc, derivativeName, initRelationMethod, relationVar);
        }
        return jdc;
    }

    public static void updateDerivativeList(JDefinedClass jdc, String derivativeName, JMethod newDerivativeMethod, JVar derivativeVar) {
        JFieldVar derivativeList = jdc.fields().get(derivativeName + "List");
        newDerivativeMethod.body().add(derivativeList.invoke("add").arg(derivativeVar));
    }

}
