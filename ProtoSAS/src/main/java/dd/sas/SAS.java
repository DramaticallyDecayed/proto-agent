package dd.sas;

import dd.ontologyinterchanger.QueryExecuter;

/**
 * Created by Sergey on 28.05.2016.
 */
public class SAS {

    private LevelHolder levelHolder;
    private SASProcessor hierarchy;
    private SASProcessor ontologyNotify;
    private SASProcessor ontologyProcessor;

    public SAS(QueryExecuter queryExecuter){
        levelHolder = new LevelHolder();
        hierarchy = new SASHierarchyProcessor(levelHolder);
        ontologyNotify = new SASOntologyNotifyProcessor(levelHolder, queryExecuter);
        ontologyProcessor = new SASOntologyProcessor(levelHolder, queryExecuter);
    }

    public void cycle(){
        hierarchy.process();
        ontologyNotify.process();
        ontologyProcessor.process();
    }

    public LevelHolder getLevelHolder(){
        return levelHolder;
    }
}
