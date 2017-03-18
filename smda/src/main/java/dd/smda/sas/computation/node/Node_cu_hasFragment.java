
package dd.smda.sas.computation.node;

import java.util.ArrayList;
import java.util.List;
import dd.sas.annotations.NodeFieldMarkup;
import dd.sas.annotations.NodeMethodMarkUp;
import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.sas.computation.Node;
import dd.smda.sas.objectproperty.HasFragment;
import dd.smda.sas.worldentity.Graph;
import dd.smda.sas.worldentity.GraphFragment;

public class Node_cu_hasFragment
    extends Node
    implements Node_cu_GraphFragmentOxidizing
{

    public final static String NAME = "node_cu_hasFragment";
    @NodeFieldMarkup(present = "derivative")
    private List<HasFragment> hasFragmentList = new ArrayList<HasFragment>();
    @NodeFieldMarkup(present = "derivative")
    private List<Graph> graphList = new ArrayList<Graph>();

    public Node_cu_hasFragment(Level level) {
        super(level);
    }

    @Override
    public void pushAsDonor(Node acceptor) {
        ((Node_cu_hasFragmentOxidizing) acceptor).setDonor(this);
    }

    @Override
    public void dropDerivative() {
        hasFragmentList.clear();
        graphList.clear();
    }

    @Override
    public CalculationResult customProcess() {
        return (CalculationResult.UNKNOWN);
    }

    @Override
    public String name() {
        return "node_cu_hasFragment";
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<HasFragment> getHasFragmentList() {
        return hasFragmentList;
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<Graph> getGraphList() {
        return graphList;
    }

    @Override
    public void setDonor(Node_cu_GraphFragment node) {
        fillDonor(GraphFragment.class, (node::getGraphFragmentList));
    }

    public List<GraphFragment> getGraphFragmentList() {
        return ((List<GraphFragment> ) getBaseMap().get(GraphFragment.class));
    }

}
