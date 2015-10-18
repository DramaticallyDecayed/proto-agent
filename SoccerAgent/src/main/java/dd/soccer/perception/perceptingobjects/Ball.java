package dd.soccer.perception.perceptingobjects;

/**
 * Created by Sergey on 18.10.2015.
 */
public class Ball extends ObservableObject {

    public Ball(String paramsString){
        String[] paramStringArray = paramsString.split(" ");
        setDistance(Double.parseDouble(paramStringArray[0]));
        setDirection(Double.parseDouble(paramStringArray[1]));
    }

    public String toString(){
        return "Ball "
                + " distance: " + getDistance()
                + " direction: " + getDirection();
    }

}
