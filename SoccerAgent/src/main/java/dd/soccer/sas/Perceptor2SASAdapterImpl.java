package dd.soccer.sas;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Node;
import dd.soccer.perception.perceptingobjects.*;
import dd.soccer.sas.computation.node.*;
import dd.soccer.sas.worldentity.*;
import dd.soccer.sas.worldentity.Ball;
import dd.soccer.sas.worldentity.Flag;
import dd.soccer.sas.worldentity.Goal;
import dd.soccer.sas.worldentity.Line;
import dd.soccer.sas.worldentity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergey on 30.05.2016.
 */
public class Perceptor2SASAdapterImpl extends Perceptor2SASAdapter {

    public final static String NAME = "perceptor2SASAdapter";

    private Map<String, Node> subscribers = new HashMap<>();

    private List<Ball> ballList;
    private List<CoordinateCenter> coordinateCenterList;
    private List<Landmark> landmarkList;
    private List<Ego> egoList;
    private List<EgoTeam> egoTeamList;
    private List<RivalTeam> rivalTeamList;
    private List<Goal> goalList;
    private List<Player> playerList;

    public void setBall(dd.soccer.perception.perceptingobjects.Ball ball) {
        Ball sasBall = new BallC();
        sasBall.setDirection(ball.getDirection());
        sasBall.setDistance(ball.getDistance());
        ballList = new ArrayList<>(1);
        ballList.add(sasBall);
        subscribers.get(Node_cu_Ball.NAME).processNode();
    }

    public void setCoordinateCenter(CoordinateCenter coordinateCenter) {
        coordinateCenterList = new ArrayList<>(1);
        coordinateCenterList.add(coordinateCenter);
        if (subscribers.get(Node_cu_CoordinateCenter.NAME) != null) {
            subscribers.get(Node_cu_CoordinateCenter.NAME).processNode();
        }
    }

    public void setNavigatingLandmarks(List<NavigatingLandmark> landmarks) {
        landmarkList = new ArrayList<>();
        for (NavigatingLandmark landmark : landmarks) {
            Landmark sasLandmark = new LandmarkC();
            sasLandmark.setDirection(landmark.getDirection());
            sasLandmark.setDistance(landmark.getDistance());
            sasLandmark.setX(landmark.getX());
            sasLandmark.setY(landmark.getY());
            sasLandmark.setType(landmark.getType());
            sasLandmark.setSpecialType(landmark.getSpecialType());
            landmarkList.add(sasLandmark);
        }
        if (subscribers.get(Node_cu_Landmark.NAME) != null) {
            subscribers.get(Node_cu_Landmark.NAME).processNode();
        }
    }

    public void setBodystate(BodyState bodyState) {
        Ego ego = new EgoC();
        egoList = new ArrayList<>();
        egoList.add(ego);
        if (subscribers.get(Node_cu_Ego.NAME) != null) {
            subscribers.get(Node_cu_Ego.NAME).processNode();
        }
    }

    public void setEgoTeam(EgoTeam egoTeam) {
        egoTeamList = new ArrayList<>(1);
        egoTeamList.add(egoTeam);
        subscribers.get(Node_cu_EgoTeam.NAME).processNode();
    }

    public void setRivalTeam(RivalTeam rivalTeam) {
        rivalTeamList = new ArrayList<>(1);
        rivalTeamList.add(rivalTeam);
        subscribers.get(Node_cu_RivalTeam.NAME).processNode();
    }

    public void setGoalList(dd.soccer.perception.perceptingobjects.Goal goal) {
        Goal sasGoal = new GoalC();
        sasGoal.setX(goal.getX());
        sasGoal.setY(goal.getY());
        sasGoal.setDistance(goal.getDistance());
        sasGoal.setDirection(goal.getDirection());
        sasGoal.setType(goal.getType());
        sasGoal.setSpecialType(goal.getSpecialType());
        goalList = new ArrayList<>();
        goalList.add(sasGoal);
        //subscribers.get(Node_cu_.NAME).processNode();
    }

    public void setPlayerList(List<dd.soccer.perception.perceptingobjects.Player> players) {
        if (!players.isEmpty()) {
            playerList = new ArrayList<>();
            for (dd.soccer.perception.perceptingobjects.Player player : players) {
                Player sasPlayer = new PlayerC();
                sasPlayer.setDistance(player.getDistance());
                sasPlayer.setDirection(player.getDirection());
                playerList.add(sasPlayer);
            }
            if (subscribers.get(Node_cu_Player.NAME) != null) {
                subscribers.get(Node_cu_Player.NAME).processNode();
            }
        }
    }

    @Override
    public List<Ball> getBallList() {
        return ballList;
    }

    @Override
    public List<Landmark> getLandmarkList() {
        return landmarkList;
    }

    @Override
    public List<Line> getLineList() {
        return null;
    }

    @Override
    public List<Flag> getFlagList() {
        return null;
    }

    @Override
    public List<Goal> getGoalList() {
        return null;
    }

    @Override
    public List<Player> getPlayerList() {
        return playerList;
    }

    @Override
    public List<Ego> getEgoList() {
        return egoList;
    }

    @Override
    public List<EgoTeam> getEgoTeamList() {
        return egoTeamList;
    }

    @Override
    public List<RivalTeam> getRivalTeamList() {
        return rivalTeamList;
    }

    @Override
    public List<CoordinateCenter> getCoordinateCenterList() {
        return coordinateCenterList;
    }

    @Override
    public void subscribe(Node node) {
        subscribers.put(node.name(), node);
    }

    @Override
    public void pullData() {

    }

    @Override
    public void dropDerivative() {

    }

    @Override
    public CalculationResult customProcess() {
        return CalculationResult.UNKNOWN;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public void pushAsDonor(Node acceptor) {

    }
}
