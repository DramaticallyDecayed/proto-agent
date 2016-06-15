package dd.soccer.perception.perceptingobjects;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey on 18.10.2015.
 */
public class Line extends NavigatingLandmark{

    enum LineType{
        LEFT("l", -52.5, null),
        TOP("t", null, -34.0),
        BOTTOM("b", null, 34.0),
        RIGHT("r", 52.5, null);

        private String type;
        private Double x;
        private Double y;

        LineType(String type, Double x, Double y){
            this.type = type;
            this.x = x;
            this.y = y;
        }

        public String getType() {
            return type;
        }

        public Double getX() {
            return x;
        }

        public Double getY() {
            return y;
        }
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
        setX(type.getX());
        setY(type.getY());
        setType("line");
        setSpecialType(type.getType());
    }

    public String toString(){
        return "Line " + type
                + " on distance:  " + getDistance()
                + " and direction: " + getDirection();
    }
}
