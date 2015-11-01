package dd.protosas;


import commonmodel.ElementState;
import dd.protoperception.SensorFrame;
import dd.protosas.computation.Level;
import dd.protosas.presentation.ElementIdent;

import java.util.List;

/**
 * Created by Sergey on 23.10.2015.
 */
public class SAS extends ModelFragmentProcessor {

    public SAS(Level baseLevel) {
        super(baseLevel);
    }

    @Override
    public void cycle() {
        for(SensorFrame sf : (List<SensorFrame>)getPushedObjs()){
            for(ElementState es : sf.getElementStates()){
                ElementIdent ei = new ElementIdent(es);
                getBaseLevel().push(ei);
            }
        }
        getBaseLevel().process();
        getPushedObjs().clear();
    }

}
