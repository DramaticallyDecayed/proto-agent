package onflows.calculation.structure.flows;

import onflows.worldmodel.Relation;

import java.util.List;

/**
 * Created by Sergey on 18.09.2016.
 */
public abstract class RelationFlow<R extends Relation> implements RelationFlowI<R> {

    public boolean checkLInFlow(List list) {
        return (list != null && !list.isEmpty());
    }
}
