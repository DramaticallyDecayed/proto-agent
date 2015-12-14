package dd.protosas.computation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Transmitter;
import commonmodel.ElementState;
import dd.protosas.presentation.ElementIdent;
import dd.protosas.computation.levelnode.IdentNode;
import dd.protosas.computability.NodeSpecification;

/**
 * Created by Sergey on 25.09.2015.
 */
public class Level {
    private Dispatcher dispatcher;
    private Transmitter levelTransmitter;
    private Map<NodeSpecification, IdentNode> existentNodes = new HashMap<>();
    private int height;

    public Level(int height) {
        this.height = height;
        levelTransmitter = new Transmitter();
        dispatcher = new Dispatcher(existentNodes, levelTransmitter);
    }

    public Transmitter getLevelTransmitter(){
        return levelTransmitter;
    }

    public void push(ElementState state) {
        dispatcher.setOnProcessQueue(state);
    }

    public void push(List<ElementState> states) {
        dispatcher.setOnProcessQueue(states);
    }

    public void process(){

        dispatcher.process();

        existentNodes.values().forEach(IdentNode::process);

    }

    public void addSpec(NodeSpecification spec) {
        dispatcher.addNodeSpecification(spec);
    }
}
