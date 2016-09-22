package dd.sas.pipeline.calculation.processor.associativeprocessors;

import dd.sas.pipeline.worldmodel.Relation;
import dd.sas.pipeline.worldmodel.WorldObject;

/**
 * Created by Sergey on 13.09.2016.
 */
public abstract class AssociativePlainProcessor<D extends WorldObject, R extends WorldObject>
        extends AssociativeProcessor<D, R> {

    public abstract <D1 extends D, R1 extends R> Relation<D1, R1> doStaffCommon(D1 d, R1 r);

}
