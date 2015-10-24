package dd.protosas.computability;

import dd.protosas.computation.levelnode.IdentNode;

/**
 * Created by Sergey on 25.09.2015.
 */
public abstract class NodeSpecification {

    public final String[] base;
    public final String derivative;
    private final int hash;

    public NodeSpecification(String[] base, String derivative) {
        this.base = base;
        this.derivative = derivative;
        hash = computeHash();
    }

    private int computeHash() {
        final int prime = 31;
        int result = 1;
        for (int i = 0; i < base.length; i++) {
            result = prime * result + base[i].hashCode();
        }
        result = prime * result + derivative.hashCode();
        return result;
    }

    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        NodeSpecification thatSpecification = (NodeSpecification) that;
        if (base.length != thatSpecification.base.length) {
            return false;
        }
        for (int i = 0; i < base.length; i++) {
            //so the order does matter
            if (!base[i].equals(thatSpecification.base[i])) {
                return false;
            }
        }
        if (!derivative.equals(thatSpecification.derivative)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return hash;
    }

    public String[] getBase(){
        return base;
    }

    public abstract IdentNode createNode();
}
