package dd.soccer.perception.perceptingobjects;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey on 18.10.2015.
 */
public class Line extends NavigatingLandmark{

    enum LineType{
        LEFT("l"),
        TOP("t"),
        BOTTOM("b"),
        RIGHT("r");

        private LineType(String type){
            this.type = type;
        }
        private String type;
    }
    private static Map<String, LineType> mapper;
    static{
        mapper = new HashMap<>();
        for(LineType ft : LineType.values()){
            mapper.put(ft.type, ft);
        }
    }

    private LineType type;

    public Line(String typeString, String params){
        super(params);
        type = mapper.get(typeString);
    }

    public String toString(){
        return "Line " + type
                + " on distance:  " + getDistance()
                + " and direction: " + getDirection();
    }
}
