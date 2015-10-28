package dd.soccer.sas.computation;

import dd.protosas.computability.NodeSpecification;
import dd.protosas.computation.Level;
import dd.protosas.computation.levelnode.IdentNode;
import dd.protosas.computation.levelnode.NodeProcessor;
import dd.protosas.presentation.ElementIdent;
import dd.soccer.perception.perceptingobjects.Ball;
import dd.soccer.perception.perceptingobjects.Player;
import dd.soccer.sas.presentation.soccerrelations.BallOwner;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by Sergey on 21.10.2015.
 */
public class Level1Fabric {

    public static Level createLevel1(){

        Level level1 = new Level(1);


        String[] base = new String[]{Ball.class.getName(),Player.class.getName()};
        String derivative = BallOwner.class.getName();


        NodeProcessor nodeProcessor = new NodeProcessor() {
            @Override
            public void create() {
                Ball ball = (Ball) getRegister().getBaseInput().get(Ball.class.getName()).get(0).getElement();
                List<ElementIdent> players = getRegister().getBaseInput().get(Player.class.getName());
                TreeMap<Double, Player> playersToBall = new TreeMap<>();
                for(ElementIdent ei : players){
                    Player p = (Player) ei.getElement();
                    playersToBall.put(calculateDistance(ball,p),p);
                }

                for(Double k : playersToBall.keySet()){
                    System.out.println("key = " + k + " p: " + playersToBall.get(k));
                }

                BallOwner ballOwner = new BallOwner(playersToBall.firstEntry().getValue(), ball);
                ElementIdent<BallOwner> elementIdent = new ElementIdent<BallOwner>(ballOwner);
                getRegister().addChild(elementIdent);
                System.out.println(playersToBall.firstEntry().getValue() + "\n" + ball);
            }

            private double calculateDistance(Ball ball, Player player){
                return Math.sqrt(
                        Math.pow(ball.getDistance(), 2) + Math.pow(player.getDistance(), 2)-
                        2 * ball.getDistance() * player.getDistance() *
                        Math.cos(Math.toRadians(ball.getDirection()) - Math.toRadians(player.getDirection()))
                );
            }

            @Override
            public void update() {

            }
        };

        NodeSpecification nodeSpecification = new NodeSpecification(base, derivative) {
            @Override
            public IdentNode createNode() {
                return new IdentNode(this, nodeProcessor);
            }
        };

        level1.addSpec(nodeSpecification);

        return level1;
    }
}
