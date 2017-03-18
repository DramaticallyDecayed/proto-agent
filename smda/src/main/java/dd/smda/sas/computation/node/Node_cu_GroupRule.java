
package dd.smda.sas.computation.node;

import java.util.ArrayList;
import java.util.List;
import dd.sas.annotations.NodeFieldMarkup;
import dd.sas.annotations.NodeMethodMarkUp;
import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.sas.computation.Node;
import dd.smda.sas.Perceptor2SASAdapter;
import dd.smda.sas.worldentity.GroupRule;
import dd.smda.sas.worldentity.GroupRuleC;

public class Node_cu_GroupRule
    extends Node
{

    public final static String NAME = "node_cu_GroupRule";
    @NodeFieldMarkup(present = "derivative")
    private List<GroupRule> groupRuleList = new ArrayList<GroupRule>();
    @NodeFieldMarkup(present = "donor")
    private Perceptor2SASAdapter perceptor2SASAdapter;

    public Node_cu_GroupRule(Level level) {
        super(level);
    }

    @Override
    public void pushAsDonor(Node acceptor) {
        ((Node_cu_GroupRuleOxidizing) acceptor).setDonor(this);
    }

    @Override
    public void dropDerivative() {
        groupRuleList.clear();
    }

    @Override
    public CalculationResult customProcess() {
        return (CalculationResult.UNKNOWN);
    }

    @Override
    public String name() {
        return "node_cu_GroupRule";
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<GroupRule> getGroupRuleList() {
        return groupRuleList;
    }

    private GroupRuleC createGroupRule() {
        return new GroupRuleC();
    }

    public Perceptor2SASAdapter getPerceptor2SASAdapter() {
        return perceptor2SASAdapter;
    }

    public void setPerceptor2SASAdapter(Perceptor2SASAdapter perceptor2SASAdapter) {
        this.perceptor2SASAdapter = perceptor2SASAdapter;
    }

    @Override
    public void pullData() {
        groupRuleList = perceptor2SASAdapter.getGroupRuleList();
    }

}
