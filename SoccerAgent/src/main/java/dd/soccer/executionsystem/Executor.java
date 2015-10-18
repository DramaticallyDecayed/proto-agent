package dd.soccer.executionsystem;

import dd.soccer.perception.networking.Communicator;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Sergey on 14.10.2015.
 */
public class Executor {
    private Logger logger = Logger.getLogger(Executor.class.getName());
    private boolean hasCommands = true;

    private CommandGenerator commandGenerator;

    public Executor(Communicator communicator) throws FileNotFoundException, InterruptedException {
        commandGenerator = new CommandGenerator(communicator);
        commandGenerator.generateCommand();
        commandGenerator.generateCommand();
    }

    public void cycle() throws FileNotFoundException, InterruptedException {
        if(!hasCommands){
            return;
        }

        if(!commandGenerator.generateCommand()){
            logger.log(Level.INFO, "No more commands!");
            hasCommands = false;
        }
    }
}
