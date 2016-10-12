package domain.worldmodel.relations;

import dd.sas.pipeline.worldmodel.Relation;
import domain.worldmodel.worldobjects.Viewer;
import domain.worldmodel.worldobjects.VisibleObject;

/**
 * Created by Sergey on 12.10.2016.
 */
public class Seenby<D extends VisibleObject, R extends Viewer> extends Relation<D, R> {
    public Seenby(D domain, R range) {
        super(domain, range);
    }
}