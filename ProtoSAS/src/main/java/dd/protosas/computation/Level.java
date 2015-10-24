package dd.protosas.computation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dd.protosas.presentation.ElementIdent;
import dd.protosas.computation.levelnode.IdentNode;
import dd.protosas.computability.NodeSpecification;

/**
 * Created by Sergey on 25.09.2015.
 */
public class Level {
    private Dispatcher dispatcher;
    private Map<NodeSpecification, IdentNode> existentNodes = new HashMap<>();
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
        for(IdentNode node : existentNodes.values()){
            node.process();
        }
    }

    public void addSpec(NodeSpecification spec) {
        dispatcher.addNodeSpecification(spec);
    }
}
