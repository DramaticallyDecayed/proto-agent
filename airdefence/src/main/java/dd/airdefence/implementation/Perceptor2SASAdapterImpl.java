package dd.airdefence.implementation;

import dd.airdefence.sas.Perceptor2SASAdapter;
import dd.airdefence.sas.computation.node.Node_cu_ImportantObject;
import dd.airdefence.sas.computation.node.Node_cu_UnknownAircraft;
import dd.airdefence.sas.worldentity.ImportantObject;
import dd.airdefence.sas.worldentity.UnknownAircraft;
import dd.sas.computation.CalculationResult;
import dd.sas.computation.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Perceptor2SASAdapterImpl extends Perceptor2SASAdapter {

    public final static String NAME = "perceptor2SASAdapter";

    private Map<String, Node> subscribers = new HashMap<>();


    private List<UnknownAircraft> unknownAircraftList = new ArrayList<>();
    private List<ImportantObject> importantObjectList = new ArrayList<>();

    public void setUnknownAircraftList(List<UnknownAircraft> unknownAircraftList){
        this.unknownAircraftList.clear();
        this.unknownAircraftList.addAll(unknownAircraftList);
        if (!unknownAircraftList.isEmpty() && subscribers.get(Node_cu_UnknownAircraft.NAME) != null) {
            subscribers.get(Node_cu_UnknownAircraft.NAME).processNode();
        }
    }

    public void setImportantObjectList(List<ImportantObject> importantObjectList){
        this.importantObjectList.clear();
        this.importantObjectList.addAll(importantObjectList);
        if (!importantObjectList.isEmpty() && subscribers.get(Node_cu_ImportantObject.NAME) != null) {
            subscribers.get(Node_cu_ImportantObject.NAME).processNode();
        }
    }

    @Override
    public List<UnknownAircraft> getUnknownAircraftList() {
        return unknownAircraftList;
    }

    @Override
    public List<ImportantObject> getImportantObjectList() {
        return importantObjectList;
    }

    @Override
    public void dropDerivative() {
        unknownAircraftList.clear();
        importantObjectList.clear();
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

    @Override
    public void subscribe(Node node) {
        subscribers.put(node.name(), node);
    }
}
