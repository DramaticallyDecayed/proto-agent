package dd.sas;

import dd.ontologyinterchanger.ConstructQuery;
import dd.ontologyinterchanger.QueryExecuter;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.sas.computation.Level;
import dd.sas.computation.Node;
import dd.sas.owlinterplay.QueryFabric;

/**
 * Created by Sergey on 29.05.2016.
 */
public class SASOntologyNotifyProcessor extends SASProcessor {

    private final QueryExecuter executer;

    public SASOntologyNotifyProcessor(LevelHolder levelHolder, QueryExecuter executer) {
        super(levelHolder);
        this.executer = executer;
    }

    @Override
    public boolean process() {
        boolean hasResult = false;
        for (Level level : getLevelHolder().getLevels()) {
            if (!level.getNodesToBeActivated().isEmpty()) {
                level.getNodesToBeActivated().forEach(this::activateNodeInOntology);
                level.getNodesToBeActivated().forEach(node -> refineCalculationHierarchy(node));
                hasResult = true;
                level.getNodesToBeActivated().clear();
            }
        }
        return hasResult;
    }

    private void activateNodeInOntology(Node node) {
        ConstructQuery cq = QueryFabric.prepareActivationString(node.name());
        executer.executeQuery(cq);
        if (!cq.getResult().isEmpty()) {
            executer.commitResults(cq.getResult());
            executer.arm();
        }
    }

    private void refineCalculationHierarchy(Node node) {
        SelectQueryHolder sqh = (SelectQueryHolder) executer
                .executeQuery(QueryFabric.collectNodesToBeRefined(node.name()));
        if (!sqh.isEmpty()) {
            sqh.asStream().forEach(result -> addCalculationLink(node, (Integer) result[0], (String) result[1]));
        }
    }

    private void addCalculationLink(Node node, Integer levelNum, String nodeName) {
        Node refinedNode = getLevelHolder().getLevel(levelNum).retrieveNode(nodeName);
        node.pushAsDonor(refinedNode);
        node.subscribe(refinedNode);
        refinedNode.processNode();
    }
}
