package dd.soccer;

import dd.protoperception.SensorFrame;
import dd.protosas.SAS;
import dd.soccer.executionsystem.Executor;
import dd.soccer.perception.NoCommunicationException;
import dd.soccer.perception.Perceptor;
import dd.soccer.sas.computation.Level0Fabric;

import java.io.IOException;
import java.util.List;
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

        SAS sas = new SAS(Level0Fabric.createLevel0());

        Perceptor perceptor = new Perceptor();
        Executor executor = new Executor(perceptor.getCommunicator(), "commands");



        perceptor.start();
        try{
            while(true){
                List<SensorFrame> list = perceptor.cycle();
                if(list != null){
                    sas.push(list);
                    sas.cycle();
                }
                executor.cycle();
            }
        }catch (NoCommunicationException e){
            logger.log(Level.INFO,"Stop agent");
        }

    }
}
