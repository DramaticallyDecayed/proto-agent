package onflows.calculation.structure.flows;

import onflows.worldmodel.Relation;
import onflows.worldmodel.WorldObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Created by Sergey on 13.09.2016.
 */
public class AssociativePlainFlow
        <D extends WorldObject, R extends WorldObject, S extends Relation<D, R>>
        extends RelationFlow<S> {

    private final BiFunction<D, R, S> doStaff;
    private Supplier<List<D>> domainFlow;
    private Supplier<List<R>> rangeFlow;
    private List<S> result = new ArrayList<>();

    public AssociativePlainFlow(BiFunction<D, R, S> doStaff) {
        this.doStaff = doStaff;
    }

    @Override
    public void calculate() {
        if (checkLInFlow(domainFlow.get()) && checkLInFlow(rangeFlow.get())) {
            for (D d : domainFlow.get()) {
                for (R r : rangeFlow.get()) {
                    result.add(doStaff.apply(d, r));
                }
            }
        }
    }

    public List<S> getResult() {
        return result;
    }

    public void setDomainFlow(Supplier<List<D>> domainFlow) {
        this.domainFlow = domainFlow;
    }

    public void setRangeFlow(Supplier<List<R>> rangeFlow) {
        this.rangeFlow = rangeFlow;
    }
}
