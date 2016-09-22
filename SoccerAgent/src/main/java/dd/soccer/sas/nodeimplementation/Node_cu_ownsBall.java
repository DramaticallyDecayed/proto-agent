package dd.soccer.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;

/**
 * Created by Sergey on 09.08.2016.
 */
public class Node_cu_ownsBall extends dd.soccer.sas.computation.node.Node_cu_ownsBall {
    public Node_cu_ownsBall(Level level) {
        super(level);
    }


    @Override
    public CalculationResult customProcess() {
        System.out.println("I'm here!!!!!!!!!!!!!!!!!!!!!!!!!");
        return CalculationResult.UNKNOWN;
    }
}
