package onflows.calculation.structure.flows;

import onflows.worldmodel.WorldObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * Created by Sergey on 15.09.2016.
 */
public class InitialObjectFlow<O extends WorldObject> implements ObjectFlow<O>{

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
    public void calculate() {
        result = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            result.add(doStaff.apply(null));
        }
    }
}
