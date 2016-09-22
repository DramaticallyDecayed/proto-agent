package dd.translator.programgeneration.nodecreators;

import com.sun.codemodel.*;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.sas.annotations.NodeFieldMarkup;
import dd.sas.annotations.NodeMethodMarkUp;
import dd.sas.annotations.NodePart;
import dd.translator.owlinterplay.SelectQueryFabric;
import dd.translator.owlinterplay.TranslatorOntologyHandler;
import dd.translator.programgeneration.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey on 20.05.2016.
 */
public class AssociativePlainNodeExtender extends AssociativeExtender {

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
                .map(jdc -> addDerivativeGetter(jdc))
                .collect(Collectors.toList());
    }

    private JDefinedClass addCreator(JDefinedClass jdc) {
        SelectQueryHolder sqh = executeQuery(SelectQueryFabric.collectNodeDerivatives(
                ProgramGenerationUtils.makeFirsLetterLow(jdc.name()))
        );
        sqh.asStream()
                .map(record -> addDerivativeCreator(jdc,
                        (String) record[0], getPsg())
                )
                .collect(Collectors.toList())
                .stream()
                .map(name -> treatInverse(jdc, name))
                .collect(Collectors.toList());
        return jdc;
    }

    public static String addDerivativeCreator(JDefinedClass jdc, String derivativeName, ProgramStructureGenerator psg) {
        SelectQueryHolder sqh = executeQuery(SelectQueryFabric.isAssociativeRelation(
                ProgramGenerationUtils.makeFirsLetterLow(derivativeName))
        );
        if (!sqh.isEmpty()) {
            newDerivative(jdc, derivativeName, psg);
        } else {
            ObjectProperty relation = new ObjectProperty(derivativeName);
            relation = new ObjectPropertyFiller(psg).fillObjectPropertyWithData(relation);
            addNewDerivativeMethod(jdc, derivativeName, relation.getPropertyClass());
        }
        return derivativeName;
    }

    public static void newDerivative(JDefinedClass jdc, String derivativeName, ProgramStructureGenerator psg) {
        ObjectProperty relation = new ObjectProperty(derivativeName);
        relation = new ObjectPropertyFiller(psg).fillObjectPropertyWithData(relation);

        JMethod newRelationMethod = addNewDerivativeMethod(jdc, derivativeName, relation.getPropertyClass());

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

        JMethod newDerivativeMethod = jdc.method(JMod.PUBLIC, relation.getPropertyClass(), "newDerivative");
        newDerivativeMethod.param(relation.getPropertyDomain(), "domain");
        newDerivativeMethod.param(relation.getPropertyRange(), "range");
        JVar derivativeVar = newDerivativeMethod.body()
                .decl(relation.getPropertyClass(), derivativeName)
                .init(JExpr.invoke(creator)
                        .arg(JExpr.invoke(newRelationMethod))
                        .arg(domainParam)
                        .arg(rangeParam));
        initInverseRelation(jdc, relation, newDerivativeMethod, derivativeVar);
        updateDerivativeList(jdc, derivativeName, newDerivativeMethod, derivativeVar);
        newDerivativeMethod.body()._return(derivativeVar);
    }

    private static JMethod addNewDerivativeMethod(JDefinedClass jdc, String derivativeName, JDefinedClass derivativeClass) {
        JMethod newRelationMethod = jdc.getMethod("new" + ProgramGenerationUtils.makeFirsLetterUp(derivativeName), new JType[]{});
        if (newRelationMethod == null) {
            newRelationMethod = jdc.method(JMod.PRIVATE, derivativeClass, "new" + ProgramGenerationUtils.makeFirsLetterUp(derivativeName));
            newRelationMethod.body()
                    ._return(JExpr._new(derivativeClass));
        }
        return newRelationMethod;
    }

}
