package dd.smda.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;

/**
 * Created by Sergey on 26.02.2017.
 */
public class Node_cu_GroupRule extends dd.smda.sas.computation.node.Node_cu_GroupRule {
    public Node_cu_GroupRule(Level level) {
        super(level);
    }
    @Override
    public CalculationResult customProcess() {
        return CalculationResult.POSITIVE;
    }

}
