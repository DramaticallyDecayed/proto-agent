import dd.ontologypart.OntologyHandler;
import dd.sas.LevelHolder;
import dd.sas.SASOntologyProcessor;
import org.junit.Test;

/**
 * Created by Sergey on 30.05.2016.
 */
public class SASTest {

    @Test
    public void genericSASTest(){
        OntologyHandler ontologyHandler = new OntologyHandler();
        LevelHolder levelHolder = new LevelHolder();
        SASOntologyProcessor ontologyProcessor = new SASOntologyProcessor(levelHolder, ontologyHandler);
        ontologyProcessor.process();
    }
}
