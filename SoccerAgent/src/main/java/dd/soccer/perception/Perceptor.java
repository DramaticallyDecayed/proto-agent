package dd.soccer.perception;

import dd.protoperception.SensorFrame;
import dd.soccer.perception.messageprocessing.MessageUnmarshallerDispatcher;
import dd.soccer.perception.networking.Communicator;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by Sergey on 14.10.2015.
 */
public class Perceptor {

    private Communicator communicator;
    private Thread t;

    public Perceptor() throws SocketException, UnknownHostException {
        communicator = new Communicator();
    }

    public List<SensorFrame> cycle() throws NoCommunicationException {
        if (t.isAlive()) {
            String inputMessage = communicator.getInputMessage();
            if (inputMessage != null) {
                return MessageUnmarshallerDispatcher.unmarshal(inputMessage);
            }
        } else {
            throw new NoCommunicationException();
        }
        return null;
    }

    public void start(){
        t = new Thread(communicator);
        t.start();
    }

    public Communicator getCommunicator() {
        return communicator;
    }
}