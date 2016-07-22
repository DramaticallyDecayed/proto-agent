package dd.soccer.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;

/**
 * Created by Sergey on 20.06.2016.
 */
public class Node_cu_Player extends dd.soccer.sas.computation.node.Node_cu_Player {
    public Node_cu_Player(Level level) {
        super(level);
    }

    @Override
    public CalculationResult customProcess(){
        return CalculationResult.POSITIVE;
    }
}
