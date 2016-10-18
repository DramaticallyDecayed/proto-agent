package domain.calculation.structure.nodes;

import dd.sas.pipeline.calculation.structures.Level;
import dd.sas.pipeline.calculation.structures.nodes.generativeNodes.InitialGenerativeNode;
import domain.worldmodel.worldobjects.CoordinateCenter;

/**
 * Created by Sergey on 12.10.2016.
 */
public class Node_cu_coordinatecenter extends InitialGenerativeNode<CoordinateCenter> {
    public Node_cu_coordinatecenter(Level level) {
        super(level);
    }
}
