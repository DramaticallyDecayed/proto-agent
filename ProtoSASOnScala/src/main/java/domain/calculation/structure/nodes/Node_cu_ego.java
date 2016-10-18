package domain.calculation.structure.nodes;

import dd.sas.pipeline.calculation.structures.Level;
import dd.sas.pipeline.calculation.structures.flows.generativeflows.InitialGenerativeFlow;
import dd.sas.pipeline.calculation.structures.nodes.generativeNodes.InitialGenerativeNode;
import domain.worldmodel.worldobjects.Ego;

/**
 * Created by Sergey on 02.10.2016.
 */
public class Node_cu_ego extends InitialGenerativeNode<Ego> {
    public Node_cu_ego(Level level) {
        super(level);
    }

}
