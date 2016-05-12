package dd.protosas.computation;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is created for very simple prototype used for checking the idea of owl to sas program
 * structure translation. It should be substituted with normal Level class in some future
 * <p>
 * Created by Sergey on 18.01.2016.
 */
public class LightweightLevel {
    private Integer levelNumber;
    private Map<Class, LightweightNode> nodeList = new HashMap<>();

    public LightweightLevel(Integer levelNumber){
        this.levelNumber = levelNumber;
    }

    public void addLightweightNode(LightweightNode node) {
        nodeList.put(node.getClass(), node);
    }

    public LightweightNode getLightweightNode(Class c){
        return nodeList.get(c);
    }

    public void process() {
        nodeList.values().forEach(LightweightNode::process);
    }
}
