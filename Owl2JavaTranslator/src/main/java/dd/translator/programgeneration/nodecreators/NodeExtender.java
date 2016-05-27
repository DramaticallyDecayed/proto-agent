package dd.translator.programgeneration.nodecreators;

import com.sun.codemodel.JDefinedClass;
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

}
