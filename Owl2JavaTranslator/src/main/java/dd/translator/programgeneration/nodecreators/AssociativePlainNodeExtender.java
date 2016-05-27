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
public class AssociativePlainNodeExtender extends ProgramElementGenerator {

    public AssociativePlainNodeExtender(ProgramStructureGenerator psg) {
        super(psg);
    }

    @Override
    public List<String> receiveData() {
        SelectQueryHolder         sqh = TranslatorOntologyHandler.INSTANCE.executeQuery(
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
                .collect(Collectors.toList());
        return jdc;
    }

    private JDefinedClass addDerivativeCreator(JDefinedClass jdc, String derivativeName) {
        String derivativeClassName = ObjectPropertyGenerator
                .composeName(ProgramGenerationUtils.makeFirsLetterUp(derivativeName));
        JDefinedClass derivativeClass = getPsg().getCm()._getClass(derivativeClassName);


        JMethod newRelationMethod = addNewDerivativeMethod(jdc, derivativeName, derivativeClass);


        //-------------------------------------------------------------------------------

        JDefinedClass domainClass = getRelationDefinerClass(
                executeQuery(SelectQueryFabric.collectDomains(derivativeName))
        );

        JDefinedClass realDomainClass = getRelationDefinerClass(
                executeQuery(SelectQueryFabric.inferRealDomain(
                        ProgramGenerationUtils.makeFirsLetterLow(jdc.name()),
                        derivativeName)));

        JDefinedClass rangeClass = getRelationDefinerClass(
                executeQuery(SelectQueryFabric.collectRanges(derivativeName))
        );

        JDefinedClass realRangeClass = getRelationDefinerClass(
                executeQuery(SelectQueryFabric.inferRealRange(
                        ProgramGenerationUtils.makeFirsLetterLow(jdc.name()),
                        derivativeName)));

        JMethod creator = jdc.method(
                JMod.PRIVATE,
                derivativeClass,
                "fill" + ProgramGenerationUtils.makeFirsLetterUp(derivativeName));

        JVar derivativeVar = creator.param(derivativeClass, "relation");
        JVar domainVar = creator.param(realDomainClass, "domain");
        JVar rangeVar = creator.param(realRangeClass, "range");

        JMethod donorSetter = derivativeClass.getMethod("setDomain", new JType[]{domainClass});
        JMethod rangeSetter = derivativeClass.getMethod("setRange", new JType[]{rangeClass});


        creator.body().invoke(derivativeVar, donorSetter).arg(domainVar);
        creator.body().invoke(derivativeVar, rangeSetter).arg(rangeVar);
        creator.body()._return(derivativeVar);

        //---------------------------------------------------------------------

        JMethod newDerivativeMethod = jdc.method(JMod.PRIVATE, derivativeClass, "newDerivative");
        newDerivativeMethod.param(realDomainClass, "domain");
        newDerivativeMethod.param(realRangeClass, "range");
        newDerivativeMethod
                .body()
                ._return(
                        JExpr.invoke(creator)
                                .arg(JExpr.invoke(newRelationMethod))
                                .arg(domainVar)
                                .arg(rangeVar)
                );

        return jdc;
    }

    private JMethod addNewDerivativeMethod(JDefinedClass jdc, String derivativeName, JDefinedClass derivativeClass) {
        JMethod newRelationMethod = jdc.method(JMod.PRIVATE, derivativeClass, "new" + ProgramGenerationUtils.makeFirsLetterUp(derivativeName));
        newRelationMethod.body()
                ._return(JExpr._new(derivativeClass));
        return newRelationMethod;
    }

    private JDefinedClass getNodeClass(String name) {
        String nodeClassName = NodeGenerator.composeName(ProgramGenerationUtils.makeFirsLetterUp(name));
        return getPsg().getCm()._getClass(nodeClassName);
    }


    //TODO:move to class working with ontology objects
    private JDefinedClass getRelationDefinerClass(SelectQueryHolder sqh) {
        String featureName = (String) sqh.firstSlice().get("f");
        String domainClassName = ProgramGenerationUtils.composeName(featureName);
        return getPsg().getCm()._getClass(domainClassName);
    }
}
