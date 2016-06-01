package dd.soccer.sas.nodeimplementation;

import dd.sas.computation.Level;

/**
 * Created by Sergey on 01.06.2016.
 */
public class Node_cu_Landmark extends dd.soccer.sas.computation.node.Node_cu_Landmark {
    public Node_cu_Landmark(Level level) {
        super(level);
    }

    @Override
    public Boolean customProcess(){
        return true;
    }
}
