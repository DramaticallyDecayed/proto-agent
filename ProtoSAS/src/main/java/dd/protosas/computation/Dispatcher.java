package dd.protosas.computation;

import common.Dependency;
import common.Publisher;
import common.Transmitter;
import commonmodel.ElementState;
import dd.protosas.presentation.ElementIdent;
import dd.protosas.computation.levelnode.IdentNode;
import dd.protosas.computability.NodeSpecification;

import java.util.*;

/**
 * Created by Sergey on 25.09.2015.
 */
public class Dispatcher {
    private Queue<ElementState> newElemStates = new LinkedList<>();
    private Transmitter levelTransmitter;

    /*node specifications mapped to ident node presents all possible nodes for the level*/
    private Map<NodeSpecification, IdentNode> existentNodes;

    /*bases (from all possible nodes) mapped on node specification - defines what notifies what nodes activate*/
    private TreeMap<Dependency, List<NodeSpecification>> new2PossibleMapper = new TreeMap<>();


    public Dispatcher(Map<NodeSpecification, IdentNode> existentNodes, Transmitter levelTransmitter){
        this.existentNodes = existentNodes;
        this.levelTransmitter = levelTransmitter;
    }

    public void addNodeSpecification(NodeSpecification possibleNode) {
        for (Dependency base : possibleNode.getBase()) {
            if (new2PossibleMapper.containsKey(base)) {
                new2PossibleMapper.get(base).add(possibleNode);
            } else {
                List<NodeSpecification> specs = new ArrayList<>();
                specs.add(possibleNode);
                new2PossibleMapper.put(base, specs);
            }
        }
    }

    public void setOnProcessQueue(ElementState elementState) {
        newElemStates.add(elementState);
    }

    public void setOnProcessQueue(List<ElementState> elementStates){
        newElemStates.addAll(elementStates);
    }

    public void process() {
        while (levelTransmitter.hasSomthing()) {
            ElementState state = levelTransmitter.poll();

            //TODO: remove it later cos we will know all possible elements...

            if(!new2PossibleMapper.containsKey(new Dependency(state.getClass()))){
                continue;
            }

            for (NodeSpecification spec : new2PossibleMapper.get(new Dependency(state.getClass()))) {
                IdentNode node;
                if (existentNodes.containsKey(spec)) {
                    node = existentNodes.get(spec);
                    node.getTransmitter().inform(state);
                } else {
                    node = spec.createNode();
                    existentNodes.put(spec, node);
                    node.getTransmitter().inform(state);
                }
                Publisher publisher = levelTransmitter.getCurrentPublisher();
                Dependency topic = levelTransmitter.getCurrentTopic();
                publisher.unsubscribe(topic, levelTransmitter);
                publisher.subscribe(topic, node.getTransmitter());
            }
        }
    }
}
