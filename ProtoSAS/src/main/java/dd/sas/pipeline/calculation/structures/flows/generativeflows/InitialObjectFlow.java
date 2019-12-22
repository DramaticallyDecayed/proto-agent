package dd.sas.pipeline.calculation.structures.flows.generativeflows;

import dd.sas.pipeline.worldmodel.WorldObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/**
 * Created by Sergey on 15.09.2016.
 */
public class InitialObjectFlow<O extends WorldObject> implements ObjectFlow<O>{

    private List<Consumer<ObjectFlow<O>>> consumers = new CopyOnWriteArrayList<>();
    private final UnaryOperator<O> doStaff;
    private List<O> result = new ArrayList<>();

    public InitialObjectFlow(UnaryOperator<O> doStaff) {
        this.doStaff = doStaff;
    }

    @Override
    public List<O> getResult() {
        return result;
    }

    @Override
    public void subscribe(Consumer<ObjectFlow<O>> consumer) {
        consumers.add(consumer);
    }

    public void inform(){
        consumers.forEach(c -> c.accept(this));
    }

    @Override
    public void calculate() {
        result = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            result.add(doStaff.apply(null));
        }
        inform();
    }
}
