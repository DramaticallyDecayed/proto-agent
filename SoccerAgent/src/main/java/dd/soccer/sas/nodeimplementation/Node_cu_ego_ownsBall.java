package dd.soccer.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.soccer.sas.objectproperty.Abs_see;
import dd.soccer.sas.objectproperty.Inverse_abs_see;
import dd.soccer.sas.objectproperty.Inverse_abs_seenby;
import dd.soccer.sas.worldentity.BallC;
import dd.soccer.sas.worldentity.VisibleObject;

/**
 * Created by Sergey on 22.07.2016.
 */
public class Node_cu_ego_ownsBall extends dd.soccer.sas.computation.node.Node_cu_ego_ownsBall {
    public Node_cu_ego_ownsBall(Level level) {
        super(level);
    }

    @Override
    public CalculationResult customProcess() {

        //System.out.println("inverse abs " + getInverse_abs_seenbyList().size());
        //System.out.println("see " + getAbs_seeList().size());

        for (Inverse_abs_seenby ias : getInverse_abs_seenbyList()) {
            System.out.println(ias.getDomain());
        }


        for (Abs_see vo : getAbs_seeList()) {
            System.out.println(vo.getRange());
        }

//            System.out.println(
//                    getAbs_seeList().stream().filter(x -> (x.getRange() instanceof BallC)).count()
//            );

        System.out.println("-====================================-");
        return (CalculationResult.UNKNOWN);
    }
}
