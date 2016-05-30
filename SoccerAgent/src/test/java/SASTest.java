import dd.ontologypart.OntologyHandler;
import dd.sas.LevelHolder;
import dd.sas.SASOntologyProcessor;
import dd.soccer.sas.Adapter;
import org.junit.Test;

/**
 * Created by Sergey on 30.05.2016.
 */
public class SASTest {

    @Test
    public void genericSASTest(){

        Adapter adapter = new Adapter();
        adapter.getLevel().addNode(adapter);

        OntologyHandler ontologyHandler = new OntologyHandler();

        LevelHolder levelHolder = new LevelHolder();
        levelHolder.addLevel(adapter.getLevel());


        SASOntologyProcessor ontologyProcessor = new SASOntologyProcessor(levelHolder, ontologyHandler);
        ontologyProcessor.process();
    }
}
