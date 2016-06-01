package dd.soccer.sas;

import dd.sas.computation.Node;
import dd.soccer.perception.perceptingobjects.BodyState;
import dd.soccer.perception.perceptingobjects.NavigatingLandmark;
import dd.soccer.sas.computation.node.*;
import dd.soccer.sas.worldentity.*;

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

    public void setBall(dd.soccer.perception.perceptingobjects.Ball ball){
        Ball sasBall = new BallC();
        sasBall.setDirection(ball.getDirection());
        sasBall.setDistance(ball.getDistance());
        ballList = new ArrayList<>(1);
        ballList.add(sasBall);
        subscribers.get(Node_cu_Ball.NAME).processNode();
    }

    public void setCoordinateCenter(CoordinateCenter coordinateCenter){
        coordinateCenterList = new ArrayList<>(1);
        coordinateCenterList.add(coordinateCenter);
        subscribers.get(Node_cu_CoordinateCenter.NAME).processNode();
    }

    public void setNavigatingLandmarks(List<NavigatingLandmark> landmarks){
        landmarkList = new ArrayList<>();
        for(NavigatingLandmark landmark : landmarks) {
            Landmark sasLandmark = new LandmarkC();
            sasLandmark.setDirection(landmark.getDirection());
            sasLandmark.setDistance(landmark.getDistance());
            sasLandmark.setX(landmark.getX());
            sasLandmark.setY(landmark.getY());
            landmarkList.add(sasLandmark);
        }
        subscribers.get(Node_cu_Landmark.NAME).processNode();
    }

    public void setBodystate(BodyState bodyState){
        Ego ego = new EgoC();
        egoList = new ArrayList<>();
        egoList.add(ego);
        subscribers.get(Node_cu_Ego.NAME).processNode();
    }

    public void setEgoTeam(EgoTeam egoTeam){
        egoTeamList = new ArrayList<>(1);
        egoTeamList.add(egoTeam);
        subscribers.get(Node_cu_EgoTeam.NAME).processNode();
    }

    public void setRivalTeam(RivalTeam rivalTeam){
        rivalTeamList = new ArrayList<>(1);
        rivalTeamList.add(rivalTeam);
        subscribers.get(Node_cu_RivalTeam.NAME).processNode();
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
        return null;
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
    public Boolean customProcess() {
        return false;
    }

    @Override
    public String name() {
        return NAME;
    }
}
