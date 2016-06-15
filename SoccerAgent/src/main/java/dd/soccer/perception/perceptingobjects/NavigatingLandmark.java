package dd.soccer.perception.perceptingobjects;

/**
 * Created by Sergey on 28.10.2015.
 */
public class NavigatingLandmark extends ObservableSoccerObject {

    private Double x;
    private Double y;
    private String type;
    private String specialType;

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

    public void setY(Double y) {
        this.y = y;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public String getSpecialType() {
        return specialType;
    }

    public void setSpecialType(String specialType) {
        this.specialType = specialType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
