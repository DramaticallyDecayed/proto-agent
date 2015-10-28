package dd.protosas.computation.levelnode;

import dd.protosas.computability.NodeSpecification;
import dd.protosas.presentation.ElementIdent;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Sergey on 09.10.2015.
 */
public class NodeRegister {
    /*current base used to produce derivative; updated when change notified*/
    private TreeMap<String, List<ElementIdent>> baseInput = new TreeMap<>();
    private List<ElementIdent> children = new ArrayList<>();
    private NodeSpecification nodeSpec;
    private int baseCapacity;

    public void initialize(NodeSpecification nodeSpec) {
        this.nodeSpec = nodeSpec;
        //TODO: should we initiate all keys in advance or wait for real data?
        for (String base : nodeSpec.getBase()) {
            baseInput.put(base, null);
        }
        baseCapacity = nodeSpec.getBase().length;
    }

    public List<ElementIdent> getChildren(){
        return children;
    }

    public void addChild(ElementIdent child){
        children.add(child);
    }

    public TreeMap<String, List<ElementIdent>> getBaseInput(){
        return baseInput;
    }


    //TODO: this method should not called after it first success until the base damage
    public boolean isComplete() {
        return baseCapacity == 0;
    }

    public boolean haveToBeUpdated() {
        return isComplete() && !children.isEmpty();
    }

    public void update(ElementIdent ident) {
        if(baseInput.get(ident.getName()) == null){
            baseInput.put(ident.getName(), new ArrayList<ElementIdent>());
            baseCapacity--;
        }
        baseInput.get(ident.getName()).add(ident);
    }
}
