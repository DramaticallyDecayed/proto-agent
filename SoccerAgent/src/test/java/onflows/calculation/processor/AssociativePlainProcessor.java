package onflows.calculation.processor;

import onflows.worldmodel.Relation;
import onflows.worldmodel.WorldObject;

import java.util.function.Supplier;

/**
 * Created by Sergey on 13.09.2016.
 */
public abstract class AssociativePlainProcessor<D extends WorldObject, R extends WorldObject>
        extends AssociativeProcessor<D, R> {

    public abstract <D1 extends D, R1 extends R> Relation<D1, R1> doStaffCommon(D1 d, R1 r);

}
