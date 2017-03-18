
package dd.smda.sas.computation.node;

import java.util.ArrayList;
import java.util.List;
import dd.sas.annotations.NodeFieldMarkup;
import dd.sas.annotations.NodeMethodMarkUp;
import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.sas.computation.Node;
import dd.smda.sas.objectproperty.BeGroupRuleBaseFor;
import dd.smda.sas.objectproperty.BePatientBaseFor;
import dd.smda.sas.worldentity.GroupRule;
import dd.smda.sas.worldentity.Patient;
import dd.smda.sas.worldentity.PatientGroupRule;
import dd.smda.sas.worldentity.PatientGroupRuleC;

public class Node_cu_PatientGroupRule
    extends Node
    implements Node_cu_GroupRuleOxidizing, Node_cu_describePatientOxidizing
{

    public final static String NAME = "node_cu_PatientGroupRule";
    @NodeFieldMarkup(present = "derivative")
    private List<BePatientBaseFor> bePatientBaseForList = new ArrayList<BePatientBaseFor>();
    @NodeFieldMarkup(present = "derivative")
    private List<BeGroupRuleBaseFor> beGroupRuleBaseForList = new ArrayList<BeGroupRuleBaseFor>();
    @NodeFieldMarkup(present = "derivative")
    private List<PatientGroupRule> patientGroupRuleList = new ArrayList<PatientGroupRule>();

    public Node_cu_PatientGroupRule(Level level) {
        super(level);
    }

    @Override
    public void pushAsDonor(Node acceptor) {
        ((Node_cu_PatientGroupRuleOxidizing) acceptor).setDonor(this);
    }

    @Override
    public void dropDerivative() {
        bePatientBaseForList.clear();
        beGroupRuleBaseForList.clear();
        patientGroupRuleList.clear();
    }

    @Override
    public CalculationResult customProcess() {
        return (CalculationResult.UNKNOWN);
    }

    @Override
    public String name() {
        return "node_cu_PatientGroupRule";
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<BePatientBaseFor> getBePatientBaseForList() {
        return bePatientBaseForList;
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<BeGroupRuleBaseFor> getBeGroupRuleBaseForList() {
        return beGroupRuleBaseForList;
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<PatientGroupRule> getPatientGroupRuleList() {
        return patientGroupRuleList;
    }

    @Override
    public void setDonor(Node_cu_describePatient node) {
        fillDonor(Patient.class, (node::getPatientList));
    }

    @Override
    public void setDonor(Node_cu_GroupRule node) {
        fillDonor(GroupRule.class, (node::getGroupRuleList));
    }

    public List<Patient> getPatientList() {
        return ((List<Patient> ) getBaseMap().get(Patient.class));
    }

    public List<GroupRule> getGroupRuleList() {
        return ((List<GroupRule> ) getBaseMap().get(GroupRule.class));
    }

    private BePatientBaseFor newBePatientBaseFor() {
        return new BePatientBaseFor();
    }

    private BePatientBaseFor initBePatientBaseFor(Patient domain, PatientGroupRule range) {
        BePatientBaseFor bePatientBaseFor = newBePatientBaseFor();
        bePatientBaseFor.setDomain(domain);
        bePatientBaseFor.setRange(range);
        return bePatientBaseFor;
    }

    private BeGroupRuleBaseFor newBeGroupRuleBaseFor() {
        return new BeGroupRuleBaseFor();
    }

    private BeGroupRuleBaseFor initBeGroupRuleBaseFor(GroupRule domain, PatientGroupRule range) {
        BeGroupRuleBaseFor beGroupRuleBaseFor = newBeGroupRuleBaseFor();
        beGroupRuleBaseFor.setDomain(domain);
        beGroupRuleBaseFor.setRange(range);
        return beGroupRuleBaseFor;
    }

    private PatientGroupRule newPatientGroupRule() {
        return new PatientGroupRuleC();
    }

    public void newDerivative(Patient patient, GroupRule groupRule) {
        PatientGroupRule patientGroupRule = newPatientGroupRule();
        patientGroupRuleList.add(patientGroupRule);
        BePatientBaseFor bePatientBaseFor = initBePatientBaseFor(patient, patientGroupRule);
        bePatientBaseForList.add(bePatientBaseFor);
        BeGroupRuleBaseFor beGroupRuleBaseFor = initBeGroupRuleBaseFor(groupRule, patientGroupRule);
        beGroupRuleBaseForList.add(beGroupRuleBaseFor);
    }

}
