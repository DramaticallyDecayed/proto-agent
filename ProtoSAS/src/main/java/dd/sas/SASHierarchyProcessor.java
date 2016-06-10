package dd.sas;

import dd.sas.computation.Level;
import dd.sas.computation.Node;

/**
 * Created by Sergey on 29.05.2016.
 */
public class SASHierarchyProcessor extends SASProcessor {

    public SASHierarchyProcessor(LevelHolder levelHolder) {
        super(levelHolder);
    }

    public void process() {
        for (Level level : getLevelHolder().getLevels()) {
            if (!level.getNodesToBeProcessed().isEmpty()) {
                level.getNodesToBeProcessed().forEach(Node::process);
                level.getNodesToBeProcessed().clear();
            }
        }
    }
}
