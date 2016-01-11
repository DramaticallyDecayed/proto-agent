package dd.protosas.computation.levelnode;

import common.Transmitter;
import commonmodel.ElementState;
import dd.protosas.computability.NodeSpecification;
import dd.protosas.presentation.ElementIdent;


import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Sergey on 25.09.2015.
 */

/**
 * TODO:
 * 1. Share states between nodes so that each node support a stable version of a child that is shared
 * with other nodes and can at the same time update child's state on the base of other node's stable
 * versions. This can constitute a forestall calculation mechanism that may need a correction if
 * forecast have been made on too irrelevant data;
 * 2. Realize mechanism for node state transmission to eliminate conditional complexity. On the stage
 * when we transmit from creation phase to update phase. So in order to not check all the time if an
 * object has been created or not.
 */
public class IdentNode implements INode {

    private Logger logger = Logger.getLogger(IdentNode.class.getName());

    private NodeRegister register = new NodeRegister();
    private final NodeProcessor processor;
    private Transmitter transmitter;

    public IdentNode(NodeSpecification nodeSpec, NodeProcessor processor) {
        register.initialize(nodeSpec);
        this.processor = processor;
        processor.initialize(register);
        transmitter = new Transmitter();
        logger.info("Create IdentNode");
    }

    @Override
    public void process() {
        if (updateRegister()) {
            if (register.haveToBeUpdated()) {
                logger.info("update node");
                updateChild();
            } else if (register.isComplete()) {
                logger.info("create node's child");
                createChild();
            }
        }
    }

    private boolean updateRegister() {

        if (!transmitter.hasSomething()) {
            return false;
        }

        while (transmitter.hasSomething()) {
            ElementState state = transmitter.poll();
            register.update(state);
        }
        return true;
    }

    protected void updateChild() {
        processor.update();
    }

    protected void createChild() {
        processor.create();
    }

    public Transmitter getTransmitter() {
        return transmitter;
    }


}
