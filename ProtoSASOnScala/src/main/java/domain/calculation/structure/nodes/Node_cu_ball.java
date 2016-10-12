package domain.calculation.structure.nodes;

import dd.sas.pipeline.calculation.structures.Level;
import dd.sas.pipeline.calculation.structures.flows.generativeflows.InitialGenerativeFlow;
import dd.sas.pipeline.calculation.structures.nodes.generativeNodes.InitialGenerativeNode;
import domain.worldmodel.Ball;

/**
 * Created by Sergey on 02.10.2016.
 */
public class Node_cu_ball extends InitialGenerativeNode<Ball> {
    public Node_cu_ball(Level level) {
        super(level);
        setOutFlow(new InitialGenerativeFlow<>(this));
    }
}
