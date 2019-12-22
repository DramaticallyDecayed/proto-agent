package dd.sas.pipeline.calculation.structures.nodes.generativenodes;

import dd.sas.pipeline.calculation.structures.flows.generativeflows.InitialObjectFlow;
import dd.sas.pipeline.calculation.structures.nodes.Node;
import dd.sas.pipeline.worldmodel.WorldObject;

import java.util.function.UnaryOperator;

/**
 * Created by Sergey on 15.09.2016.
 */
public abstract class InitialGenerativeNode<O extends WorldObject> implements Node {

    private final InitialObjectFlow<O> outFlow;

    public InitialGenerativeNode(UnaryOperator<O> dostaff) {
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
