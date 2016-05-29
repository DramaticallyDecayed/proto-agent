package dd.sas;

import dd.sas.computation.Level;

/**
 * Created by Sergey on 29.05.2016.
 */
public class SASHierarchyProcessor extends SASProcessor{

    public SASHierarchyProcessor(LevelHolder levelHolder) {
        super(levelHolder);
    }

    public void process(){
        getLevelHolder().getLevels().forEach(Level::process);
    }
}
