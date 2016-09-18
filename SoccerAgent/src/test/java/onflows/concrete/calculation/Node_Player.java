package onflows.concrete.calculation;


import onflows.calculation.structure.nodes.InitialGenerativeNode;
import onflows.concrete.worldmodel.Player;


/**
 * Created by Sergey on 15.09.2016.
 */
public class Node_Player extends InitialGenerativeNode<Player> {
    public Node_Player() {
        super(any -> new Player());
    }
}
