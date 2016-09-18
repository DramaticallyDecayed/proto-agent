package onflows.calculation.structure.nodes;

import onflows.calculation.structure.flows.RelationFlowI;
import onflows.worldmodel.Relation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 13.09.2016.
 */
public abstract class AssociativePlainNode<S extends Relation> implements Node{
    private List<RelationFlowI<S>> outFlows = new ArrayList<>();

    public void addOutFlow(RelationFlowI<S> relationFlow){
        outFlows.add(relationFlow);
    }

    @Override
    public void calculate(){
       outFlows.stream().forEach(of -> of.calculate());
    }

}
