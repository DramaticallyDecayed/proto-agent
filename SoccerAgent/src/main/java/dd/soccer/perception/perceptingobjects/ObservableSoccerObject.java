package dd.soccer.perception.perceptingobjects;

import commonmodel.ElementState;

/**
 * Created by Sergey on 23.10.2015.
 */
public class ObservableSoccerObject extends ElementState {

    private double distance;
    private double direction;

    public ObservableSoccerObject(String paramsString) {
        String[] paramStringArray = paramsString.split(" ");
        distance = Double.parseDouble(paramStringArray[0]);
        direction = Double.parseDouble(paramStringArray[1]);
    }

    public ObservableSoccerObject() {
    }

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
