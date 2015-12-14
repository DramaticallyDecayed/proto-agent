package dd.soccer.sas.computation.egonodecomputation;

import commonmodel.ElementState;
import dd.protosas.ModelFragmentProcessor;
import dd.protosas.computation.Level;
import dd.protosas.presentation.ElementIdent;

/**
 * Created by Sergey on 01.11.2015.
 */
public class EgoModelProcessor extends ModelFragmentProcessor {

    private ElementState outstate;

    public EgoModelProcessor(Level baseLevel) {
        super(baseLevel);
    }

    public void cycle() {
        if (!getPushedObjs().isEmpty()) {
            outstate = (ElementState) getPushedObjs().get(0);
        }
        getPushedObjs().clear();
    }

    public ElementState getOutstate() {
        return outstate;
    }

}
