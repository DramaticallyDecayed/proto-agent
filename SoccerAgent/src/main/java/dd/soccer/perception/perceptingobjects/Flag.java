package dd.soccer.perception.perceptingobjects;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey on 18.10.2015.
 */
public class Flag extends NavigatingLandmark {
    enum FlagType {
        LEFT_TOP("l t", -52.5, -34.0),
        CENTER_TOP("c t", 0.0, -34.0),
        RIGHT_TOP("r t", 52.5, -34.0),
        PENALTY_LEFT_TOP("p l t", -36.0, -20.0),
        PENALTY_LEFT_CENTER("p l c", -36.0, 0.0),
        PENALTY_LEFT_BOTTOM("p l b", -36.0, 20.0),

        GOAL_LEFT_TOP("g l t", -52.5, -7.0),
        GOAL_LEFT("g l", -52.5, 0.0),
        GOAL_LEFT_BOTTOM("g l b", -52.5, 7.0),
        LEFT_BOTTOM("l b", -52.5, 34.0),
        CENTER_BOTTOM("c b", 0.0, 34.0),
        RIGHT_BOTTOM("r b", 52.5, 34.0),

        PENALTY_RIGHT_TOP("p r t", 36.0, -20.0),
        PENALTY_RIGHT_CENTER("p r c", 36.0, 0.0),
        PENALTY_RIGHT_BOTTOM("p r b", 36.0, 20.0),
        GOAL_RIGHT_TOP("g r t", 52.5, -7.0),
        GOAL_RIGHT("g r", 52.5, 0.0),
        GOAL_RIGHT_BOTTOM("g r b", 52.5, 7.0),

        CENTER("c", 0.0, 0.0);

        private String type;
        private double x;
        private double y;

        FlagType(String type, double x, double y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }

        public String getType() {
            return type;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }

    private static Map<String, FlagType> mapper;

    static {
        mapper = new HashMap<>();
        for (FlagType ft : FlagType.values()) {
            mapper.put(ft.type, ft);
        }
    }

    private FlagType type;

    public Flag(String typeString, String params) {
        super(params);
        type = mapper.get(typeString);
        setX(type.getX());
        setY(type.getY());
        setType("flag");
        setSpecialType(type.getType());
    }

    public String toString() {
        return "Flag " + type
                + " on distance:  " + getDistance()
                + " and direction: " + getDirection();
    }
}
