
package dd.smda.sas.computation.node;

import java.util.ArrayList;
import java.util.List;
import dd.sas.annotations.NodeFieldMarkup;
import dd.sas.annotations.NodeMethodMarkUp;
import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.sas.computation.Node;
import dd.smda.sas.objectproperty.IncludeParameter;
import dd.smda.sas.worldentity.Analysis;
import dd.smda.sas.worldentity.SystemParameter;

public class Node_cu_includeParameter
    extends Node
    implements Node_cu_AnalysisOxidizing
{

    public final static String NAME = "node_cu_includeParameter";
    @NodeFieldMarkup(present = "derivative")
    private List<IncludeParameter> includeParameterList = new ArrayList<IncludeParameter>();
    @NodeFieldMarkup(present = "derivative")
    private List<SystemParameter> systemParameterList = new ArrayList<SystemParameter>();

    public Node_cu_includeParameter(Level level) {
        super(level);
    }

    @Override
    public void pushAsDonor(Node acceptor) {
        ((Node_cu_includeParameterOxidizing) acceptor).setDonor(this);
    }

    @Override
    public void dropDerivative() {
        includeParameterList.clear();
        systemParameterList.clear();
    }

    @Override
    public CalculationResult customProcess() {
        return (CalculationResult.UNKNOWN);
    }

    @Override
    public String name() {
        return "node_cu_includeParameter";
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<IncludeParameter> getIncludeParameterList() {
        return includeParameterList;
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<SystemParameter> getSystemParameterList() {
        return systemParameterList;
    }

    @Override
    public void setDonor(Node_cu_Analysis node) {
        fillDonor(Analysis.class, (node::getAnalysisList));
    }

    public List<Analysis> getAnalysisList() {
        return ((List<Analysis> ) getBaseMap().get(Analysis.class));
    }

}
