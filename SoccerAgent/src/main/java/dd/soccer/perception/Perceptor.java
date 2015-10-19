package dd.soccer.perception;

import dd.soccer.perception.messageprocessing.MessageUnmarshaller;
import dd.soccer.perception.messageprocessing.MessageUnmarshallerDispatcher;
import dd.soccer.perception.networking.Communicator;

import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Sergey on 14.10.2015.
 */
public class Perceptor {

    private Communicator communicator;
    private Thread t;

    public Perceptor() throws SocketException, UnknownHostException {
        communicator = new Communicator();
    }

    public void cycle() throws NoCommunicationException {
        if (t.isAlive()) {
            String inputMessage = communicator.getInputMessage();
            if (inputMessage != null) {
                MessageUnmarshallerDispatcher.unmarshal(inputMessage);
                /*
                There is should be some code that transmit sensor frames to SAS.
                Is sensor frame should be split on lists of objects of different types?
                 */
            }
        } else {
            throw new NoCommunicationException();
        }
    }

    public void start(){
        t = new Thread(communicator);
        t.start();
    }

    public Communicator getCommunicator() {
        return communicator;
    }
}
