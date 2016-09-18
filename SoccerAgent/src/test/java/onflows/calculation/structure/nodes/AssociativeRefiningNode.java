package onflows.calculation.structure.nodes;

import onflows.calculation.structure.flows.RelationFlowI;
import onflows.worldmodel.Relation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 18.09.2016.
 */
public class AssociativeRefiningNode<C extends Relation> implements Node  {

    private List<RelationFlowI<C>> outFlows = new ArrayList<>();

    public void addOutFlow(RelationFlowI<C> relationFlow){
        outFlows.add(relationFlow);
    }


    @Override
    public void calculate() {
        outFlows.stream().forEach(of -> of.calculate());
    }
}
