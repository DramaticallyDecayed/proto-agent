package dd.protosas.computation;

import dd.protosas.presentation.ElementIdent;
import dd.protosas.computation.levelnode.Node;
import dd.protosas.computability.NodeSpecification;

import java.util.*;

/**
 * Created by Sergey on 25.09.2015.
 */
public class Dispatcher {
    private Queue<ElementIdent> newElemIdents = new LinkedList<>();
    private Map<NodeSpecification, Node> existentNodes;
    private TreeMap<String, List<NodeSpecification>> new2PossibleMapper = new TreeMap<>();


    public Dispatcher(Map<NodeSpecification, Node> existentNodes){
        this.existentNodes = existentNodes;
    }

    public void addNodeSpecification(NodeSpecification possibleNode) {
        for (String base : possibleNode.getBase()) {
            if (new2PossibleMapper.containsKey(base)) {
                new2PossibleMapper.get(base).add(possibleNode);
            } else {
                List<NodeSpecification> specs = new ArrayList<>();
                specs.add(possibleNode);
                new2PossibleMapper.put(base, specs);
            }
        }
    }

    public void setOnProcessQueue(ElementIdent elementIdent) {
        newElemIdents.add(elementIdent);
    }

    public void setOnProcessQueue(List<ElementIdent> elementIdents) {
        newElemIdents.addAll(elementIdents);
    }

    public void process() {
        while (!newElemIdents.isEmpty()) {
            ElementIdent ident = newElemIdents.poll();
            for (NodeSpecification spec : new2PossibleMapper.get(ident.getName())) {
                if (existentNodes.containsKey(spec)) {
                    Node node = existentNodes.get(spec);
                    node.notifyOnBaseInput(ident);
                } else {
                    Node node = spec.createNode();
                    existentNodes.put(spec, node);
                    node.notifyOnBaseInput(ident);
                }
            }
        }
    }
}
