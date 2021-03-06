package dd.soccer;

import common.Publisher;
import commonmodel.ElementState;
import dd.protoperception.SensorFrame;
import dd.protosas.SAS;
import dd.soccer.calculationdependencies.ManualCalculationDependencyGenerator;
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

        ManualCalculationDependencyGenerator.initialize(perceptor, sas.getBaseLevel());

        perceptor.start();
        try{
            while(true){
                List<SensorFrame> list = perceptor.cycle();
                if(list != null){


                    for(SensorFrame ef : list){
                        for(ElementState es : ef.getElementStates()){
                            Publisher publisher = perceptor.getPublisher();
                            publisher.setPost(es);
                            publisher.detailedPublish(es.getRepresentDependecy());
                        }
                    }


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
