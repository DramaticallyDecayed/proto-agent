package dd.soccer.perception.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketTimeoutException;

/**
 * Created by Sergey on 13.10.2015.
 */
public class PacketInterchanger {
    private static final int MSG_SIZE = 4096;
    private NetworkGate gate;

    public PacketInterchanger(NetworkGate gate) {
        this.gate = gate;
    }

    public void send(String msg) throws IOException {
        byte[] buf = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, gate.getIpAddress(), gate.getPort());
        gate.getSocket().send(packet);
    }

    public String receive() throws IOException {
        try {
            byte[] buf = new byte[MSG_SIZE];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            gate.getSocket().receive(packet);
            return new String(packet.getData(), 0, packet.getLength());
        } catch (SocketTimeoutException e) {
            throw e;
        }
    }
}
