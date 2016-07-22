package dd.soccer.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.soccer.sas.objectproperty.Abs;
import dd.soccer.sas.objectproperty.Seenby;
import dd.soccer.sas.worldentity.CoordinateCenter;
import dd.soccer.sas.worldentity.Ego;
import dd.soccer.sas.worldentity.Landmark;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 10.06.2016.
 */
public class Node_cu_abs_seenby extends dd.soccer.sas.computation.node.Node_cu_abs_seenby {

    public Node_cu_abs_seenby(Level level) {
        super(level);
    }


    @Override
    public CalculationResult customProcess() {
        if (getAbsList() != null && getSeenbyList() != null) {
            if (!(getAbsList().isEmpty() || getSeenbyList().isEmpty())) {
                Ego ego = (Ego) getSeenbyList().get(0).getRange();
                CoordinateCenter coordinateCenter = getAbsList().get(0).getDomain();
                List<Landmark> landmarks = new ArrayList<>();
                getAbsList().forEach(abs -> landmarks.add((Landmark) abs.getRange()));
                Ego newEgo = calculateAbsoluteCoordinates(ego, landmarks);
                if (ego != null) {
                    Abs abs = new Abs();
                    abs.setDomain(coordinateCenter);
                    Seenby seenby = new Seenby();
                    seenby.setRange(newEgo);
                    //TODO: think about the used approach.. really we do not need two relations but only its domain and range
                    newDerivative(abs, seenby);
                    return CalculationResult.POSITIVE;
                }
            }
        }
        return CalculationResult.UNKNOWN;
    }

    private Ego calculateAbsoluteCoordinates(Ego ego, List<Landmark> landmarks) {
        Landmark[] ls = new Landmark[2];
        int i = 0;
        for (Landmark landmark : landmarks) {
            if (landmark.getType().equals("flag")) {
                ls[i++] = landmark;
                if (i == 2) {
                    double[] result = calculateCoordinates(ls);
                    ego.setX(result[0]);
                    ego.setY(result[1]);
                    ego.setGlobalDirection(result[2]);
                    return ego;
                }
            }
        }
        return null;
    }


    public static double[] calculateCoordinates(Landmark... ls) {
        double x1 = ls[0].getX();
        double y1 = ls[0].getY();
        double dist1 = ls[0].getDistance();
        double dir1 = ls[0].getDirection();

        double x2 = ls[1].getX();
        double y2 = ls[1].getY();
        double dist2 = ls[1].getDistance();
        double dir2 = ls[1].getDirection();

        double d = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

        double a = (Math.pow(dist1, 2) - Math.pow(dist2, 2) + Math.pow(d, 2)) / (2 * d);

        double cosa = Math.abs(x1 - x2) / d;
        double sina = Math.abs(y1 - y2) / d;


        double rx = x1 + a * cosa;
        double ry = y1 + a * sina;

        double sign = Math.signum(dir2 - dir1);

        double h = Math.sqrt(Math.pow(dist1, 2) - Math.pow(a, 2));

        double x = rx - h * sign * sina;
        double y = ry + h * sign * cosa;

        double angle = normalize(Math.atan2(y1 - y, x1 - x) - Math.toRadians(dir1));

        return new double[]{x, y, angle};
    }

    private static double normalize(double angle) {
        if (Math.abs(angle) > Math.PI) {
            return (-1) * Math.signum(angle) * (2 * Math.PI) + angle;
        }
        return angle;
    }

}
