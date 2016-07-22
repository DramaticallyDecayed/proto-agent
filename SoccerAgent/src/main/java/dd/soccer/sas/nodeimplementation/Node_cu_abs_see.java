package dd.soccer.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.soccer.sas.objectproperty.Abs_seenby;
import dd.soccer.sas.objectproperty.See;
import dd.soccer.sas.worldentity.*;

import java.util.stream.Collectors;

/**
 * Created by Sergey on 17.06.2016.
 */
public class Node_cu_abs_see extends dd.soccer.sas.computation.node.Node_cu_abs_see {
    public Node_cu_abs_see(Level level) {
        super(level);
    }

    @Override
    public CalculationResult customProcess() {
        if (!getAbs_seenbyList().isEmpty() && !getSeeList().isEmpty()) {
            getSeeList()
                    .stream()
                    .filter(see -> see.getRange().getX() == null && see.getRange().getY() == null)
                    .map(see -> calculateGlobalCoordinates((Ego)getAbs_seenbyList().get(0).getRange(), see.getRange()))
                    .forEach(visibleObject -> createDerivative(getAbs_seenbyList().get(0).getDomain(), visibleObject));
            return CalculationResult.POSITIVE;
        }
        return CalculationResult.UNKNOWN;
    }

    private void createDerivative(CoordinateCenter domain, VisibleObject visibleObject) {
//        System.out.println(visibleObject.getX() + " " + visibleObject.getY() + " " + visibleObject.getClass());
//        System.out.println("-=======================-");
        Abs_seenby abs_seenby = new Abs_seenby();
        abs_seenby.setDomain(domain);
        See see = new See();
        see.setRange(visibleObject);
        newDerivative(abs_seenby, see);
    }

    private VisibleObject calculateGlobalCoordinates(Ego domain, VisibleObject range) {
        range.setGlobalDirection(domain.getGlobalDirection() + Math.toRadians(range.getDirection()));
        range.setX(domain.getX() + range.getDistance() * Math.cos(range.getGlobalDirection()));
        range.setY(domain.getY() + range.getDistance() * Math.sin(range.getGlobalDirection()));
        return range;
    }
}
