
package dd.union.sas;

import java.util.List;
import dd.sas.computation.Level;
import dd.sas.computation.Node;
import dd.union.sas.worldentity.Notification;
import dd.union.sas.worldentity.UnionPercepted;

public abstract class Perceptor2SASAdapter
    extends Node
{


    public Perceptor2SASAdapter() {
        super(new Level(0));
    }

    public abstract List<UnionPercepted> getUnionPerceptedList();

    public abstract List<Notification> getNotificationList();

}
