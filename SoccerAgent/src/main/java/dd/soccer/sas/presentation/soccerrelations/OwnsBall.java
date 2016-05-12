package dd.soccer.sas.presentation.soccerrelations;

import commonmodel.ElementState;
import dd.soccer.perception.perceptingobjects.Ball;
import dd.soccer.perception.perceptingobjects.Player;


/**
 * Created by Sergey on 25.10.2015.
 */
public class OwnsBall extends ElementState{

    private Player player;
    private Ball ball;

    public OwnsBall(Player player, Ball ball){
        this.player = player;
        this.ball = ball;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }
}
