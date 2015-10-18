package dd.soccer.perception.networking;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Sergey on 13.10.2015.
 */
public class NetworkGate {
    private final int port;
    private final String address;
    private final InetAddress ipAddress;
    private final DatagramSocket socket;

    public NetworkGate(int port, String address) throws UnknownHostException, SocketException {
        this.port = port;
        this.address = address;
        ipAddress = InetAddress.getByName(address);
        socket = new DatagramSocket();
        socket.setSoTimeout(1000);
    }

    public NetworkGate() throws SocketException, UnknownHostException {
        this(6000, "127.0.0.1");
    }


    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public int getPort(){
        return port;
    }

    public void closeGate(){
        socket.close();
    }


}
