package onflows.concrete.calculation;

import onflows.calculation.structure.nodes.InitialGenerativeNode;
import onflows.concrete.worldmodel.Ball;

/**
 * Created by Sergey on 15.09.2016.
 */
public class Node_Ball extends InitialGenerativeNode<Ball> {

    public Node_Ball() {
        super(any -> new Ball());
    }
}
