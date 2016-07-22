package dd.soccer.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.sas.computation.WrappingGetter;
import dd.soccer.sas.worldentity.Point;

/**
 * Created by Sergey on 10.06.2016.
 */
public class Node_cu_abs extends dd.soccer.sas.computation.node.Node_cu_abs {
    public Node_cu_abs(Level level) {
        super(level);
    }

    @Override
    public CalculationResult customProcess() {
        if (getPointList() != null && getCoordinateCenterList() != null) {
            if (!(getPointList().isEmpty() || getCoordinateCenterList().isEmpty())) {
                for(Point point : getPointList()) {
                    newDerivative(getCoordinateCenterList().get(0), point);
                }
                //System.out.println("GENERATE ABS AND INVERSE_ABS!!!");
                return CalculationResult.POSITIVE;
            }
        }
        return CalculationResult.UNKNOWN;
    }
}
