package dd.sas.computation;

import java.util.*;

/**
 * Created by Sergey on 18.05.2016.
 */
public class Level {

    private int number;

    private Map<String, Node> nodeRegister = new HashMap<>();

    private Set<Node> nodesToBeProcessed = new HashSet<>();
    private Set<Node> nodesToBeActivated = new HashSet<>();

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

    public Set<Node> getNodesToBeActivated(){
        return nodesToBeActivated;
    }

    public Set<Node> getNodesToBeProcessed(){
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
