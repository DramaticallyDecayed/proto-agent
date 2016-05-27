package dd.translator.programgeneration.nodecreators;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JType;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.translator.owlinterplay.SelectQueryFabric;
import dd.translator.programgeneration.NameService;
import dd.translator.programgeneration.ProgramElementGenerator;
import dd.translator.programgeneration.ProgramStructureGenerator;

/**
 * Created by Sergey on 27.05.2016.
 */
public class ObjectPropertyFiller {

    private ProgramStructureGenerator psg;

    public ObjectPropertyFiller(ProgramStructureGenerator psg) {
        this.psg = psg;
    }

    public ChainedObjectProperty fillObjectPropertyWithData(ChainedObjectProperty cop) {
        cop = (ChainedObjectProperty) fillObjectPropertyWithData((ObjectProperty) cop);
        setFirstAndSecondParts(cop);
        return cop;
    }

    private void setFirstAndSecondParts(ChainedObjectProperty cop) {
        SelectQueryHolder sqh = ProgramElementGenerator.executeQuery(
                SelectQueryFabric.collectRelationAxiomParts(
                        cop.getPropertyName())
        );
        cop.setFirstPart(getRelationClass(sqh, "first"));
        cop.setSecondPart(getRelationClass(sqh, "second"));
    }

    public ObjectProperty fillObjectPropertyWithData(ObjectProperty op) {
        op.setPropertyClass(psg.getCm().
                _getClass(NameService.objectPropertyClassName(op.getPropertyName())));
        op.setPropertyDomain(getDomainClass(op.getPropertyName()));
        op.setPropertyRange(getRangeClass(op.getPropertyName()));
        op.setInverseProperty(getInverse(op.getPropertyName()));
        op.setDomainSetMethod(
                op.getPropertyClass().getMethod("setDomain", new JType[]{op.getPropertyDomain()})
        );
        op.setDomainGetMethod(
                op.getPropertyClass().getMethod("getDomain", new JType[]{})
        );
        op.setRangeSetMethod(
                op.getPropertyClass().getMethod("setRange", new JType[]{op.getPropertyRange()})
        );
        op.setRangeGetMethod(
                op.getPropertyClass().getMethod("getRange", new JType[]{})
        );
        return op;
    }

    public JDefinedClass getInverse(String propertyName){
        SelectQueryHolder sqh = ProgramElementGenerator.executeQuery(
                SelectQueryFabric.findInverseRelation(propertyName)
        );
        if (!sqh.isEmpty()) {
            return getRelationClass(sqh, "r");
        }
        return null;
    }

    public JDefinedClass getRelationClass(SelectQueryHolder sqh, String field) {
        String relationName = (String) sqh.firstSlice().get(field);
        String relationClassName = NameService.objectPropertyClassName(relationName);
        return psg.getCm()._getClass(relationClassName);
    }

    public JDefinedClass getWorldObjectClass(SelectQueryHolder sqh) {
        String featureName = (String) sqh.getTheFirstResult();
        String domainClassName = NameService.worldEntityClassName(featureName);
        return psg.getCm()._getClass(domainClassName);
    }


    public JDefinedClass getDomainClass(String relationName) {
        return getWorldObjectClass(
                ProgramElementGenerator.executeQuery(SelectQueryFabric.collectDomains(relationName))
        );
    }

    public JDefinedClass getRangeClass(String relationName) {
        return getWorldObjectClass(
                ProgramElementGenerator.executeQuery(SelectQueryFabric.collectRanges(relationName))
        );
    }
}
