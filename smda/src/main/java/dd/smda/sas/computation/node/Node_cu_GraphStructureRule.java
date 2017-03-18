
package dd.smda.sas.computation.node;

import java.util.ArrayList;
import java.util.List;
import dd.sas.annotations.NodeFieldMarkup;
import dd.sas.annotations.NodeMethodMarkUp;
import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.sas.computation.Node;
import dd.smda.sas.Perceptor2SASAdapter;
import dd.smda.sas.worldentity.GraphStructureRule;
import dd.smda.sas.worldentity.GraphStructureRuleC;

public class Node_cu_GraphStructureRule
    extends Node
{

    public final static String NAME = "node_cu_GraphStructureRule";
    @NodeFieldMarkup(present = "derivative")
    private List<GraphStructureRule> graphStructureRuleList = new ArrayList<GraphStructureRule>();
    @NodeFieldMarkup(present = "donor")
    private Perceptor2SASAdapter perceptor2SASAdapter;

    public Node_cu_GraphStructureRule(Level level) {
        super(level);
    }

    @Override
    public void pushAsDonor(Node acceptor) {
        ((Node_cu_GraphStructureRuleOxidizing) acceptor).setDonor(this);
    }

    @Override
    public void dropDerivative() {
        graphStructureRuleList.clear();
    }

    @Override
    public CalculationResult customProcess() {
        return (CalculationResult.UNKNOWN);
    }

    @Override
    public String name() {
        return "node_cu_GraphStructureRule";
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<GraphStructureRule> getGraphStructureRuleList() {
        return graphStructureRuleList;
    }

    private GraphStructureRuleC createGraphStructureRule() {
        return new GraphStructureRuleC();
    }

    public Perceptor2SASAdapter getPerceptor2SASAdapter() {
        return perceptor2SASAdapter;
    }

    public void setPerceptor2SASAdapter(Perceptor2SASAdapter perceptor2SASAdapter) {
        this.perceptor2SASAdapter = perceptor2SASAdapter;
    }

    @Override
    public void pullData() {
        graphStructureRuleList = perceptor2SASAdapter.getGraphStructureRuleList();
    }

}
