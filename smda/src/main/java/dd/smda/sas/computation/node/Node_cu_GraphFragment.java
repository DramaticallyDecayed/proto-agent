
package dd.smda.sas.computation.node;

import java.util.ArrayList;
import java.util.List;
import dd.sas.annotations.NodeFieldMarkup;
import dd.sas.annotations.NodeMethodMarkUp;
import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.sas.computation.Node;
import dd.smda.sas.objectproperty.BeBatchFor;
import dd.smda.sas.objectproperty.GraphStructureRuleFor;
import dd.smda.sas.objectproperty.HasBatch;
import dd.smda.sas.objectproperty.HasGraph;
import dd.smda.sas.worldentity.GraphFragment;
import dd.smda.sas.worldentity.GraphFragmentC;
import dd.smda.sas.worldentity.GraphStructureRule;
import dd.smda.sas.worldentity.ParameterBatch;
import dd.smda.sas.worldentity.Patient;

public class Node_cu_GraphFragment
    extends Node
    implements Node_cu_GraphStructureRuleOxidizing, Node_cu_ParameterBatchOxidizing
{

    public final static String NAME = "node_cu_GraphFragment";
    @NodeFieldMarkup(present = "derivative")
    private List<GraphStructureRuleFor> graphStructureRuleForList = new ArrayList<GraphStructureRuleFor>();
    @NodeFieldMarkup(present = "derivative")
    private List<HasGraph> hasGraphList = new ArrayList<HasGraph>();
    @NodeFieldMarkup(present = "derivative")
    private List<GraphFragment> graphFragmentList = new ArrayList<GraphFragment>();
    @NodeFieldMarkup(present = "derivative")
    private List<BeBatchFor> beBatchForList = new ArrayList<BeBatchFor>();

    public Node_cu_GraphFragment(Level level) {
        super(level);
    }

    @Override
    public void pushAsDonor(Node acceptor) {
        ((Node_cu_GraphFragmentOxidizing) acceptor).setDonor(this);
    }

    @Override
    public void dropDerivative() {
        graphStructureRuleForList.clear();
        hasGraphList.clear();
        graphFragmentList.clear();
        beBatchForList.clear();
    }

    @Override
    public CalculationResult customProcess() {
        return (CalculationResult.UNKNOWN);
    }

    @Override
    public String name() {
        return "node_cu_GraphFragment";
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<GraphStructureRuleFor> getGraphStructureRuleForList() {
        return graphStructureRuleForList;
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<HasGraph> getHasGraphList() {
        return hasGraphList;
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<GraphFragment> getGraphFragmentList() {
        return graphFragmentList;
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<BeBatchFor> getBeBatchForList() {
        return beBatchForList;
    }

    @Override
    public void setDonor(Node_cu_GraphStructureRule node) {
        fillDonor(GraphStructureRule.class, (node::getGraphStructureRuleList));
    }

    @Override
    public void setDonor(Node_cu_ParameterBatch node) {
        fillDonor(HasBatch.class, (node::getHasBatchList));
    }

    public List<HasBatch> getHasBatchList() {
        return ((List<HasBatch> ) getBaseMap().get(HasBatch.class));
    }

    public List<GraphStructureRule> getGraphStructureRuleList() {
        return ((List<GraphStructureRule> ) getBaseMap().get(GraphStructureRule.class));
    }

    private GraphStructureRuleFor newGraphStructureRuleFor() {
        return new GraphStructureRuleFor();
    }

    private GraphStructureRuleFor initGraphStructureRuleFor(GraphStructureRule domain, GraphFragment range) {
        GraphStructureRuleFor graphStructureRuleFor = newGraphStructureRuleFor();
        graphStructureRuleFor.setDomain(domain);
        graphStructureRuleFor.setRange(range);
        return graphStructureRuleFor;
    }

    private HasGraph newHasGraph() {
        return new HasGraph();
    }

    private HasGraph initHasGraph(Patient domain, GraphFragment range) {
        HasGraph hasGraph = newHasGraph();
        hasGraph.setDomain(domain);
        hasGraph.setRange(range);
        return hasGraph;
    }

    private GraphFragment newGraphFragment() {
        return new GraphFragmentC();
    }

    private BeBatchFor newBeBatchFor() {
        return new BeBatchFor();
    }

    private BeBatchFor initBeBatchFor(ParameterBatch domain, GraphFragment range) {
        BeBatchFor beBatchFor = newBeBatchFor();
        beBatchFor.setDomain(domain);
        beBatchFor.setRange(range);
        return beBatchFor;
    }

    public void newDerivative(GraphStructureRule graphStructureRule) {
        GraphFragment graphFragment = newGraphFragment();
        graphFragmentList.add(graphFragment);
        GraphStructureRuleFor graphStructureRuleFor = initGraphStructureRuleFor(graphStructureRule, graphFragment);
        graphStructureRuleForList.add(graphStructureRuleFor);
    }

}
