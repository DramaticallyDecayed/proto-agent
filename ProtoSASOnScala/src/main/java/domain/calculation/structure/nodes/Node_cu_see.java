package domain.calculation.structure.nodes;

import dd.sas.pipeline.calculation.structures.Level;
import dd.sas.pipeline.calculation.structures.flows.associativeflows.AssociativePlainFlow;
import dd.sas.pipeline.calculation.structures.nodes.Node;
import domain.calculation.processors.SeeProcessor;
import domain.worldmodel.Ball;
import domain.worldmodel.Ego;
import domain.worldmodel.Player;
import domain.worldmodel.See;

/**
 * Created by Sergey on 02.10.2016.
 */
public class Node_cu_see extends Node {

    private SeeProcessor processor = new SeeProcessor();

    private AssociativePlainFlow<Ego, Ball, See<Ego, Ball>> ego_see_ball_flow =
            new AssociativePlainFlow<>(this, processor.ego_see_ball());

    private AssociativePlainFlow<Ego, Player, See<Ego, Player>> ego_see_player_flow =
            new AssociativePlainFlow<>(this, processor.ego_see_player());

    public Node_cu_see(Level level) {
        super(level);
    }

    public void setDonor(Node_cu_ego node) {
        ego_see_ball_flow.setDomainFlow(node.getOutFlow());
        ego_see_player_flow.setDomainFlow(node.getOutFlow());
    }

    public void setDonor(Node_cu_ball node) {
        ego_see_ball_flow.setRangeFlow(node.getOutFlow());
    }

    public void setDonor(Node_cu_player node) {
        ego_see_player_flow.setRangeFlow(node.getOutFlow());
    }
}
