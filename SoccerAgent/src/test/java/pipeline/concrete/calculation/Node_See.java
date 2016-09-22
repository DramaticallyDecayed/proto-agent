package pipeline.concrete.calculation;


import dd.sas.pipeline.calculation.structure.nodes.associativenodes.AssociativePlainNode;
import dd.sas.pipeline.calculation.structure.flows.associativeflows.AssociativePlainFlow;
import pipeline.concrete.worldmodel.*;

/**
 * Created by Sergey on 13.09.2016.
 */
public class Node_See extends AssociativePlainNode {

    private SeeProcessor processor = new SeeProcessor();

    private AssociativePlainFlow<Ego, Ball, See<Ego, Ball>> ego_see_ball
            = new AssociativePlainFlow<>(processor.ego_see_ball());

    private AssociativePlainFlow<Ego, Player, See<Ego, Player>> ego_see_player
            = new AssociativePlainFlow<>(processor.ego_see_player());

    private AssociativePlainFlow<Ego, Landmark, See<Ego, Landmark>> ego_see_landmark
            = new AssociativePlainFlow<>(processor.ego_see_landmark());

    public Node_See(){
        addOutFlow(ego_see_ball);
        addOutFlow(ego_see_player);
        addOutFlow(ego_see_landmark);
    }

    public void setDonor(Node_Ego node){
        ego_see_ball.setDomainFlow(node.getOutFlow());
        ego_see_player.setDomainFlow(node.getOutFlow());
        ego_see_landmark.setDomainFlow(node.getOutFlow());
    }

    public void setDonor(Node_Ball node){
        ego_see_ball.setRangeFlow(node.getOutFlow());
    }

    public void setDonor(Node_Player node){
        ego_see_player.setRangeFlow(node.getOutFlow());
    }

    public void setDonor(Node_Landmark node){
        ego_see_landmark.setRangeFlow(node.getOutFlow());
    }
}
