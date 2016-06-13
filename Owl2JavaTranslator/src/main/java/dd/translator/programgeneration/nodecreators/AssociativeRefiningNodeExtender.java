package dd.translator.programgeneration.nodecreators;

import com.sun.codemodel.*;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.sas.annotations.NodeWarning;
import dd.sas.annotations.NodeWarningMessage;
import dd.translator.owlinterplay.SelectQueryFabric;
import dd.translator.owlinterplay.TranslatorOntologyHandler;
import dd.translator.programgeneration.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey on 21.05.2016.
 */
public class AssociativeRefiningNodeExtender extends NodeExtender {

    public AssociativeRefiningNodeExtender(ProgramStructureGenerator psg) {
        super(psg);
    }

    @Override
    public List<String> receiveData() {
        SelectQueryHolder sqh = TranslatorOntologyHandler.INSTANCE.executeQuery(
                SelectQueryFabric.collectAssociativeRefiningNodes());
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
                .collect(Collectors.toList())
        ;
        return jdc;
    }

    private String addDerivativeCreator(JDefinedClass jdc, String derivativeName) {
        String derivativeClassName = ObjectPropertyGenerator
                .composeName(ProgramGenerationUtils.makeFirsLetterUp(derivativeName));
        JDefinedClass derivativeClass = getPsg().getCm()._getClass(derivativeClassName);
        JMethod newRelationMethod = addNewDerivativeMethod(jdc, derivativeClass);
        //----------------------------------------------------------------------------------------
        addNewDerivative(jdc, derivativeName, derivativeClass, newRelationMethod);
        return derivativeName;
    }

    private void addNewDerivative(JDefinedClass jdc,
                                  String derivativeName,
                                  JDefinedClass derivativeClass,
                                  JMethod newRelationMethod) {
        SelectQueryHolder sqh = executeQuery(SelectQueryFabric.collectRelationAxiomParts4Node(
                ProgramGenerationUtils.makeFirsLetterLow(jdc.name()),
                derivativeName)
        );
        if (!sqh.isEmpty()) {
            ChainedObjectProperty cop = new ChainedObjectProperty(derivativeName);
            cop = new ObjectPropertyFiller(getPsg()).fillObjectPropertyWithData(cop);
            JMethod newDerivativeMethod = jdc.method(JMod.PRIVATE, derivativeClass, "newDerivative");
            JVar firstVar = newDerivativeMethod.param(cop.getFirstPart(), "first");
            JVar secondVar = newDerivativeMethod.param(cop.getSecondPart(), "second");
            JVar derivativeVar = newDerivativeMethod.body()
                    .decl(derivativeClass, derivativeName)
                    .init(JExpr.invoke(newRelationMethod));
            if (setPropertyDomain(cop, newDerivativeMethod, firstVar, derivativeVar) |
                    setPropertyRange(cop, newDerivativeMethod, secondVar, derivativeVar)) {
                annotateAsUnsafe(newDerivativeMethod);
            }
            initInverseRelation(jdc, cop, newDerivativeMethod, derivativeVar);
            updateDerivativeList(jdc, derivativeName, newDerivativeMethod, derivativeVar);
            newDerivativeMethod.body()._return(derivativeVar);
        }
    }

    private void annotateAsUnsafe(JMethod newDerivativeMethod) {
        newDerivativeMethod.annotate(NodeWarning.class).param("warning", NodeWarningMessage.UNSAFE_CASTING.getValue());
    }

    private boolean setPropertyDomain(ChainedObjectProperty cop, JMethod newDerivativeMethod, JVar firstVar, JVar derivativeVar) {
        ObjectProperty first = new ObjectProperty(cop.getFirstPart().name());
        first = new ObjectPropertyFiller(getPsg()).fillObjectPropertyWithData(first);
        if (first.getPropertyDomain().isAssignableFrom(cop.getPropertyDomain())
                && first.getPropertyDomain() != cop.getPropertyDomain()) {
            newDerivativeMethod.body().invoke(
                    derivativeVar,
                    cop.getDomainSetMethod()
            ).arg(JExpr.cast(
                    cop.getPropertyDomain(),
                    JExpr.invoke(firstVar, first.getDomainGetMethod())
                    )
            );
            return true;
        } else {
            newDerivativeMethod.body().invoke(
                    derivativeVar,
                    cop.getDomainSetMethod()
            ).arg(JExpr.invoke(firstVar, first.getDomainGetMethod()));
            return false;
        }
    }

    private boolean setPropertyRange(ChainedObjectProperty cop, JMethod newDerivativeMethod, JVar secondVar, JVar derivativeVar) {
        ObjectProperty second = new ObjectProperty(cop.getSecondPart().name());
        second = new ObjectPropertyFiller(getPsg()).fillObjectPropertyWithData(second);
        if (second.getPropertyRange().isAssignableFrom(cop.getPropertyRange())
                && second.getPropertyRange() != cop.getPropertyRange()) {
            newDerivativeMethod.body().invoke(
                    derivativeVar,
                    cop.getRangeSetMethod()
            ).arg(JExpr.cast(cop.getPropertyRange(), JExpr.invoke(secondVar, second.getRangeGetMethod())));
            return true;
        } else {
            newDerivativeMethod.body().invoke(
                    derivativeVar,
                    cop.getRangeSetMethod()
            ).arg(JExpr.invoke(secondVar, second.getRangeGetMethod()));
            return false;
        }
    }

    private JMethod addNewDerivativeMethod(JDefinedClass jdc, JDefinedClass derivativeClass) {
        JMethod newRelationMethod = jdc.method(JMod.PRIVATE, derivativeClass, "new" + derivativeClass.name());
        newRelationMethod.body()
                ._return(JExpr._new(derivativeClass));
        return newRelationMethod;
    }
}
