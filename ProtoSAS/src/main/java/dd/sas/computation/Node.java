package dd.sas.computation;

/**
 * Created by Sergey on 18.05.2016.
 */
public abstract class Node implements Processable {

    /**
     * Possible additions:
     * 1. Node is activated by other nodes
     * 2. Filter entities.
     * 2.1. For associative composite relation it is include finding intersected pairs of relations that forms a composite
     * 2.2. For associative composite relation that is adjustment of another relation that is also include finding intersection among domain and range of adjusted relation
     * 3. Translate properties (if is interpreted as mapping on scales) or set predicates
     * 4. Fix derivative
     */


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
