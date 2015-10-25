package dd.soccer;

import dd.soccer.executionsystem.Executor;
import dd.soccer.perception.Perceptor;

import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Sergey on 25.10.2015.
 */
public class DymmyAgent {

    public static void main(String...args) throws SocketException, UnknownHostException, FileNotFoundException, InterruptedException {

        Perceptor perceptor = new Perceptor();
        Executor executor = new Executor(perceptor.getCommunicator(),"dymmycommands");
        perceptor.start();

    }
}
