package dd.sas.computation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 18.05.2016.
 */
public class Level implements Processable, Activable {

    private int number;
    private List<Node> nodesToBeProcessed = new ArrayList<>();
    private List<Node> nodesToBeActivated = new ArrayList<>();

    public Level(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void process() {
        for (Node node : nodesToBeProcessed) {
            node.process();
        }
    }

    @Override
    public void activate(){
        for (Node node : nodesToBeActivated) {
            node.activate();
        }
    }

    public void addNodeToBeProcessed(Node node) {
        nodesToBeProcessed.add(node);
    }
    public void addNodeToBeActivated(Node node){
        nodesToBeActivated.add(node);
    }

}
