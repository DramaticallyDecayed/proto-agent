package dd.protosas.computation;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is created for very simple prototype used for checking the idea of owl to sas program
 * structure translation. It should be substituted with normal Level class in some future
 * <p>
 * Created by Sergey on 18.01.2016.
 */
public class LightweightLevel {
    private Integer levelNumber;
    private List<LightweightNode> nodeList = new ArrayList<>();

    public void addLightweightNode(LightweightNode node){
        nodeList.add(node);
    }
}
