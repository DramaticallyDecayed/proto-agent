package dd.soccer.sas;

import dd.sas.computation.Node;
import dd.soccer.sas.worldentity.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergey on 30.05.2016.
 */
public class Adapter extends Perceptor2SASAdapter {

    public final static String NAME = "perceptor2SASAdapter";

    private Map<String, Node> subscribers = new HashMap<>();

    @Override
    public List<Ball> getBallList() {
        return null;
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
