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
    private TreeMap<String, List<ElementIdent>> baseInput = new TreeMap<String, List<ElementIdent>>();
    private List<ElementIdent> children = new ArrayList<>();
    private NodeSpecification nodeSpec;


    public void initialize(NodeSpecification nodeSpec) {
        this.nodeSpec = nodeSpec;
        for (String base : nodeSpec.getBase()) {
            baseInput.put(base, null);
        }
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

    public boolean isComplete() {
        return baseInput.values().size() >= nodeSpec.base.length;
    }

    public boolean haveToBeUpdated() {
        return isComplete() && !children.isEmpty();
    }

    public void update(ElementIdent ident) {
        if(baseInput.get(ident.getName()) == null){
            baseInput.put(ident.getName(), new ArrayList<ElementIdent>());
        }
        baseInput.get(ident.getName()).add(ident);
    }
}
