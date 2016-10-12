package domain.worldmodel.relations;

import dd.sas.pipeline.worldmodel.Relation;
import domain.worldmodel.worldobjects.Viewer;
import domain.worldmodel.worldobjects.VisibleObject;

/**
 * Created by Sergey on 02.10.2016.
 */
public class See<D extends Viewer, R extends VisibleObject> extends Relation<D, R> {

    public See(D domain, R range) {
        super(domain, range);
    }

}
