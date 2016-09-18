package onflows.calculation.processor;

import onflows.worldmodel.Relation;
import onflows.worldmodel.WorldObject;

/**
 * Created by Sergey on 18.09.2016.
 */
public abstract class AssociativeRefiningProcessor
        <D extends WorldObject, M extends WorldObject, R extends WorldObject>
        extends AssociativeProcessor<D, R> {

    public abstract <D1 extends D, M1 extends M, R1 extends R> Relation<D1, R1>
    doStaffCommon(D1 d, M1 m, R1 r);


}
