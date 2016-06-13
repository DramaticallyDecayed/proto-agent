package dd.soccer.sas.nodeimplementation;

import dd.sas.computation.Level;

/**
 * Created by Sergey on 10.06.2016.
 */
public class Node_cu_abs_seenby extends dd.soccer.sas.computation.node.Node_cu_abs_seenby {

    public Node_cu_abs_seenby(Level level) {
        super(level);
    }


    @Override
    public Boolean customProcess() {
        System.out.println("READY TO CALCULATE....");
        return false;
    }
}
