import ch.qos.logback.classic.Level;
import dd.ontologypart.OntologyHandler;
import dd.sas.SAS;
import dd.soccer.perception.perceptingobjects.BodyState;
import dd.soccer.perception.perceptingobjects.Line;
import dd.soccer.perception.perceptingobjects.NavigatingLandmark;
import dd.soccer.sas.Perceptor2SASAdapterImpl;
import dd.soccer.sas.worldentity.CoordinateCenter;
import dd.soccer.sas.worldentity.CoordinateCenterC;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 30.05.2016.
 */
public class SASTest {

    public static void setLoggingLevel(Level level) {
        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        root.setLevel(level);
    }

    @Test
    public void genericSASTest() {

        setLoggingLevel(Level.OFF);

        OntologyHandler ontologyHandler = new OntologyHandler();
        SAS sas = new SAS(ontologyHandler);
        Perceptor2SASAdapterImpl adapter = new Perceptor2SASAdapterImpl();
        adapter.getLevel().addNode(adapter);
        sas.getLevelHolder().addLevel(adapter.getLevel());

        long start = System.nanoTime();
        sas.cycle();
        long end = System.nanoTime();
        System.out.println(end - start);

        BodyState bodyState = new BodyState();
        adapter.setBodystate(bodyState);
        Line line = new Line("l", "1.0 2.0");
        List<NavigatingLandmark> landmarkList = new ArrayList<>();
        landmarkList.add(line);
        adapter.setNavigatingLandmarks(landmarkList);


        CoordinateCenter coordinateCenter = new CoordinateCenterC();
        coordinateCenter.setX(0.0);
        coordinateCenter.setY(0.0);
        adapter.setCoordinateCenter(coordinateCenter);

        start = System.nanoTime();
        sas.cycle();
        end = System.nanoTime();
        System.out.println(end - start);
    }
}
