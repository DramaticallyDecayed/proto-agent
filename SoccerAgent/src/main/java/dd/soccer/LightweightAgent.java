package dd.soccer;

import commonmodel.ElementState;
import dd.protoperception.SensorFrame;
import dd.protosas.computation.LevelHolder;
import dd.protosas.computation.LightweightLevel;
import dd.soccer.executionsystem.Executor;
import dd.soccer.perception.NoCommunicationException;
import dd.soccer.perception.Perceptor;
import dd.soccer.perception.perceptingobjects.Ball;
import dd.soccer.sas.computation.generated.LevelNodeInitializer;
import dd.soccer.sas.computation.generated.Node_cd_perception;

import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Sergey on 20.01.2016.
 */
public class LightweightAgent {
    private static Logger logger = Logger.getLogger(LightweightAgent.class.getName());

    public static void main(String...args) throws SocketException, UnknownHostException, FileNotFoundException, InterruptedException {

        LevelHolder levelHolder = new LevelHolder();
        LevelNodeInitializer.init(levelHolder);
        LightweightLevel level_1 = levelHolder.getLevel(0);
        Node_cd_perception perceptionNode = (Node_cd_perception) level_1.getLightweightNode(Node_cd_perception.class);
        levelHolder.process();

        Perceptor perceptor = new Perceptor();
        Executor executor = new Executor(perceptor.getCommunicator(), "commands");
        perceptor.start();
        try{
            while(true){
                List<SensorFrame> list = perceptor.cycle();
                if(list != null){
                    for(SensorFrame ef : list){
                        for(ElementState es : ef.getElementStates()){
                            System.out.println(es);
                            if(es instanceof Ball){
                                ArrayList<Ball> ballList = new ArrayList<>();
                                ballList.add((Ball) es);
                                perceptionNode.setBallList(ballList);
                            }
                        }
                    }
                }
                executor.cycle();
            }
        }catch (NoCommunicationException e){
            logger.log(Level.INFO,"Stop agent");
        }
    }
}
