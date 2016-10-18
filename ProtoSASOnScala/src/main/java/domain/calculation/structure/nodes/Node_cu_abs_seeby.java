package domain.calculation.structure.nodes;

import dd.sas.pipeline.calculation.structures.Level;
import dd.sas.pipeline.calculation.structures.flows.associativeflows.AssociativeRefiningFlow;
import dd.sas.pipeline.calculation.structures.nodes.Node;
import domain.calculation.processors.AbsSeenbyProcessor;
import domain.worldmodel.relations.AbsSeeby;
import domain.worldmodel.worldobjects.CoordinateCenter;
import domain.worldmodel.worldobjects.Ego;
import domain.worldmodel.worldobjects.Landmark;

/**
 * Created by Sergey on 12.10.2016.
 */
public class Node_cu_abs_seeby extends Node {

    private AbsSeenbyProcessor absSeeProcessor = new AbsSeenbyProcessor();

    private AssociativeRefiningFlow<CoordinateCenter, Landmark, Ego, AbsSeeby<CoordinateCenter, Ego>> coordinatecenter_abssee_landmark =
            new AssociativeRefiningFlow<>(this, absSeeProcessor.coordinatecenter_abssee_ego());

    public Node_cu_abs_seeby(Level level) {
        super(level);
    }

    public void setDonor(Node_cu_abs node) {
        coordinatecenter_abssee_landmark.setDomainFlow(node.getCoordinatecentre_abs_landmark());
    }

    public void setDonor(Node_cu_see node) {
        coordinatecenter_abssee_landmark.setRangeFlow(node.getLandmark_seenby_ego_flow());
    }


}
