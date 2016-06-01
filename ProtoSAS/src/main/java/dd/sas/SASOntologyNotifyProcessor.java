package dd.sas;

import dd.ontologyinterchanger.ConstructQuery;
import dd.ontologyinterchanger.QueryExecuter;
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
    public void process() {
        for (Level level : getLevelHolder().getLevels()) {
            level.getNodesToBeActivated().forEach(this::activateNodeInOntology);
        }
    }

    private void activateNodeInOntology(Node node) {
        ConstructQuery cq = QueryFabric.prepareActivationString(node.name());
        executer.executeQuery(cq);
        if(!cq.getResult().isEmpty()){
            executer.commitResults(cq.getResult());
        }
    }
}
