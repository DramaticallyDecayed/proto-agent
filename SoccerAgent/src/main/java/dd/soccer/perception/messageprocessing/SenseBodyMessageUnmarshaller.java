package dd.soccer.perception.messageprocessing;

import dd.soccer.perception.perceptingobjects.BodyState;

/**
 * Created by Sergey on 28.10.2015.
 */
public class SenseBodyMessageUnmarshaller extends MessageUnmarshaller{
    @Override
    protected void unmarshal() {
        BodyState egoState = new BodyState();
        while (hasNextElement()) {
            String parameterName = getSeeElement();
            if(parameterName.equals("stamina")){
                String[] params = getElementParams().split(" ");
                egoState.setStamina(Integer.parseInt(params[0]));
                egoState.setEffort(Integer.parseInt(params[1]));
            } else if(parameterName.equals("view_mode")){
                String[] params = getElementParams().split(" ");
                egoState.setViewQuality(params[0]);
                egoState.setViewWidth(params[1]);
            }else if(parameterName.equals("speed")){
                egoState.setSpeed(Double.parseDouble(getElementParams()));
            }else{
                getElementParams();
            }
        }
        getSensorFrame().addObservableObject(egoState);
    }
}
