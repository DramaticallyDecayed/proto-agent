package dd.sas.computation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 18.05.2016.
 */
public abstract class Node implements Processable, Activable {

    private DerivativeActivable activator;
    private List<Node> subscribers = new ArrayList<>();

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
        activator = new OntologyActivator(this);
    }

    @Override
    public void process() {
        pullData();
        dropDerivative();
        if(customProcess()) {
            activate();
        }
    }

    public void activateNode(){
        level.addNodeToBeActivated(this);
    }

    public void processNode(){
        level.addNodeToBeProcessed(this);
    }

    public void subscribe(Node node){
        subscribers.add(node);
    }

    @Override
    public void activate(){
        activator.activateDerivative();
    }

    abstract public void pullData();

    abstract public void dropDerivative();

    abstract public Boolean customProcess();

    abstract public String name();

    public Level getLevel() {
        return level;
    }

    public void setActivator(DerivativeActivable activator){
        this.activator = activator;
    }

    public List<Node> getSubscribers(){
        return subscribers;
    }
}
