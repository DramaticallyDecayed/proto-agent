package pipeline.concrete.calculation;

import dd.sas.pipeline.calculation.structures.nodes.generativenodes.InitialGenerativeNode;
import pipeline.concrete.worldmodel.Landmark;

/**
 * Created by Sergey on 18.09.2016.
 */
public class Node_Landmark extends InitialGenerativeNode<Landmark> {
    public Node_Landmark() {
        super(any -> new Landmark());
    }
}