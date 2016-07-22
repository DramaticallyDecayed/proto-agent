package dd.soccer.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;

/**
 * Created by Sergey on 17.06.2016.
 */
public class Node_cu_Ball extends dd.soccer.sas.computation.node.Node_cu_Ball {
    public Node_cu_Ball(Level level) {
        super(level);
    }

    @Override
    public CalculationResult customProcess(){
        return CalculationResult.POSITIVE;
    }
}
