package dd.protoperception;

import commonmodel.ElementState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 19.10.2015.
 */
public class SensorFrame {
    private int cycle;
    private List<ElementState> elementStates = new ArrayList<>();

    public SensorFrame(int cycle){
        this.cycle = cycle;
    }

    public void addObservableObject(ElementState elementState){
        elementStates.add(elementState);
    }

    public List<ElementState> getElementStates() {
        return elementStates;
    }

    public int getCycle() {
        return cycle;
    }

}
