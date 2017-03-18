
package dd.smda.sas.computation.node;

import java.util.ArrayList;
import java.util.List;
import dd.sas.annotations.NodeFieldMarkup;
import dd.sas.annotations.NodeMethodMarkUp;
import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.sas.computation.Node;
import dd.smda.sas.objectproperty.AnalysisFormBatch;
import dd.smda.sas.objectproperty.BePatientBaseFor;
import dd.smda.sas.objectproperty.HasBatch;
import dd.smda.sas.objectproperty.IncludeParameter;
import dd.smda.sas.objectproperty.ParameterFormBatch;
import dd.smda.sas.objectproperty.RestrictBatch;
import dd.smda.sas.worldentity.ParameterBatch;

public class Node_cu_ParameterBatch
    extends Node
    implements Node_cu_PatientGroupRuleOxidizing, Node_cu_includeParameterOxidizing
{

    public final static String NAME = "node_cu_ParameterBatch";
    @NodeFieldMarkup(present = "derivative")
    private List<RestrictBatch> restrictBatchList = new ArrayList<RestrictBatch>();
    @NodeFieldMarkup(present = "derivative")
    private List<ParameterFormBatch> parameterFormBatchList = new ArrayList<ParameterFormBatch>();
    @NodeFieldMarkup(present = "derivative")
    private List<AnalysisFormBatch> analysisFormBatchList = new ArrayList<AnalysisFormBatch>();
    @NodeFieldMarkup(present = "derivative")
    private List<ParameterBatch> parameterBatchList = new ArrayList<ParameterBatch>();
    @NodeFieldMarkup(present = "derivative")
    private List<HasBatch> hasBatchList = new ArrayList<HasBatch>();

    public Node_cu_ParameterBatch(Level level) {
        super(level);
    }

    @Override
    public void pushAsDonor(Node acceptor) {
        ((Node_cu_ParameterBatchOxidizing) acceptor).setDonor(this);
    }

    @Override
    public void dropDerivative() {
        restrictBatchList.clear();
        parameterFormBatchList.clear();
        analysisFormBatchList.clear();
        parameterBatchList.clear();
        hasBatchList.clear();
    }

    @Override
    public CalculationResult customProcess() {
        return (CalculationResult.UNKNOWN);
    }

    @Override
    public String name() {
        return "node_cu_ParameterBatch";
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<RestrictBatch> getRestrictBatchList() {
        return restrictBatchList;
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<ParameterFormBatch> getParameterFormBatchList() {
        return parameterFormBatchList;
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<AnalysisFormBatch> getAnalysisFormBatchList() {
        return analysisFormBatchList;
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<ParameterBatch> getParameterBatchList() {
        return parameterBatchList;
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<HasBatch> getHasBatchList() {
        return hasBatchList;
    }

    @Override
    public void setDonor(Node_cu_PatientGroupRule node) {
        fillDonor(BePatientBaseFor.class, (node::getBePatientBaseForList));
    }

    @Override
    public void setDonor(Node_cu_includeParameter node) {
        fillDonor(IncludeParameter.class, (node::getIncludeParameterList));
    }

    public List<IncludeParameter> getIncludeParameterList() {
        return ((List<IncludeParameter> ) getBaseMap().get(IncludeParameter.class));
    }

    public List<BePatientBaseFor> getBePatientBaseForList() {
        return ((List<BePatientBaseFor> ) getBaseMap().get(BePatientBaseFor.class));
    }

}
