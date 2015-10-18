package dd.protosas.computation.levelnode;

import dd.protosas.computability.NodeSpecification;
import dd.protosas.presentation.ElementIdent;

import java.util.*;

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
public class Node {
    private final NodeSpecification nodeSpec;
    private Queue<ElementIdent> baseNotifies = new LinkedList<ElementIdent>();
    private NodeRegister register = new NodeRegister();
    private final NodeProcessor processor;

    public Node(NodeSpecification nodeSpec, NodeProcessor processor) {
        this.nodeSpec = nodeSpec;
        register.initialize(nodeSpec);
        this.processor = processor;
        processor.initialize(register);
    }

    public void notifyOnBaseInput(ElementIdent ident) {
        baseNotifies.add(ident);
    }

    public void process() {
        if (updateRegister()) {
            if (register.haveToBeUpdated()) {
                updateChild();
            } else if (register.isComplete()) {
                createChild();
            }
        }
    }

    private boolean updateRegister() {
        if (baseNotifies.isEmpty()) {
            return false;
        }
        while (!baseNotifies.isEmpty()) {
            ElementIdent ident = baseNotifies.poll();
            register.update(ident);
        }
        return true;
    }

    protected void updateChild(){
        processor.update();
    }

    protected void createChild(){
        processor.create();
    }

}
