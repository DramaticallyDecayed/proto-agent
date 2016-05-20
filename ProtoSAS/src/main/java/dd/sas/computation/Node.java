package dd.sas.computation;

/**
 * Created by Sergey on 18.05.2016.
 */
public abstract class Node implements Processable {

    private Level level;

    public Node(Level level) {
        this.level = level;
    }

    @Override
    public void process() {
        pullData();
        dropDerivative();
        customProcess();
        buildDerivative();
    }

    abstract public void pullData();

    abstract public void dropDerivative();

    abstract public void customProcess();

    abstract public void buildDerivative();

    public Level getLevel() {
        return level;
    }
}
