package dd.translator.programgeneration.nodecreators;

import com.sun.codemodel.*;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.sas.annotations.NodeMethodMarkUp;
import dd.sas.annotations.NodePart;
import dd.translator.owlinterplay.SelectQueryFabric;
import dd.translator.programgeneration.NameService;
import dd.translator.programgeneration.ProgramGenerationUtils;
import dd.translator.programgeneration.ProgramStructureGenerator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey on 29.08.2016.
 */
public abstract class AssociativeExtender extends NodeExtender {
    public AssociativeExtender(ProgramStructureGenerator psg) {
        super(psg);
    }


    public JDefinedClass addDoStaffMethods(JDefinedClass jdc) {

        return jdc;
    }

    public JDefinedClass addDerivativeGetter(JDefinedClass jdc) {
        SelectQueryHolder sqh = executeQuery(SelectQueryFabric.collectOutPropertyFlows(
                ProgramGenerationUtils.makeFirsLetterLow(jdc.name()))
        );
        if (!sqh.isEmpty()) {
            sqh.asStream()
                    .map(record -> createAndAddDerivativeGetter(jdc,
                            (String) record[0],
                            (String) record[1],
                            (String) record[2])
                    )
                    .collect(Collectors.toList());
        }
        return jdc;
    }

    public JDefinedClass createAndAddDerivativeGetter(
            JDefinedClass jdc,
            String domainName,
            String propertyName,
            String rangeName
    ) {
        JDefinedClass propertyClass = getPsg().getCm()._getClass(NameService.objectPropertyClassName(propertyName));
        JClass returnCommonClass = getPsg().getCm().ref(List.class);
        JClass returnFinalClass = returnCommonClass.narrow(propertyClass);

        String name = domainName + "_" + propertyName + "_" + rangeName;
        JMethod getter = jdc.method(
                JMod.PUBLIC,
                returnFinalClass,
                "get" + name + "List"
        );
        getter.annotate(NodeMethodMarkUp.class).param("present", NodePart.DERIVATIVE.getValue());
        getter
                .body()
                ._return(JExpr.cast(returnFinalClass,
                        JExpr.invoke(
                                JExpr.invoke("getDerivativeMap"), "get")
                                .arg(name)
                        )
                );
        return jdc;
    }
}
