package dd.soccer.perception.perceptingobjects;

/**
 * Created by Sergey on 18.10.2015.
 */
public class ObservableObject {
    private double distance;
    private double direction;

    public ObservableObject(String paramsString){
        String[] paramStringArray = paramsString.split(" ");
        distance = Double.parseDouble(paramStringArray[0]);
        direction = Double.parseDouble(paramStringArray[1]);
    }

    public ObservableObject(){}

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }
}
