package dd.soccer.perception.messageprocessing;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Sergey on 18.10.2015.
 */
public class MessageUnmarshallerDispatcher{

    public static void unmarshal(String message) {
        Scanner scanner = new Scanner(message);
        scanner = scanner.skip(Pattern.compile("\\(+"));
        String messageType = scanner.next();
        if(messageType.equals("see")){
            System.out.println("---------------------------------------------------------------");
            System.out.println(message);
            new SeeMessageUnmarshaller().unmarshalMessage(scanner);
        }
    }
}
