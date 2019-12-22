package pipeline.concrete.calculation;

import dd.sas.pipeline.calculation.structures.nodes.generativenodes.InitialGenerativeNode;
import pipeline.concrete.worldmodel.Ego;

/**
 * Created by Sergey on 15.09.2016.
 */
public class Node_Ego extends InitialGenerativeNode<Ego> {
    public Node_Ego() {
        super(any -> new Ego());
    }
}
