package dd.soccer.perception.perceptingobjects;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey on 18.10.2015.
 */
public class Flag extends NavigatingLandmark {
    enum FlagType{
        LEFT_TOP("l t"),
        CENTER_TOP("c t"),
        RIGHT_TOP("r t"),
        PENALTY_LEFT_TOP("p l t"),
        PENALTY_LEFT_CENTER("p l c"),
        PENALTY_LEFT_BOTTOM("p l b"),

        GOAL_LEFT_TOP("g l t"),
        GOAL_LEFT("g l"),
        GOAL_LEFT_BOTTOM("g l b"),
        LEFT_BOTTOM("l b"),
        CENTER_BOTTOM("c b"),
        RIGHT_BOTTOM("r b"),

        PENALTY_RIGHT_TOP("p r t"),
        PENALTY_RIGHT_CENTER("p r c"),
        PENALTY_RIGHT_BOTTOM("p r b"),
        GOAL_RIGHT_TOP("g r t"),
        GOAL_RIGHT("g r"),
        GOAL_RIGHT_BOTTOM("g r b"),

        CENTER("c");

        private FlagType(String type){
            this.type = type;
        }
        private String type;
    }
    private static Map<String, FlagType> mapper;
    static{
        mapper = new HashMap<>();
        for(FlagType ft : FlagType.values()){
            mapper.put(ft.type, ft);
        }
    }

    private FlagType type;

    public Flag(String typeString, String params){
        super(params);
        type = mapper.get(typeString);
    }

    public String toString(){
        return "Flag " + type
                + " on distance:  " + getDistance()
                + " and direction: " + getDirection();
    }
}
