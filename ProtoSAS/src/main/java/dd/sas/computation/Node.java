package dd.sas.computation;

import dd.sas.Utils;
import dd.sas.presentation.WorldEntity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergey on 18.05.2016.
 */
public abstract class Node implements Processable, Activable {

    private DerivativeActivable activator;
    private Map<Class, List<WrappingGetter>> donorMap = new HashMap<>();
    private Map<Class, List<? extends WorldEntity>> baseMap = new HashMap<>();
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
        dropDerivative();
        pullData();
        CalculationResult result  = customProcess();
        if (result == CalculationResult.POSITIVE || result == CalculationResult.NEGATIVE) {
            activate();
        }
        dropBases();
    }

    public void activateNode() {
        level.addNodeToBeActivated(this);
    }

    public void processNode() {
        level.addNodeToBeProcessed(this);
    }

    public void subscribe(Node node) {
        subscribers.add(node);
    }

    @Override
    public void activate() {
        activator.activateDerivative();
    }

    public void pullData() {
        for (Class c : donorMap.keySet()) {
            for (WrappingGetter wg : donorMap.get(c)) {
                fillBases(c, wg);
            }
        }
    }

    public void fillDonor(Class c, WrappingGetter wg) {
        if (donorMap.get(c) == null) {
            donorMap.put(c, new ArrayList<>());
        }
        donorMap.get(c).add(wg);
    }

    public void fillBases(Class c, WrappingGetter wg) {
        if (baseMap.get(c) == null) {
            baseMap.put(c, new ArrayList<>());
        }
        baseMap.get(c).addAll(wg.getObjectList());
    }

    public void dropBases(){
        baseMap.values().forEach(List::clear);
    }

    abstract public void dropDerivative();

    abstract public CalculationResult customProcess();

    abstract public String name();

    public Level getLevel() {
        return level;
    }

    public void setActivator(DerivativeActivable activator) {
        this.activator = activator;
    }

    public List<Node> getSubscribers() {
        return subscribers;
    }

    public abstract void pushAsDonor(Node acceptor);

    public void setDonor(Node donor, Class donorClass) {
        try {
            Method method = this.getClass().getMethod("set" + Utils.makeFirsLetterUp(donor.name()), new Class[]{donorClass});
            method.invoke(this, donor);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Map<Class, List<WrappingGetter>> getDonorMap() {
        return donorMap;
    }

    public Map<Class, List<? extends WorldEntity>> getBaseMap() {
        return baseMap;
    }

}
