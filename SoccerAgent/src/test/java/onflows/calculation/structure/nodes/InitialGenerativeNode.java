package onflows.calculation.structure.nodes;

import onflows.calculation.structure.flows.InitialObjectFlow;
import onflows.worldmodel.WorldObject;

import java.util.function.UnaryOperator;

/**
 * Created by Sergey on 15.09.2016.
 */
public abstract class InitialGenerativeNode<O extends WorldObject> implements Node {

    private final InitialObjectFlow<O> outFlow;

    protected InitialGenerativeNode(UnaryOperator<O> dostaff) {
        this.outFlow = new InitialObjectFlow<O>(dostaff);
    }

    @Override
    public void calculate() {
        outFlow.calculate();
    }

    public InitialObjectFlow<O> getOutFlow(){
        return outFlow;
    }
}
