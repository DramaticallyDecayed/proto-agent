package dd.soccer.sas;

import dd.sas.computation.Node;
import dd.soccer.sas.computation.node.Node_cu_Ball;
import dd.soccer.sas.computation.node.Node_cu_CoordinateCenter;
import dd.soccer.sas.worldentity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergey on 30.05.2016.
 */
public class Adapter extends Perceptor2SASAdapter {

    public final static String NAME = "perceptor2SASAdapter";

    private Map<String, Node> subscribers = new HashMap<>();

    private List<Ball> ballList;
    private List<CoordinateCenter> coordinateCenterList;

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

    @Override
    public List<Ball> getBallList() {
        return ballList;
    }

    @Override
    public List<Landmark> getLandmarkList() {
        return null;
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
        return null;
    }

    @Override
    public List<EgoTeam> getEgoTeamList() {
        return null;
    }

    @Override
    public List<RivalTeam> getRivalTeamList() {
        return null;
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
