package dd.soccer.perception.perceptingobjects;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey on 18.10.2015.
 */
public class Goal extends ObservableObject{

    enum GoalType {
        LEFT("l"),
        RIGHT("r");

        private GoalType(String type) {
            this.type = type;
        }

        private String type;
        }

    private static Map<String, GoalType> mapper;

    static {
        mapper = new HashMap<>();
        for (GoalType ft : GoalType.values()) {
            mapper.put(ft.type, ft);
        }
    }
    private GoalType type;

    public Goal(String typeString, String params){
        super(params);
        type = mapper.get(typeString);
    }

    public String toString(){
        return "Goal " + type
                + " on distance:  " + getDistance()
                + " and direction: " + getDirection();
    }

}
