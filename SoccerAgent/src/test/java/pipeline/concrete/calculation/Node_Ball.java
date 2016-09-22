package pipeline.concrete.calculation;

import dd.sas.pipeline.calculation.structure.nodes.generativenodes.InitialGenerativeNode;
import pipeline.concrete.worldmodel.Ball;

/**
 * Created by Sergey on 15.09.2016.
 */
public class Node_Ball extends InitialGenerativeNode<Ball> {

    public Node_Ball() {
        super(any -> new Ball());
    }
}