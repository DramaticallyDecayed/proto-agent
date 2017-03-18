package dd.smda.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;

/**
 * Created by Sergey on 26.02.2017.
 */
public class Node_cu_GraphStructureRule extends dd.smda.sas.computation.node.Node_cu_GraphStructureRule {

    public Node_cu_GraphStructureRule(Level level) {
        super(level);
    }

    public CalculationResult customProcess() {
        return CalculationResult.POSITIVE;
    }
}
