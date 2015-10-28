import commonmodel.ElementState;
import dd.protoperception.SensorFrame;
import dd.soccer.perception.messageprocessing.MessageUnmarshallerDispatcher;
import dd.soccer.perception.perceptingobjects.Line;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Sergey on 14.10.2015.
 */
public class MarshallingTest {
    private Scanner scanner;
    

    @Test
    public void marshallingMessageTest() {
        String message = "(see 0 " +
                "((flag c) 14 -3 0 0) " +
                "((flag l t) 74.4 23) " +
                "((flag l b) 74.4 -31) " +
                "((flag g l b) 66.7 -9) " +
                "((goal l) 66.7 -3) " +
                "((flag g l t) 66.7 2) " +
                "((flag p l b) 54.1 -25) " +
                "((flag p l c) 49.9 -3) " +
                "((flag p l t) 54.1 18) " +
                "((ball) 13.5 -3 0 0) " +
                "((player foofoe 3) 16.4 -11 0 0) " +
                "((line l) 66.7 86))";
        List<SensorFrame> sensorFrameList = MessageUnmarshallerDispatcher.unmarshal(message);
        for(ElementState es : sensorFrameList.get(0).getElementStates()){
            System.out.println(es);
        }


        //(sense_body 27 (view_mode high normal) (stamina 7980 1) (speed 0.18) (kick 0) (dash 4) (turn 1) (say 0))

        message = "(sense_body 0 " +
                "(view_mode high normal) " +
                "(stamina 8000 1) " +
                "(speed 0) " +
                "(kick 0) " +
                "(dash 0) " +
                "(turn 1) " +
                "(say 0))";
        sensorFrameList = MessageUnmarshallerDispatcher.unmarshal(message);
    }



}
