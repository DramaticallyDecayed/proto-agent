package dd.translator.programgeneration.nodecreators;

import com.sun.codemodel.*;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.translator.owlinterplay.SelectQueryFabric;
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
                .collect(Collectors.toList());
        return jdc;
    }

    private JDefinedClass addDerivativeCreator(JDefinedClass jdc, String derivativeName) {

        String derivativeClassName = ObjectPropertyGenerator
                .composeName(ProgramGenerationUtils.makeFirsLetterUp(derivativeName));
        JDefinedClass derivativeClass = getPsg().getCm()._getClass(derivativeClassName);

        JMethod creator = jdc.method(
                JMod.PRIVATE,
                derivativeClass,
                "create" + ProgramGenerationUtils.makeFirsLetterUp(derivativeName));


        JDefinedClass domainClass = getRelationDefinerClass(
                executeQuery(SelectQueryFabric.collectDomains(derivativeName))
        );

        JDefinedClass domainRangeClass = getRelationDefinerClass(
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

        JVar derivativeVar = creator.param(derivativeClass, "relation");
        JVar domainVar = creator.param(domainRangeClass, "domain");
        JVar rangeVar = creator.param(realRangeClass, "range");

        JMethod donorSetter = derivativeClass.getMethod("setDomain",new JType[]{domainClass});
        JMethod rangeSetter = derivativeClass.getMethod("setRange",new JType[]{rangeClass});


        creator.body().invoke(derivativeVar, donorSetter).arg(domainVar);
        creator.body().invoke(derivativeVar, rangeSetter).arg(rangeVar);

        return jdc;
    }

    private JDefinedClass getNodeClass(String name) {
        String nodeClassName = NodeGenerator.composeName(ProgramGenerationUtils.makeFirsLetterUp(name));
        return getPsg().getCm()._getClass(nodeClassName);
    }


    //TODO:move to class working with ontology objects
    private JDefinedClass getRelationDefinerClass(SelectQueryHolder sqh){
        String featureName = (String) sqh.firstSlice().get("f");
        String domainClassName = ProgramGenerationUtils.composeName(featureName);
        return getPsg().getCm()._getClass(domainClassName);
    }
}
