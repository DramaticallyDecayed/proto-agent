package dd.sas.computation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergey on 18.05.2016.
 */
public class Level {

    private int number;

    private Map<String, Node> nodeRegister = new HashMap<>();

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
        return "level_" + number;
    }

    public void addNode(Node node){
        nodeRegister.put(node.name(), node);
    }

    public Node retrieveNode(String nodeName){
        return nodeRegister.get(nodeName);
    }

}
