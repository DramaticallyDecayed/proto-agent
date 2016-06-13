package dd.soccer.sas.nodeimplementation;

import dd.sas.computation.Level;
import dd.sas.computation.WrappingGetter;

/**
 * Created by Sergey on 10.06.2016.
 */
public class Node_cu_abs extends dd.soccer.sas.computation.node.Node_cu_abs {
    public Node_cu_abs(Level level) {
        super(level);
        System.out.println("CREATED");
    }

    @Override
    public Boolean customProcess() {

        System.out.print("DO SOMETHING with ");
        if(getPointList() != null){
            System.out.print(getPointList().size());
        }else{
            System.out.print(" nothing");
        }
        System.out.println("");

        return false;
    }

    public void fillBases(Class c, WrappingGetter wg) {
        System.out.println("FILL WITH SOMETHING: " + c.getSimpleName() + " : " + wg.getObjectList().size());
        super.fillBases(c, wg);
    }

    public void dropDerivative() {
        System.out.println("DROP SOMETHING");
        super.dropDerivative();
    }
}
