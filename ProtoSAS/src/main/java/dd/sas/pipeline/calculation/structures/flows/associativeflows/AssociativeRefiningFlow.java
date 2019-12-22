package dd.sas.pipeline.calculation.structures.flows.associativeflows;

import dd.sas.pipeline.TripleFunction;
import dd.sas.pipeline.worldmodel.Relation;
import dd.sas.pipeline.worldmodel.WorldObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by Sergey on 18.09.2016.
 */
public class AssociativeRefiningFlow
        <D extends WorldObject, M extends WorldObject, R extends WorldObject,
                F extends Relation<D, M>, S extends Relation<M, R>, C extends Relation<D, R>>
        extends RelationFlow<C> {

    private final TripleFunction<D, M, R, C> doStaff;
    private Supplier<List<F>> domainFlow;
    private Supplier<List<S>> rangeFlow;
    private List<C> result = new ArrayList<>();

    public AssociativeRefiningFlow(TripleFunction<D, M, R, C> doStaff) {
        this.doStaff = doStaff;
    }

    private boolean checkOnObjectEquality(M firstMiddle, M secondMiddle) {
        return firstMiddle == secondMiddle;
    }

    @Override
    public List<C> getResult() {
        return null;
    }

    @Override
    public void calculate() {
        if (checkLInFlow(domainFlow.get()) && checkLInFlow(rangeFlow.get())) {
            for (F f : domainFlow.get()) {
                for (S s : rangeFlow.get()) {
                    if (checkOnObjectEquality(f.getRange(), s.getDomain())) {
                        result.add(doStaff.apply(f.getDomain(), f.getRange(), s.getRange()));
                    }
                }
            }
        }
    }

    public void setDomainFlow(Supplier<List<F>> domainFlow) {
        this.domainFlow = domainFlow;
    }

    public void setRangeFlow(Supplier<List<S>> rangeFlow) {
        this.rangeFlow = rangeFlow;
    }


}
