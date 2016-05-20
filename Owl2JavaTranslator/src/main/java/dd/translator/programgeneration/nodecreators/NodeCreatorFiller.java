package dd.translator.programgeneration.nodecreators;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JMod;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.translator.owlinterplay.SelectQueryFabric;
import dd.translator.programgeneration.NodeGenerator;
import dd.translator.programgeneration.ProgramElementGenerator;
import dd.translator.programgeneration.ProgramGenerationUtils;
import dd.translator.programgeneration.ProgramStructureGenerator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey on 20.05.2016.
 */
public class NodeCreatorFiller extends ProgramElementGenerator {


    public NodeCreatorFiller(ProgramStructureGenerator psg) {
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
        String derivativeClassName = ProgramGenerationUtils
                .composeName(ProgramGenerationUtils.makeFirsLetterUp(derivativeName));
        JDefinedClass derivativeClass = getPsg().getCm()._getClass(derivativeClassName);
        jdc.method(JMod.PRIVATE, derivativeClass, "create" + derivativeName)
                .body()
                ._return(JExpr._new(derivativeClass));
        return jdc;
    }

    private JDefinedClass getNodeClass(String name) {
        String nodeClassName = NodeGenerator.composeName(ProgramGenerationUtils.makeFirsLetterUp(name));
        return getPsg().getCm()._getClass(nodeClassName);
    }
}
