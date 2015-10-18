package dd.soccer;

import dd.soccer.executionsystem.Executor;
import dd.soccer.perception.NoCommunicationException;
import dd.soccer.perception.Perceptor;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Sergey on 13.10.2015.
 */

/*
(hear 0 referee drop_ball) 
(hear 0 referee play_on) 
 */
public class Agent {
    private static Logger logger = Logger.getLogger(Agent.class.getName());

    public static void main(String... args) throws IOException, InterruptedException {

        Perceptor perceptor = new Perceptor();
        Executor executor = new Executor(perceptor.getCommunicator());
        perceptor.start();

        try{
            while(true){
                perceptor.cycle();
                executor.cycle();
            }
        }catch (NoCommunicationException e){
            logger.log(Level.INFO,"Stop agent");
        }

    }
}
