package dd.sas;

import dd.ontologyinterchanger.QueryExecuter;

/**
 * Created by Sergey on 28.05.2016.
 */
public class SAS {

    private LevelHolder levelHolder;
    private SASProcessor hierarchy;
    private SASProcessor ontologyNotify;

    public SAS(QueryExecuter queryExecuter){
        levelHolder = new LevelHolder();
        hierarchy = new SASHierarchyProcessor(levelHolder);
        ontologyNotify = new SASOntologyNotifyProcessor(levelHolder, queryExecuter);
    }

    public void cycle(){
        hierarchy.process();
        ontologyNotify.process();
    }
}
