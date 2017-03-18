
package dd.smda.sas.computation.node;

import java.util.ArrayList;
import java.util.List;
import dd.sas.annotations.NodeFieldMarkup;
import dd.sas.annotations.NodeMethodMarkUp;
import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.sas.computation.Node;
import dd.smda.sas.objectproperty.BePatientGroupRuleFor;
import dd.smda.sas.worldentity.ParameterBatch;
import dd.smda.sas.worldentity.PatientGroupRule;

public class Node_cu_bePatientGroupRuleFor
    extends Node
    implements Node_cu_ParameterBatchOxidizing, Node_cu_PatientGroupRuleOxidizing
{

    public final static String NAME = "node_cu_bePatientGroupRuleFor";
    @NodeFieldMarkup(present = "derivative")
    private List<BePatientGroupRuleFor> bePatientGroupRuleForList = new ArrayList<BePatientGroupRuleFor>();

    public Node_cu_bePatientGroupRuleFor(Level level) {
        super(level);
    }

    @Override
    public void pushAsDonor(Node acceptor) {
        ((Node_cu_bePatientGroupRuleForOxidizing) acceptor).setDonor(this);
    }

    @Override
    public void dropDerivative() {
        bePatientGroupRuleForList.clear();
    }

    @Override
    public CalculationResult customProcess() {
        return (CalculationResult.UNKNOWN);
    }

    @Override
    public String name() {
        return "node_cu_bePatientGroupRuleFor";
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<BePatientGroupRuleFor> getBePatientGroupRuleForList() {
        return bePatientGroupRuleForList;
    }

    @Override
    public void setDonor(Node_cu_PatientGroupRule node) {
        fillDonor(PatientGroupRule.class, (node::getPatientGroupRuleList));
    }

    @Override
    public void setDonor(Node_cu_ParameterBatch node) {
        fillDonor(ParameterBatch.class, (node::getParameterBatchList));
    }

    public List<PatientGroupRule> getPatientGroupRuleList() {
        return ((List<PatientGroupRule> ) getBaseMap().get(PatientGroupRule.class));
    }

    public List<ParameterBatch> getParameterBatchList() {
        return ((List<ParameterBatch> ) getBaseMap().get(ParameterBatch.class));
    }

    private BePatientGroupRuleFor newBePatientGroupRuleFor() {
        return new BePatientGroupRuleFor();
    }

    private BePatientGroupRuleFor fillBePatientGroupRuleFor(BePatientGroupRuleFor relation, PatientGroupRule domain, ParameterBatch range) {
        relation.setDomain(domain);
        relation.setRange(range);
        return relation;
    }

    public BePatientGroupRuleFor newDerivative(PatientGroupRule domain, ParameterBatch range) {
        BePatientGroupRuleFor bePatientGroupRuleFor = fillBePatientGroupRuleFor(newBePatientGroupRuleFor(), domain, range);
        bePatientGroupRuleForList.add(bePatientGroupRuleFor);
        return bePatientGroupRuleFor;
    }

}
