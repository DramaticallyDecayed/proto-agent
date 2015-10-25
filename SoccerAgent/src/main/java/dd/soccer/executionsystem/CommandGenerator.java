package dd.soccer.executionsystem;

import dd.soccer.perception.networking.Communicator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sergey on 13.10.2015.
 */
public class CommandGenerator {

    private Communicator communicator;
    private Scanner commandsReader;

    public CommandGenerator(Communicator communicator, String commandFile) throws FileNotFoundException {
        this.communicator = communicator;
        File commands = new File(commandFile);
        commandsReader = new Scanner(commands);
    }

    public boolean generateCommand() throws FileNotFoundException, InterruptedException {
        if (commandsReader.hasNext()) {
            String command = commandsReader.nextLine();
            int p = command.indexOf("#");
            if (p != -1) {
                long sleepTime = Integer.parseInt(command.substring(++p));
                TimeUnit.MILLISECONDS.sleep(sleepTime);
            } else {
                communicator.addOutputMessage(command);
            }
            return true;
        }
        return false;
    }
}
