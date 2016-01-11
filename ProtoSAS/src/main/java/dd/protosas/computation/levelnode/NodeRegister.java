package dd.protosas.computation.levelnode;

import common.Dependency;
import commonmodel.ElementState;
import dd.protosas.computability.NodeSpecification;
import dd.protosas.presentation.ElementIdent;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * TODO: it can be implemented through concrete fields getters and setters on code generation
 * Created by Sergey on 09.10.2015.
 */
public class NodeRegister {

    /*current base used to produce derivative; updated when change notified*/
    private TreeMap<Dependency, List<ElementState>> baseInput = new TreeMap<>();
    private List<ElementState> derivative = new ArrayList<>();
    private int baseCapacity;

    public void initialize(NodeSpecification nodeSpec) {

        //TODO: should we initiate all keys in advance or wait for real data?
        for (Dependency base : nodeSpec.getBase()) {
            baseInput.put(base, null);
        }
        baseCapacity = nodeSpec.getBase().length;
    }

    public List<ElementState> getDerivative() {
        return derivative;
    }

    public void addDerivative(ElementState derivative) {
        this.derivative.add(derivative);
    }

    public TreeMap<Dependency, List<ElementState>> getBaseInput() {
        return baseInput;
    }


    //TODO: this method should not called after it first success until the base damage
    //TODO: here should be more comprehended check
    public boolean isComplete() {
        return baseCapacity == 0;
    }

    public boolean haveToBeUpdated() {
        return isComplete() && !derivative.isEmpty();
    }

    public void update(ElementState state) {
        if (baseInput.get(state.getRepresentDependecy()) == null) {
            baseInput.put(state.getRepresentDependecy(), new ArrayList<>());
            baseCapacity--;
        }
        baseInput.get(state.getRepresentDependecy()).add(state);
    }

    public void clearResterRecord() {
        baseInput.values().forEach(java.util.List::clear);
    }
}
