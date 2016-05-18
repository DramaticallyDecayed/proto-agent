package dd.sas.computation;

import dd.sas.presentation.WorldEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 18.05.2016.
 */
public abstract class Node implements Processable{

    private Level level;
    private List<Node> donors = new ArrayList<>();
    private List<WorldEntity> derivative = new ArrayList<>();

    public Node(Level level){
        this.level = level;
    }

    @Override
    abstract  public void process();

    public Level getLevel() {
        return level;
    }
}
