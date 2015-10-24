package dd.protosas;


import commonmodel.ElementState;
import dd.protoperception.SensorFrame;
import dd.protosas.computation.Level;

import dd.protosas.presentation.ElementIdent;

import java.util.List;

/**
 * Created by Sergey on 23.10.2015.
 */
public class SAS {

    private Level baseLevel;

    public SAS(Level baseLevel){
        this.baseLevel = baseLevel;
    }

    public void cycle(List<SensorFrame> list) {

        for(SensorFrame sf : list){
            for(ElementState es : sf.getElementStates()){
                ElementIdent ei = new ElementIdent(es);
            }
        }

    }
}
