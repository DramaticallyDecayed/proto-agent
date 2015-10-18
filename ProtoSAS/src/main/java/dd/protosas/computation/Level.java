package dd.protosas.computation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dd.protosas.presentation.ElementIdent;
import dd.protosas.computation.levelnode.Node;
import dd.protosas.computability.NodeSpecification;

/**
 * Created by Sergey on 25.09.2015.
 */
public class Level {
    private Dispatcher dispatcher;
    private Map<NodeSpecification, Node> existentNodes = new HashMap<>();
    private int height;

    public Level(int height) {
        this.height = height;
        dispatcher = new Dispatcher(existentNodes);
    }

    public void push(ElementIdent ident) {
        dispatcher.setOnProcessQueue(ident);
    }

    public void push(List<ElementIdent> idents) {
        dispatcher.setOnProcessQueue(idents);
    }

    public void process(){
        dispatcher.process();
        for(Node node : existentNodes.values()){
            node.process();
        }
    }

    public void addSpec(NodeSpecification spec) {
        dispatcher.addNodeSpecification(spec);
    }
}
