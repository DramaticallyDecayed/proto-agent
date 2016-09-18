package onflows.concrete.calculation;

import onflows.calculation.structure.nodes.InitialGenerativeNode;
import onflows.concrete.worldmodel.Ego;

/**
 * Created by Sergey on 15.09.2016.
 */
public class Node_Ego extends InitialGenerativeNode<Ego> {
    public Node_Ego() {
        super(any -> new Ego());
    }
}
