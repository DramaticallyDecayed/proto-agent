package dd.soccer.sas;

import dd.soccer.sas.worldentity.*;

import java.util.List;

/**
 * Created by Sergey on 30.05.2016.
 */
public class Adapter extends Perceptor2SASAdapter {


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
    public void pullData() {

    }

    @Override
    public void dropDerivative() {

    }

    @Override
    public Boolean customProcess() {
        return null;
    }

    @Override
    public String name() {
        return null;
    }
}
