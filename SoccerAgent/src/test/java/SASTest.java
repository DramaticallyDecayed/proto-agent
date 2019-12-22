import ch.qos.logback.classic.Level;
import dd.ontologypart.OntologyHandler;
import dd.sas.SAS;
import dd.sas.computation.WrappingGetter;
import dd.sas.presentation.WorldEntity;
import dd.soccer.perception.perceptingobjects.BodyState;
import dd.soccer.perception.perceptingobjects.Line;
import dd.soccer.perception.perceptingobjects.NavigatingLandmark;
import dd.soccer.sas.Perceptor2SASAdapterImpl;
import dd.soccer.sas.computation.node.Node_cu_Ball;
import dd.soccer.sas.computation.node.Node_cu_Landmark;
import dd.soccer.sas.computation.node.Node_cu_see;
import dd.soccer.sas.worldentity.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergey on 30.05.2016.
 */
public class SASTest {

//    public static void setLoggingLevel(Level level) {
//        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
//        root.setLevel(level);
//    }

    @Test
    public void testSeparateIdeas() {


        Node_cu_Ball n = new Node_cu_Ball(null);
        List<Ball> balls = new ArrayList<>();
        Ball b = new BallC();
        b.setX(100.0);
        balls.add(b);
        //n.setBallList(balls);

        Node_cu_Landmark nl = new Node_cu_Landmark(null);

        Node_cu_see ncs = new Node_cu_see(null);


        Map<Class, List<WrappingGetter>> nodes = new HashMap<>();
        nodes.put(VisibleObject.class, new ArrayList<>());

        Map<Class, List<? extends WorldEntity>> bases = new HashMap<>();

        nodes.get(VisibleObject.class).add(n::getBallList);
        nodes.get(VisibleObject.class).add(nl::getLandmarkList);


        bases.put(VisibleObject.class, new ArrayList<>());
        bases.get(VisibleObject.class).addAll(nodes.get(VisibleObject.class).get(0).getObjectList());


        System.out.println((List<VisibleObject>)bases.get(VisibleObject.class));

        ncs.setDonor(n);


        ncs.pullData();
    }

    @Test
    public void genericSASTest() {

//        setLoggingLevel(Level.OFF);

        OntologyHandler ontologyHandler = new OntologyHandler();
        ontologyHandler.arm();

        SAS sas = new SAS(ontologyHandler);
        Perceptor2SASAdapterImpl adapter = new Perceptor2SASAdapterImpl();
        adapter.getLevel().addNode(adapter);
        sas.getLevelHolder().addLevel(adapter.getLevel());

        long start = System.nanoTime();
        sas.cycle();
        long end = System.nanoTime();
        System.out.println(end - start);

        fillAdapterWithSomeObjects_1(adapter);

        start = System.nanoTime();
        sas.cycle();
        end = System.nanoTime();
        System.out.println(end - start);

        start = System.nanoTime();
        sas.cycle();
        end = System.nanoTime();
        System.out.println(end - start);

        fillAdapterWithSomeObjects_2(adapter);

        start = System.nanoTime();
        sas.cycle();
        end = System.nanoTime();
        System.out.println(end - start);


        start = System.nanoTime();
        sas.cycle();
        end = System.nanoTime();
        System.out.println(end - start);
    }

    private void fillAdapterWithSomeObjects_1(Perceptor2SASAdapterImpl adapter) {
        BodyState bodyState = new BodyState();
        adapter.setBodystate(bodyState);

        CoordinateCenter coordinateCenter = new CoordinateCenterC();
        coordinateCenter.setX(0.0);
        coordinateCenter.setY(0.0);
        adapter.setCoordinateCenter(coordinateCenter);
    }

    private void fillAdapterWithSomeObjects_2(Perceptor2SASAdapterImpl adapter) {
        Line line = new Line("l", "1.0 2.0");
        List<NavigatingLandmark> landmarkList = new ArrayList<>();
        landmarkList.add(line);
        adapter.setNavigatingLandmarks(landmarkList);
    }
}
