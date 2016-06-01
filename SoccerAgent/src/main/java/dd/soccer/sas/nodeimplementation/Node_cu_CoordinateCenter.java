package dd.soccer.sas.nodeimplementation;

import dd.sas.computation.Level;

/**
 * Created by Sergey on 01.06.2016.
 */
public class Node_cu_CoordinateCenter extends dd.soccer.sas.computation.node.Node_cu_CoordinateCenter {

    private boolean introduced = false;

    public Node_cu_CoordinateCenter(Level level) {
        super(level);
    }

    public Boolean customProcess(){
        if(!introduced){
            introduced = !introduced;
            return true;
        }
        return false;
    }

}
