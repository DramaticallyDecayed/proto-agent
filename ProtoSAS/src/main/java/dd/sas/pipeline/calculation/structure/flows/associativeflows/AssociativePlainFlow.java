package dd.sas.pipeline.calculation.structure.flows.associativeflows;

import dd.sas.pipeline.calculation.structure.flows.generativeflows.ObjectFlow;
import dd.sas.pipeline.worldmodel.Relation;
import dd.sas.pipeline.worldmodel.WorldObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
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
    private Map<ObjectFlow, Consumer<ObjectFlow>> donors = new HashMap<>();

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
        resubscribe();
    }

    public List<S> getResult() {
        return result;
    }

    public void setDomainFlow(ObjectFlow<D> flow) {
        domainFlow = flow::getResult;
        donors.put(flow, this::setDomainFlow);
    }

    public void setRangeFlow(ObjectFlow<R> flow) {
        rangeFlow = flow::getResult;
        donors.put(flow, this::setRangeFlow);
    }

    private void resubscribe(){
        donors.keySet().forEach(k -> k.subscribe(donors.get(k)));
    }
}
