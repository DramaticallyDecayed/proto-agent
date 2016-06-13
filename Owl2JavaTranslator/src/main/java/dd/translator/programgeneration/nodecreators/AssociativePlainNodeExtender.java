package dd.translator.programgeneration.nodecreators;

import com.sun.codemodel.*;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.translator.owlinterplay.SelectQueryFabric;
import dd.translator.owlinterplay.TranslatorOntologyHandler;
import dd.translator.programgeneration.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey on 20.05.2016.
 */
public class AssociativePlainNodeExtender extends NodeExtender {

    public AssociativePlainNodeExtender(ProgramStructureGenerator psg) {
        super(psg);
    }

    @Override
    public List<String> receiveData() {
        SelectQueryHolder sqh = TranslatorOntologyHandler.INSTANCE.executeQuery(
                SelectQueryFabric.collectAssociativePlainNodes());
        return sqh.getDisk("nd");
    }

    @Override
    public void generate() {
        List<String> elementNames = receiveData();
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
                .collect(Collectors.toList());
        return jdc;
    }

    private String addDerivativeCreator(JDefinedClass jdc, String derivativeName) {

        ObjectProperty relation = new ObjectProperty(derivativeName);
        relation = new ObjectPropertyFiller(getPsg()).fillObjectPropertyWithData(relation);

        JMethod newRelationMethod = addNewDerivativeMethod(jdc, derivativeName, relation.getPropertyClass());


        //-------------------------------------------------------------------------------


        SelectQueryHolder sqh = executeQuery(SelectQueryFabric.isAssociativeRelation(
                ProgramGenerationUtils.makeFirsLetterLow(derivativeName))
        );
        if(!sqh.isEmpty()) {
            JMethod creator = jdc.method(
                    JMod.PRIVATE,
                    relation.getPropertyClass(),
                    "fill" + ProgramGenerationUtils.makeFirsLetterUp(derivativeName));

            JVar derivativeParam = creator.param(relation.getPropertyClass(), "relation");
            JVar domainParam = creator.param(relation.getPropertyDomain(), "domain");
            JVar rangeParam = creator.param(relation.getPropertyRange(), "range");

            creator.body().invoke(derivativeParam, relation.getDomainSetMethod()).arg(domainParam);
            creator.body().invoke(derivativeParam, relation.getRangeSetMethod()).arg(rangeParam);
            creator.body()._return(derivativeParam);

            //---------------------------------------------------------------------

            JMethod newDerivativeMethod = jdc.method(JMod.PRIVATE, relation.getPropertyClass(), "newDerivative");
            newDerivativeMethod.param(relation.getPropertyDomain(), "domain");
            newDerivativeMethod.param(relation.getPropertyRange(), "range");
            JVar derivativeVar = newDerivativeMethod.body()
                    .decl(relation.getPropertyClass(), derivativeName)
                    .init(JExpr.invoke(creator)
                            .arg(JExpr.invoke(newRelationMethod))
                            .arg(domainParam)
                            .arg(rangeParam));
            initInverseRelation(jdc, relation, newDerivativeMethod, derivativeVar);
            newDerivativeMethod.body()._return(derivativeVar);
        }
        return derivativeName;
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
