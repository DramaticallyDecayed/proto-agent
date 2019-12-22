package pipeline.concrete.calculation;


import dd.sas.pipeline.calculation.structures.nodes.generativenodes.InitialGenerativeNode;
import pipeline.concrete.worldmodel.Player;


/**
 * Created by Sergey on 15.09.2016.
 */
public class Node_Player extends InitialGenerativeNode<Player> {
    public Node_Player() {
        super(any -> new Player());
    }
}
