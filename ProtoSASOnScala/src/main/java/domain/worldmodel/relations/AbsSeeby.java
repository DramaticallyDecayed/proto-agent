package domain.worldmodel.relations;

import dd.sas.pipeline.worldmodel.Relation;
import domain.worldmodel.worldobjects.Ball;
import domain.worldmodel.worldobjects.CoordinateCenter;
import domain.worldmodel.worldobjects.Ego;
import domain.worldmodel.worldobjects.Viewer;

import java.util.function.BiFunction;

/**
 * Created by Sergey on 12.10.2016.
 */
public class AbsSeeby<D extends CoordinateCenter, R extends Viewer> extends Relation<D, R> {
    public AbsSeeby(D domain, R range) {
        super(domain, range);
    }


}
