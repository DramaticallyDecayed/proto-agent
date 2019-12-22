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

    public boolean process() {
        boolean hasResult = false;
        for (Level level : getLevelHolder().getLevels()) {
            if (!level.getNodesToBeProcessed().isEmpty()) {
                level.getNodesToBeProcessed().forEach(Node::process);
                hasResult = true;
                level.getNodesToBeProcessed().clear();
            }
        }
        return hasResult;
    }
}
