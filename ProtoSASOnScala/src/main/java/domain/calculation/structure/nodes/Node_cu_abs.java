package domain.calculation.structure.nodes;

import dd.sas.pipeline.calculation.structures.Level;
import dd.sas.pipeline.calculation.structures.flows.associativeflows.AssociativePlainFlow;
import dd.sas.pipeline.calculation.structures.nodes.Node;
import domain.calculation.processors.AbsProcessor;
import domain.worldmodel.relations.Abs;
import domain.worldmodel.worldobjects.CoordinateCenter;
import domain.worldmodel.worldobjects.Landmark;

/**
 * Created by Sergey on 12.10.2016.
 */
public class Node_cu_abs extends Node {

    private AbsProcessor absProcessor = new AbsProcessor();

    private AssociativePlainFlow<CoordinateCenter, Landmark, Abs<CoordinateCenter, Landmark>> coordinatecentre_abs_landmark =
            new AssociativePlainFlow<>(this, absProcessor.coordinatecenter_abs_landmark());

    public Node_cu_abs(Level level) {
        super(level);
    }

    public void setDonor(Node_cu_coordinatecenter node) {
        coordinatecentre_abs_landmark.setDomainFlow(node.getOutFlow());
    }

    public void setDonor(Node_cu_landmark node) {
        coordinatecentre_abs_landmark.setRangeFlow(node.getOutFlow());
    }

    public AssociativePlainFlow<CoordinateCenter, Landmark, Abs<CoordinateCenter, Landmark>> getCoordinatecentre_abs_landmark(){
        return coordinatecentre_abs_landmark;
    }

}
