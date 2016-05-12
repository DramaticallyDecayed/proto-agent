package dd.soccer;

import commonmodel.ElementState;
import dd.protoperception.SensorFrame;
import dd.protosas.computation.LevelHolder;
import dd.protosas.computation.LightweightLevel;
import dd.soccer.executionsystem.Executor;
import dd.soccer.perception.NoCommunicationException;
import dd.soccer.perception.Perceptor;
import dd.soccer.perception.perceptingobjects.*;
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

    public static void main(String... args) throws SocketException, UnknownHostException, FileNotFoundException, InterruptedException {

        LevelHolder levelHolder = new LevelHolder();
        LevelNodeInitializer.init(levelHolder);
        LightweightLevel level_1 = levelHolder.getLevel(0);
        Node_cd_perception perceptionNode = (Node_cd_perception) level_1.getLightweightNode(Node_cd_perception.class);


        Perceptor perceptor = new Perceptor();
        Executor executor = new Executor(perceptor.getCommunicator(), "commands");
        perceptor.start();


        ArrayList<Ball> ballList = new ArrayList<>();
        ArrayList<Line> lineList = new ArrayList<>();
        ArrayList<Flag> flagList = new ArrayList<>();
        ArrayList<Goal> goalList = new ArrayList<>();
        ArrayList<Player> playerList = new ArrayList<>();
        ArrayList<BodyState> bodyStateList = new ArrayList<>();

        try {
            while (true) {
                List<SensorFrame> list = perceptor.cycle();
                if (list != null) {
                    for (SensorFrame ef : list) {
                        ballList.clear();
                        lineList.clear();
                        flagList.clear();
                        goalList.clear();
                        playerList.clear();
                        bodyStateList.clear();
                        for (ElementState es : ef.getElementStates()) {
                            if (es instanceof Ball) {
                                ballList.add((Ball) es);
                            } else if (es instanceof Line) {
                                lineList.add((Line) es);
                            } else if (es instanceof Flag) {
                                flagList.add((Flag) es);
                            } else if (es instanceof Flag) {
                                goalList.add((Goal) es);
                            } else if (es instanceof Player) {
                                playerList.add((Player) es);
                            } else if (es instanceof BodyState) {
                                bodyStateList.add((BodyState) es);
                            }
                        }
                        perceptionNode.setBallList(ballList);
                        perceptionNode.setFlagList(flagList);
                        perceptionNode.setLineList(lineList);
                        perceptionNode.setPlayerList(playerList);
                        perceptionNode.setBodyStateList(bodyStateList);
                        perceptionNode.setGoalList(goalList);
//                        System.out.println("------------------------------");
//                        System.out.println(ballList.size());
//                        System.out.println(flagList.size());
//                        System.out.println(lineList.size());
//                        System.out.println(playerList.size());
//                        System.out.println(bodyStateList.size());
//                        System.out.println(goalList.size());
//                        System.out.println("------------------------------");
                    }
                }
                levelHolder.process();
                executor.cycle();
            }
        } catch (NoCommunicationException e) {
            logger.log(Level.INFO, "Stop agent");
        }
    }
}
