package dd.sas.computation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 18.05.2016.
 */
public class Level {

    private int number;
    private List<Node> nodesToBeProcessed = new ArrayList<>();
    private List<Node> nodesToBeActivated = new ArrayList<>();

    public Level(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void addNodeToBeProcessed(Node node) {
        nodesToBeProcessed.add(node);
    }
    public void addNodeToBeActivated(Node node){
        nodesToBeActivated.add(node);
    }

    public List<Node> getNodesToBeActivated(){
        return nodesToBeActivated;
    }

    public List<Node> getNodesToBeProcessed(){
        return nodesToBeProcessed;
    }

    public String getName(){
        return "Level_" + number;
    }

}
