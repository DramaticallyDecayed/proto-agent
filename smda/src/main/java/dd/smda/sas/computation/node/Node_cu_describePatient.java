
package dd.smda.sas.computation.node;

import java.util.ArrayList;
import java.util.List;
import dd.sas.annotations.NodeFieldMarkup;
import dd.sas.annotations.NodeMethodMarkUp;
import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.sas.computation.Node;
import dd.smda.sas.objectproperty.DescribePatient;
import dd.smda.sas.worldentity.Analysis;
import dd.smda.sas.worldentity.Patient;

public class Node_cu_describePatient
    extends Node
    implements Node_cu_AnalysisOxidizing
{

    public final static String NAME = "node_cu_describePatient";
    @NodeFieldMarkup(present = "derivative")
    private List<DescribePatient> describePatientList = new ArrayList<DescribePatient>();
    @NodeFieldMarkup(present = "derivative")
    private List<Patient> patientList = new ArrayList<Patient>();

    public Node_cu_describePatient(Level level) {
        super(level);
    }

    @Override
    public void pushAsDonor(Node acceptor) {
        ((Node_cu_describePatientOxidizing) acceptor).setDonor(this);
    }

    @Override
    public void dropDerivative() {
        describePatientList.clear();
        patientList.clear();
    }

    @Override
    public CalculationResult customProcess() {
        return (CalculationResult.UNKNOWN);
    }

    @Override
    public String name() {
        return "node_cu_describePatient";
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<DescribePatient> getDescribePatientList() {
        return describePatientList;
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<Patient> getPatientList() {
        return patientList;
    }

    @Override
    public void setDonor(Node_cu_Analysis node) {
        fillDonor(Analysis.class, (node::getAnalysisList));
    }

    public List<Analysis> getAnalysisList() {
        return ((List<Analysis> ) getBaseMap().get(Analysis.class));
    }

}
