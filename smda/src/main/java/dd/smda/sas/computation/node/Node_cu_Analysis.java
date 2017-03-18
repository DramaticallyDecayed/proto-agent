
package dd.smda.sas.computation.node;

import java.util.ArrayList;
import java.util.List;
import dd.sas.annotations.NodeFieldMarkup;
import dd.sas.annotations.NodeMethodMarkUp;
import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.sas.computation.Node;
import dd.smda.sas.Perceptor2SASAdapter;
import dd.smda.sas.worldentity.Analysis;
import dd.smda.sas.worldentity.AnalysisC;

public class Node_cu_Analysis
    extends Node
{

    public final static String NAME = "node_cu_Analysis";
    @NodeFieldMarkup(present = "derivative")
    private List<Analysis> analysisList = new ArrayList<Analysis>();
    @NodeFieldMarkup(present = "donor")
    private Perceptor2SASAdapter perceptor2SASAdapter;

    public Node_cu_Analysis(Level level) {
        super(level);
    }

    @Override
    public void pushAsDonor(Node acceptor) {
        ((Node_cu_AnalysisOxidizing) acceptor).setDonor(this);
    }

    @Override
    public void dropDerivative() {
        analysisList.clear();
    }

    @Override
    public CalculationResult customProcess() {
        return (CalculationResult.UNKNOWN);
    }

    @Override
    public String name() {
        return "node_cu_Analysis";
    }

    @NodeMethodMarkUp(present = "derivative")
    public List<Analysis> getAnalysisList() {
        return analysisList;
    }

    private AnalysisC createAnalysis() {
        return new AnalysisC();
    }

    public Perceptor2SASAdapter getPerceptor2SASAdapter() {
        return perceptor2SASAdapter;
    }

    public void setPerceptor2SASAdapter(Perceptor2SASAdapter perceptor2SASAdapter) {
        this.perceptor2SASAdapter = perceptor2SASAdapter;
    }

    @Override
    public void pullData() {
        analysisList = perceptor2SASAdapter.getAnalysisList();
    }

}
