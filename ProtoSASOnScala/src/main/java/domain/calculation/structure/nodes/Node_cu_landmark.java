package domain.calculation.structure.nodes;

import dd.sas.pipeline.calculation.structures.Level;
import dd.sas.pipeline.calculation.structures.flows.generativeflows.InitialGenerativeFlow;
import dd.sas.pipeline.calculation.structures.nodes.generativeNodes.InitialGenerativeNode;
import domain.worldmodel.worldobjects.Landmark;

/**
 * Created by Sergey on 12.10.2016.
 */
public class Node_cu_landmark extends InitialGenerativeNode<Landmark> {

    public Node_cu_landmark(Level level) {
        super(level);
        setOutFlow(new InitialGenerativeFlow<>(this));
    }

}
