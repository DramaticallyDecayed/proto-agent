import domain.worldmodel.worldobjects.BallI;
import domain.worldmodel.worldobjects.Nothing;

/**
 * Created by Sergey on 12.01.2017.
 */
public class TestNothing {


    public static void main(String...args){
        Nothing n = new Nothing();
        f(n);
    }

    public static void f(BallI ball){
        System.out.println(ball);
    }


}
