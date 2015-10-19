package dd.soccer.perception.messageprocessing;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Sergey on 14.10.2015.
 */
public abstract class MessageUnmarshaller {
    private Scanner scanner;
    private SensorFrame sensorFrame;

    public SensorFrame unmarshalMessage(Scanner scanner){
        this.scanner = scanner;
        sensorFrame = new SensorFrame(Integer.parseInt(scanner.next()));
        unmarshal();
        return sensorFrame;
    }

    protected abstract void unmarshal();

    public String getSeeElement(){
        scanner = scanner.skip(Pattern.compile(".*?\\(+"));
        return scanner.findInLine(Pattern.compile("\\w+"));
    }

    public String getElementParams(){
        String type = scanner.findInLine(Pattern.compile("[\\w\\s\\-\\.]*"));
        scanner = scanner.skip(Pattern.compile("\\)+"));
        return type.trim();
    }

    public SensorFrame getSensorFrame() {
        return sensorFrame;
    }

    public boolean hasNextElement(){
        return scanner.hasNext();
    }
}
