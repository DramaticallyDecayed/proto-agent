package dd.soccer.sas.presentation.entities;

import commonmodel.ElementState;
import dd.soccer.perception.perceptingobjects.Flag;
import dd.soccer.perception.perceptingobjects.Goal;
import dd.soccer.perception.perceptingobjects.Line;
import dd.soccer.perception.perceptingobjects.NavigatingLandmark;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 19.01.2016.
 */
public class SeeLandmark extends ElementState {
    private Ego ego;

    private List<Goal> goalList = new ArrayList<>();
    private List<Flag> flagList = new ArrayList<>();
    private List<Line> lineList = new ArrayList<>();



    public Ego getEgo() {
        return ego;
    }

    public void setEgo(Ego ego) {
        this.ego = ego;
    }

    public List<Goal> getGoalList() {
        return goalList;
    }

    public void setGoalList(List<Goal> goalList) {
        this.goalList = goalList;
    }

    public List<Flag> getFlagList() {
        return flagList;
    }

    public void setFlagList(List<Flag> flagList) {
        this.flagList = flagList;
    }

    public List<Line> getLineList() {
        return lineList;
    }

    public void setLineList(List<Line> lineList) {
        this.lineList = lineList;
    }
}
