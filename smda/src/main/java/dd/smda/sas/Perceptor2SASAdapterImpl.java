package dd.smda.sas;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Node;
import dd.smda.sas.computation.node.Node_cu_ConcentrateParameterRule;
import dd.smda.sas.computation.node.Node_cu_IntervalRule;
import dd.smda.sas.computation.node.Node_cu_SystemParameter;
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

    private List<SystemParameter> systemParameterList;
    private List<ConcentrateParameterRule> concentrateParameterRuleList;
    private List<IntervalRule> intervalRuleList;

    @Override
    public List<IntervalRule> getIntervalRuleList() {
        return intervalRuleList;
    }

    @Override
    public List<ConcentrateParameterRule> getConcentrateParameterRuleList() {
        return concentrateParameterRuleList;
    }

    @Override
    public List<SystemParameter> getSystemParameterList() {
        return systemParameterList;
    }

    public void setConcentrateParameterRuleList() {
        concentrateParameterRuleList = new ArrayList<>();
        concentrateParameterRuleList.add(new ConcentrateParameterRuleC());
        if (subscribers.get(Node_cu_ConcentrateParameterRule.NAME) != null) {
            subscribers.get(Node_cu_ConcentrateParameterRule.NAME).processNode();
        }
    }

    public void setSystemParameterList() {
        systemParameterList = new ArrayList<>();
        systemParameterList.add(new SystemParameterC());
        if (subscribers.get(Node_cu_SystemParameter.NAME) != null) {
            subscribers.get(Node_cu_SystemParameter.NAME).processNode();
        }

    }

    public void setIntervalRuleList() {
        intervalRuleList = new ArrayList<>();
        intervalRuleList.add(new IntervalRuleC());
        if (subscribers.get(Node_cu_IntervalRule.NAME) != null) {
            subscribers.get(Node_cu_IntervalRule.NAME).processNode();
        }
    }

    @Override
    public void dropDerivative() {

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
