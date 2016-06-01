package dd.soccer.perception.perceptingobjects;

/**
 * Created by Sergey on 28.10.2015.
 */
public class NavigatingLandmark extends ObservableSoccerObject {

    private Double x;
    private Double y;

    public NavigatingLandmark() {

    }

    public NavigatingLandmark(String paramsString) {
        super(paramsString);
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }
}
