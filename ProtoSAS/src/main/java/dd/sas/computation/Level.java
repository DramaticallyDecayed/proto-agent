package dd.sas.computation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 18.05.2016.
 */
public class Level implements Processable {

    private int number;
    private List<Node> nodes = new ArrayList<>();

    public Level(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void process() {
        for (Node node : nodes) {
            node.process();
        }
    }

    public void addNode(Node node) {
        nodes.add(node);
    }
}
