package dd.protosas.computation.levelnode;

import dd.protosas.computability.NodeSpecification;
import dd.protosas.presentation.ElementIdent;

/**
 * Created by Sergey on 23.10.2015.
 */
public class DataNode implements INode {

    private NodeRegister register = new NodeRegister();
    private final NodeProcessor processor;

    public DataNode(NodeSpecification nodeSpec, NodeProcessor processor) {
        register.initialize(nodeSpec);
        this.processor = processor;
        processor.initialize(register);
    }

    @Override
    public void process() {
        processor.create();
    }

}
