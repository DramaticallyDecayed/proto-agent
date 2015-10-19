package dd.soccer.perception.messageprocessing;

import dd.soccer.perception.perceptingobjects.ObservableObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 19.10.2015.
 */
public class SensorFrame {
    private int cycle;
    private List<ObservableObject> observableObjects = new ArrayList<>();

    public SensorFrame(int cycle){
        this.cycle = cycle;
    }

    public void addObservableObject(ObservableObject observableObject){
        observableObjects.add(observableObject);
    }

    public List<ObservableObject> getObservableObjects() {
        return observableObjects;
    }

    public int getCycle() {
        return cycle;
    }

}
