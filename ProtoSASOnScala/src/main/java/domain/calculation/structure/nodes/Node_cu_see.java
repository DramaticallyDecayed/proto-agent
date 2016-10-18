package domain.calculation.structure.nodes;

import dd.sas.pipeline.calculation.structures.Level;
import dd.sas.pipeline.calculation.structures.flows.associativeflows.AssociativePlainFlow;
import dd.sas.pipeline.calculation.structures.nodes.Node;
import domain.calculation.processors.SeeProcessor;
import domain.calculation.processors.SeenbyProcessor;
import domain.worldmodel.relations.Seenby;
import domain.worldmodel.worldobjects.Ball;
import domain.worldmodel.worldobjects.Ego;
import domain.worldmodel.worldobjects.Landmark;
import domain.worldmodel.worldobjects.Player;
import domain.worldmodel.relations.See;

/**
 * Created by Sergey on 02.10.2016.
 */
public class Node_cu_see extends Node {

    private SeeProcessor seeProcessor = new SeeProcessor();
    private SeenbyProcessor seenbyProcessor = new SeenbyProcessor();

    private AssociativePlainFlow<Ego, Ball, See<Ego, Ball>> ego_see_ball_flow =
            new AssociativePlainFlow<>(this, seeProcessor.ego_see_ball());

    private AssociativePlainFlow<Ego, Player, See<Ego, Player>> ego_see_player_flow =
            new AssociativePlainFlow<>(this, seeProcessor.ego_see_player());

    private AssociativePlainFlow<Ball, Ego, Seenby<Ball, Ego>> ball_seenby_ego_flow =
            new AssociativePlainFlow<>(this, seenbyProcessor.ball_seenby_ego());

    private AssociativePlainFlow<Player, Ego, Seenby<Player, Ego>> player_seenby_ego_flow =
            new AssociativePlainFlow<>(this, seenbyProcessor.player_seenby_ego());

    private AssociativePlainFlow<Ego, Landmark, See<Ego, Landmark>> ego_see_landmark_flow =
            new AssociativePlainFlow<>(this, seeProcessor.ego_see_landmark());

    private AssociativePlainFlow<Landmark, Ego, Seenby<Landmark, Ego>> landmark_seenby_ego_flow =
            new AssociativePlainFlow<>(this, seenbyProcessor.landmark_seenby_ego());

    public Node_cu_see(Level level) {
        super(level);
    }

    public void setDonor(Node_cu_ego node) {
        ego_see_ball_flow.setDomainFlow(node.getOutFlow());
        ego_see_player_flow.setDomainFlow(node.getOutFlow());
        ego_see_landmark_flow.setDomainFlow(node.getOutFlow());
        ball_seenby_ego_flow.setRangeFlow(node.getOutFlow());
        player_seenby_ego_flow.setRangeFlow(node.getOutFlow());
        landmark_seenby_ego_flow.setRangeFlow(node.getOutFlow());
    }

    public void setDonor(Node_cu_ball node) {
        ego_see_ball_flow.setRangeFlow(node.getOutFlow());
        ball_seenby_ego_flow.setDomainFlow(node.getOutFlow());
    }

    public void setDonor(Node_cu_player node) {
        ego_see_player_flow.setRangeFlow(node.getOutFlow());
        player_seenby_ego_flow.setDomainFlow(node.getOutFlow());
    }

    public void setDonor(Node_cu_landmark node) {
        ego_see_landmark_flow.setRangeFlow(node.getOutFlow());
        landmark_seenby_ego_flow.setDomainFlow(node.getOutFlow());
    }

    public AssociativePlainFlow<Landmark, Ego, Seenby<Landmark, Ego>> getLandmark_seenby_ego_flow(){
        return landmark_seenby_ego_flow;
    }
}
