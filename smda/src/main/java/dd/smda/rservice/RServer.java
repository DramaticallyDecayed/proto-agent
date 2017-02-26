package dd.smda.rservice;

import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

/**
 * Created by Sergey on 26.02.2017.
 */
public class RServer {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 6311;
    private RConnection connection;

    public void start() throws RserveException {
        connection = new RConnection(HOST, PORT);
    }



    public void stop(){
        if (connection != null && connection.isConnected()) {
            connection.close();
        }
    }


    public static String getPathToFunction(String fileName) {
        String path = RServer.class.getClassLoader().getResource(fileName).getPath();
        path = path.replace("/", "\\\\").replaceFirst("\\\\\\\\", "");
        return path;
    }

    public RConnection getConnection(){
        return connection;
    }
}
