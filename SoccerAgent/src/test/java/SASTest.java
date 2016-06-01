import dd.ontologypart.OntologyHandler;
import dd.sas.LevelHolder;
import dd.sas.SASHierarchyProcessor;
import dd.sas.SASOntologyNotifyProcessor;
import dd.sas.SASOntologyProcessor;
import dd.soccer.perception.perceptingobjects.BodyState;
import dd.soccer.perception.perceptingobjects.Line;
import dd.soccer.perception.perceptingobjects.NavigatingLandmark;
import dd.soccer.sas.Perceptor2SASAdapterImpl;
import dd.soccer.sas.worldentity.CoordinateCenter;
import dd.soccer.sas.worldentity.CoordinateCenterC;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Sergey on 30.05.2016.
 */
public class SASTest {

    @Test
    public void genericSASTest(){

        Perceptor2SASAdapterImpl adapter = new Perceptor2SASAdapterImpl();
        adapter.getLevel().addNode(adapter);

        OntologyHandler ontologyHandler = new OntologyHandler();

        LevelHolder levelHolder = new LevelHolder();
        levelHolder.addLevel(adapter.getLevel());


        SASOntologyProcessor ontologyProcessor = new SASOntologyProcessor(levelHolder, ontologyHandler);
        ontologyProcessor.process();

        SASHierarchyProcessor sasHierarchyProcessor = new SASHierarchyProcessor(levelHolder);
        sasHierarchyProcessor.process();

        //Ball ball = new Ball("1.0 2.0");
        //adapter.setBall(ball);

        BodyState bodyState = new BodyState();
        adapter.setBodystate(bodyState);
        Line line = new Line("l","1.0 2.0");
        List<NavigatingLandmark> landmarkList = new ArrayList<>();
        landmarkList.add(line);
        adapter.setNavigatingLandmarks(landmarkList);


        CoordinateCenter coordinateCenter = new CoordinateCenterC();
        coordinateCenter.setX(0.0);
        coordinateCenter.setY(0.0);
        adapter.setCoordinateCenter(coordinateCenter);

        sasHierarchyProcessor.process();

        SASOntologyNotifyProcessor ontologyNotifyProcessor = new SASOntologyNotifyProcessor(levelHolder, ontologyHandler);
        ontologyNotifyProcessor.process();

        ontologyProcessor.process();
    }
}
