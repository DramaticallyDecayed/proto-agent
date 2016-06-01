package dd.soccer;

import commonmodel.ElementState;
import dd.ontologypart.OntologyHandler;
import dd.protoperception.SensorFrame;
import dd.sas.SAS;
import dd.soccer.executionsystem.Executor;
import dd.soccer.perception.NoCommunicationException;
import dd.soccer.perception.Perceptor;
import dd.soccer.perception.perceptingobjects.*;
import dd.soccer.sas.Perceptor2SASAdapterImpl;
import dd.soccer.sas.worldentity.CoordinateCenter;
import dd.soccer.sas.worldentity.CoordinateCenterC;

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

    public static void setLoggingLevel(ch.qos.logback.classic.Level level) {
        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        root.setLevel(level);
    }

    public static void main(String... args) throws SocketException, UnknownHostException, FileNotFoundException, InterruptedException {

        setLoggingLevel(ch.qos.logback.classic.Level.OFF);
//        LevelHolder levelHolder = new LevelHolder();
//        LevelNodeInitializer.init(levelHolder);
//        LightweightLevel level_1 = levelHolder.getLevel(0);
//        Node_cd_perception perceptionNode = (Node_cd_perception) level_1.getLightweightNode(Node_cd_perception.class);

        SAS sas = new SAS(new OntologyHandler());
        Perceptor2SASAdapterImpl adapter = initPerceptor2SASAdapter(sas);
        sas.cycle();
        initKnowObjects(adapter);

        Perceptor perceptor = new Perceptor();
        Executor executor = new Executor(perceptor.getCommunicator(), "commands");
        perceptor.start();


        ArrayList<Ball> ballList = new ArrayList<>();
        ArrayList<Line> lineList = new ArrayList<>();
        ArrayList<Flag> flagList = new ArrayList<>();
        ArrayList<Goal> goalList = new ArrayList<>();
        ArrayList<NavigatingLandmark> navigatingLandmarkList = new ArrayList<>();
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
                                //ballList.add((Ball) es);
                                adapter.setBall((Ball) es);
                            } else if (es instanceof Line) {
                                //lineList.add((Line) es);
                                navigatingLandmarkList.add((Line) es);
                            } else if (es instanceof Flag) {
                                //flagList.add((Flag) es);
                                navigatingLandmarkList.add((Flag) es);
                            } else if (es instanceof Flag) {
                                //goalList.add((Goal) es);
                                navigatingLandmarkList.add((Goal) es);
                            } else if (es instanceof Player) {
                                playerList.add((Player) es);
                            } else if (es instanceof BodyState) {
                                //bodyStateList.add((BodyState) es);
                                adapter.setBodystate((BodyState) es);
                            }
                        }


                        adapter.setNavigatingLandmarks(navigatingLandmarkList);

//                        perceptionNode.setBallList(ballList);
//                        perceptionNode.setFlagList(flagList);
//                        perceptionNode.setLineList(lineList);
//                        perceptionNode.setPlayerList(playerList);
//                        perceptionNode.setBodyStateList(bodyStateList);
//                        perceptionNode.setGoalList(goalList);
//                        System.out.println("------------------------------");
//                        System.out.println("balls: " + ballList.size());
//                        System.out.println("flags: " + flagList.size());
//                        System.out.println("lines: " + lineList.size());
//                        System.out.println("players: " + playerList.size());
//                        System.out.println("bodies: " + bodyStateList.size());
//                        System.out.println("goals: " + goalList.size());
//                        System.out.println("------------------------------");
                    }
                }
//                levelHolder.process();
                sas.cycle();
                executor.cycle();
            }
        } catch (NoCommunicationException e) {
            logger.log(Level.INFO, "Stop agent");
        }
    }

    private static void initKnowObjects(Perceptor2SASAdapterImpl adapter) {
        CoordinateCenter coordinateCenter = new CoordinateCenterC();
        coordinateCenter.setX(0.0);
        coordinateCenter.setY(0.0);
        adapter.setCoordinateCenter(coordinateCenter);
    }

    private static Perceptor2SASAdapterImpl initPerceptor2SASAdapter(SAS sas) {
        Perceptor2SASAdapterImpl adapter = new Perceptor2SASAdapterImpl();
        adapter.getLevel().addNode(adapter);
        sas.getLevelHolder().addLevel(adapter.getLevel());
        return adapter;
    }
}
