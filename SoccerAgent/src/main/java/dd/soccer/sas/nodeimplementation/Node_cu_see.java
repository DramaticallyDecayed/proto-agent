package dd.soccer.sas.nodeimplementation;

import dd.sas.computation.Level;
import dd.soccer.sas.worldentity.VisibleObject;

/**
 * Created by Sergey on 15.06.2016.
 */
public class Node_cu_see extends dd.soccer.sas.computation.node.Node_cu_see {
    public Node_cu_see(Level level) {
        super(level);
    }

    @Override
    public Boolean customProcess() {
        if (getViewerList() != null && getVisibleObjectList() != null) {
            if (!(getViewerList().isEmpty() || getVisibleObjectList().isEmpty())) {
                for (VisibleObject visibleObject : getVisibleObjectList()) {
                    newDerivative(getViewerList().get(0), visibleObject);
                }
                return true;
            }
        }
        return false;
    }
}
