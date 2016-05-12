package dd.soccer.perception.messageprocessing;

import dd.protoperception.SensorFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Sergey on 18.10.2015.
 */
public class MessageUnmarshallerDispatcher{

    public static List<SensorFrame> unmarshal(String message) {
        List<SensorFrame> sensorFrames = new ArrayList<>();
        Scanner scanner = new Scanner(message);
        scanner = scanner.skip(Pattern.compile("\\(+"));
        String messageType = scanner.next();
        if(messageType.equals("see")){
            sensorFrames.add((new SeeMessageUnmarshaller()).unmarshalMessage(scanner));
        } else if(messageType.equals("sense_body")){
            sensorFrames.add((new SenseBodyMessageUnmarshaller()).unmarshalMessage(scanner));
        }
        if(sensorFrames == null){
            System.out.println("!!!!!!!!!!!!!!!!!!");
        }
        return sensorFrames;
    }
}
