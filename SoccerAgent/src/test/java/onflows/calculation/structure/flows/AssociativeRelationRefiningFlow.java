package onflows.calculation.structure.flows;

import onflows.worldmodel.Relation;
import onflows.worldmodel.WorldObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Created by Sergey on 18.09.2016.
 */
public class AssociativeRelationRefiningFlow
        <D extends WorldObject, R extends WorldObject,
                P extends Relation<D, R>,
                C extends Relation<D, R>> extends RelationFlow<C> {


    private final BiFunction<D, R, C> doStaff;
    private Supplier<List<P>> parentRelationFlow;
    private List<C> result = new ArrayList<>();

    public AssociativeRelationRefiningFlow(BiFunction<D, R, C> doStaff) {
        this.doStaff = doStaff;
    }

    @Override
    public List<C> getResult() {
        return null;
    }

    @Override
    public void calculate() {
        if (checkLInFlow(parentRelationFlow.get())) {
            for (P p : parentRelationFlow.get()) {
                result.add(doStaff.apply(p.getDomain(), p.getRange()));
            }
        }
    }

    public void setParentRelationFlow(Supplier<List<P>> domainFlow) {
        this.parentRelationFlow = parentRelationFlow;
    }
}
