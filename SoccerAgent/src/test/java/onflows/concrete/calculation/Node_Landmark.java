package onflows.concrete.calculation;

import onflows.calculation.structure.nodes.InitialGenerativeNode;
import onflows.concrete.worldmodel.Landmark;

/**
 * Created by Sergey on 18.09.2016.
 */
public class Node_Landmark extends InitialGenerativeNode<Landmark> {
    public Node_Landmark() {
        super(any -> new Landmark());
    }
}