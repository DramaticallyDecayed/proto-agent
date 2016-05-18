package dd.translator.programgeneration;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMod;
import dd.sas.computation.Node;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey on 18.05.2016.
 */
public class NodeGenerator extends ProgramElementGenerator {

    public NodeGenerator(ProgramStructureGenerator psg) {
        super(psg);
    }

    @Override
    public void generate(List<String> elementNames) {
        elementNames.stream()
                .map(name -> trimName(name))
                .map(name -> ProgramGenerationUtils.makeFirsLetterUp(name))
                .map(name -> composeName(name))
                .map(name -> ProgramGenerationUtils.createClass(getPsg().getCm(), name))
                .map(jdc -> link2SAS(jdc))
                .collect(Collectors.toList());
    }

    @Override
    public JDefinedClass link2SAS(JDefinedClass jdc) {
        jdc._extends(Node.class);
        jdc.method(JMod.PUBLIC, void.class, "process").annotate(Override.class);
        return jdc;
    }

    private String trimName(String name) {
        return name.substring(0, name.lastIndexOf("_"));
    }

    private String composeName(String name) {
        return ProgramStructureGenerator.PACKAGE_PREFIX + "." + "computation.node" + "." + name;
    }
}
