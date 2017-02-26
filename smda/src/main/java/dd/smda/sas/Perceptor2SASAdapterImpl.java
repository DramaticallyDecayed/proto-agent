package dd.smda.sas;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Node;
import dd.smda.sas.computation.node.Node_cu_Analysis;
import dd.smda.sas.computation.node.Node_cu_GraphStructureRule;
import dd.smda.sas.computation.node.Node_cu_GroupRule;
import dd.smda.sas.worldentity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergey on 06.02.2017.
 */
public class Perceptor2SASAdapterImpl extends Perceptor2SASAdapter {

    public final static String NAME = "perceptor2SASAdapter";

    private Map<String, Node> subscribers = new HashMap<>();

    private List<Analysis> analysisList;
    private List<GraphStructureRule> graphStructureRuleList;
    private List<GroupRule> groupRuleList;

    @Override
    public List<GroupRule> getGroupRuleList() {
        return groupRuleList;
    }

    @Override
    public List<GraphStructureRule> getGraphStructureRuleList() {
        return graphStructureRuleList;
    }

    @Override
    public List<Analysis> getAnalysisList() {
        return analysisList;
    }

    public void setAnalysisList(List<Analysis> analyses) {
        analysisList = new ArrayList<>(analyses);
        analyses.clear();
        if (subscribers.get(Node_cu_Analysis.NAME) != null && !analysisList.isEmpty()) {
            subscribers.get(Node_cu_Analysis.NAME).processNode();
        }
    }

    public void setGraphStructureRuleList() {
        graphStructureRuleList = new ArrayList<>();
        GraphStructureRule graphStructureRule = new GraphStructureRuleC();
        graphStructureRule.setHasRuleString("UrineColor " +
                "AcidityApprox" +
                "AmorphousPhosphates" +
                "Bacteria" +
                "Bilirubin" +
                "Creatinine" +
                "Glucose" +
                "KetoneBodies" +
                "Oxalate" +
                "Protein" +
                "ProteinAnalyzer" +
                "RedBloodCells" +
                "SpecificGravity" +
                "Urata" +
                "UricAcidCrystals" +
                "Urobilinogen" +
                "WhiteBloodCells");
        graphStructureRuleList.add(graphStructureRule);
        if (subscribers.get(Node_cu_GraphStructureRule.NAME) != null) {
            subscribers.get(Node_cu_GraphStructureRule.NAME).processNode();
        }
    }

    public void setGroupRuleList() {
        groupRuleList = new ArrayList<>();
        GroupRule groupRule = new GroupRuleC();
        groupRule.setHasRuleString("getHasDiagnosis getHasSex");
        groupRuleList.add(groupRule);
        if (subscribers.get(Node_cu_GroupRule.NAME) != null) {
            subscribers.get(Node_cu_GroupRule.NAME).processNode();
        }
    }

    @Override
    public void dropDerivative() {
        groupRuleList.clear();
        graphStructureRuleList.clear();
    }

    @Override
    public void subscribe(Node node) {
        subscribers.put(node.name(), node);
    }

    @Override
    public CalculationResult customProcess() {
        return CalculationResult.UNKNOWN;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public void pushAsDonor(Node acceptor) {

    }
}
