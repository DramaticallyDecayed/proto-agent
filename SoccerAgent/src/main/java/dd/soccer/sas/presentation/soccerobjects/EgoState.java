package dd.soccer.sas.presentation.soccerobjects;

import dd.soccer.perception.perceptingobjects.BodyState;

/**
 * Created by Sergey on 30.10.2015.
 */
public class EgoState extends BodyState{


    /*
    TODO: think about this problem - problem of state copying. Also the relative problem is the problem of exending class (object) with additional property
     */

    public EgoState(BodyState bodyState){
        this.setSpeed(bodyState.getSpeed());
        this.setStamina(bodyState.getStamina());
        this.setEffort(bodyState.getEffort());
        this.setViewQuality(bodyState.getViewQuality());
        this.setViewWidth(bodyState.getViewWidth());
    }
}
