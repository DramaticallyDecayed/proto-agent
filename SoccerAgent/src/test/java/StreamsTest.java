
import dd.soccer.sas.worldentity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Sergey on 16.08.2016.
 */
public class StreamsTest {

    public static void main(String...args){
        List<Landmark> landmarks = new ArrayList<>();
        landmarks.add(new LineC());
        landmarks.add(new FlagC());

        List<Ball> balls = new ArrayList<>();
        balls.add(new BallC());

        List<Double> doubles = new ArrayList<>();
        doubles.add(new Double(0.0));

        Stream<Landmark> landmarkStream = landmarks.stream();
        Stream<Ball> ballStream = balls.stream();


        Stream.of(ballStream, landmarkStream).forEach(x -> System.out.println(x));

    }
}
