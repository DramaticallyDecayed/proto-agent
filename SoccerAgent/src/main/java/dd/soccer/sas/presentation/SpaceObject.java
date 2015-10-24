package dd.soccer.sas.presentation;


import commonmodel.ElementState;

/**
 * Created by Sergey on 12.10.2015.
 */
public class SpaceObject  extends ElementState {
    private Double x;
    private Double y;

    public SpaceObject(String name, Double x, Double y){
        super(name);
        this.x = x;
        this.y = y;
    }

    public Double getX(){
        return x;
    }

    public Double getY(){
        return y;
    }

    public void setX(Double x){
        this.x = x;
    }

    public void setY(Double y){
        this.y = y;
    }
}
