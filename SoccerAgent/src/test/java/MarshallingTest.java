import dd.soccer.perception.messageprocessing.MessageUnmarshallerDispatcher;
import org.junit.Test;

import java.util.Scanner;

/**
 * Created by Sergey on 14.10.2015.
 */
public class MarshallingTest {
    private Scanner scanner;
    

    @Test
    public void marshallingMessageTest() {
        String message = "(see 0 ((flag c) 57.4 -31) ((flag c b) 49.4 4) ((flag r t) 120.3 -32) ((flag r b) 101.5 2) ((flag g r b) 104.6 -12) ((goal r) 105.6 -16) ((flag g r t) 107.8 -20) ((flag p r b) 85.6 -6) ((flag p r c) 90 -19) ((flag p r t) 98.5 -30) ((flag p l b) 16.3 -37 0 0) ((ball) 60.3 -31) ((line r) 101.5 89))";
        MessageUnmarshallerDispatcher.unmarshal(message);

    }



}
