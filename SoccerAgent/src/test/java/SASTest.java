import dd.ontologypart.OntologyHandler;
import dd.sas.LevelHolder;
import dd.sas.SASHierarchyProcessor;
import dd.sas.SASOntologyProcessor;
import dd.soccer.perception.perceptingobjects.Ball;
import dd.soccer.sas.Adapter;
import dd.soccer.sas.worldentity.CoordinateCenter;
import dd.soccer.sas.worldentity.CoordinateCenterC;
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

        SASHierarchyProcessor sasHierarchyProcessor = new SASHierarchyProcessor(levelHolder);
        sasHierarchyProcessor.process();

        Ball ball = new Ball("1.0 2.0");
        adapter.setBall(ball);

        CoordinateCenter coordinateCenter = new CoordinateCenterC();
        coordinateCenter.setX(0.0);
        coordinateCenter.setY(0.0);
        adapter.setCoordinateCenter(coordinateCenter);

        sasHierarchyProcessor.process();
    }
}
