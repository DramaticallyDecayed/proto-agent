package commonmodel;

import common.Dependency;

/**
 * Created by Sergey on 23.10.2015.
 */
public class ElementState {

    private final Dependency representDependecy;

    public ElementState() {
        representDependecy = new Dependency(getClass());
    }
    
    public Dependency getRepresentDependecy() {
        return representDependecy;
    }
}
