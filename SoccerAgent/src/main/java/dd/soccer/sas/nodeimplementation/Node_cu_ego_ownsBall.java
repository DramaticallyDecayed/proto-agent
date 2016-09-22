package dd.soccer.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.soccer.common.Constants;
import dd.soccer.sas.objectproperty.Abs_see;
import dd.soccer.sas.worldentity.Ball;
import dd.soccer.sas.worldentity.BallC;
import dd.soccer.sas.worldentity.Ego;

import java.util.stream.Collectors;
import java.util.List;

/**
 * Created by Sergey on 22.07.2016.
 */
public class Node_cu_ego_ownsBall extends dd.soccer.sas.computation.node.Node_cu_ego_ownsBall {
    public Node_cu_ego_ownsBall(Level level) {
        super(level);
    }

    @Override
    public CalculationResult customProcess() {

        Ego ego =  null;
        if(!getInverse_abs_seenbyList().isEmpty()){
            ego = getInverse_abs_seenbyList().get(0).getDomain();
        }



        Ball ball = null;

        List<Abs_see> abs_sees = getAbs_seeList().stream().filter(x -> (x.getRange() instanceof BallC)).collect(Collectors.toList());
        if (!abs_sees.isEmpty()) {
            ball = (Ball) abs_sees.get(0).getRange();
        }

        if (ball != null && ego != null) {
            double d = Math.sqrt(Math.pow(ego.getX() - ball.getX(), 2) + Math.pow(ego.getY() - ball.getY(), 2));
            if(d <= Constants.POSSESSING_AREA.getValue() ){
                System.out.println("Posses ball");
            }
            else{
                System.out.println("Does not posses ball");
            }
        }


        return (CalculationResult.UNKNOWN);
    }
}
