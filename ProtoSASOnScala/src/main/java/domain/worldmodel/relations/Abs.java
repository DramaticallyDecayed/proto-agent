package domain.worldmodel.relations;

import dd.sas.pipeline.worldmodel.Relation;
import domain.worldmodel.worldobjects.CoordinateCenter;
import domain.worldmodel.worldobjects.Point;

/**
 * Created by Sergey on 12.10.2016.
 */
public class Abs<D extends CoordinateCenter, R extends Point> extends Relation<D, R> {

    public Abs(D domain, R range) {
        super(domain, range);
    }

}