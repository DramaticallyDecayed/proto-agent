package dd.sas.pipeline.calculation.processor.associativeprocessors;

import dd.sas.pipeline.worldmodel.Relation;
import dd.sas.pipeline.worldmodel.WorldObject;

import java.util.function.Supplier;

/**
 * Created by Sergey on 18.09.2016.
 */
public abstract class AssociativeProcessor<D extends WorldObject, R extends WorldObject> {

    public <D1 extends D, R1 extends R, S extends Relation<D1, R1>> S generator(
            Supplier<S> supplier, D1 d, R1 r) {
        S see = supplier.get();
        see.setDomain(d);
        see.setRange(r);
        return see;
    }

}
