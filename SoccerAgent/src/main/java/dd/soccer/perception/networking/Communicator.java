package dd.soccer.perception.networking;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Sergey on 13.10.2015.
 */
public class Communicator implements Runnable {

    private Logger logger = Logger.getLogger(Communicator.class.getName());

    private Queue<String> outputMessageQ = new LinkedBlockingQueue<>();
    private Queue<String> inputMessageQ = new LinkedBlockingQueue<>();
    private NetworkGate gate;
    private PacketInterchanger packetInterchanger;

    public Communicator() throws SocketException, UnknownHostException {
        gate = new NetworkGate();
        packetInterchanger = new PacketInterchanger(gate);
    }

    public void run() {
        try {
            while (true) {
                sendMessage();
                if (!receiveMessage()) {
                    break;
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Stop communicator thread because of errors: ", e);
        }
        gate.closeGate();
    }

    private void sendMessage() throws IOException {
        while (!outputMessageQ.isEmpty()) {
            String message = outputMessageQ.poll();
            packetInterchanger.send(message);
        }
    }

    private boolean receiveMessage() throws IOException {
        try {
            String message = packetInterchanger.receive();
            inputMessageQ.add(message);
            //System.out.println("SIZE OF QUEUE: " + inputMessageQ.size());
            return true;
        } catch (SocketTimeoutException e) {
            logger.log(Level.INFO, "timeout expires -> no server activity -> go out");
            return false;
        }
    }

    public void addOutputMessage(String str) {
        outputMessageQ.add(str);
    }

    public String getInputMessage() {
        return inputMessageQ.poll();
    }
}
