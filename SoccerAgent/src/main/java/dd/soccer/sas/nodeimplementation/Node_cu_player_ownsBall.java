package dd.soccer.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.soccer.common.Constants;
import dd.soccer.sas.objectproperty.Abs_see;
import dd.soccer.sas.objectproperty.Inverse_abs_see;
import dd.soccer.sas.worldentity.Ball;
import dd.soccer.sas.worldentity.BallC;
import dd.soccer.sas.worldentity.Player;
import dd.soccer.sas.worldentity.PlayerC;

import java.util.stream.Collectors;
import java.util.List;

/**
 * Created by Sergey on 25.07.2016.
 */
public class Node_cu_player_ownsBall extends dd.soccer.sas.computation.node.Node_cu_player_ownsBall {
    public Node_cu_player_ownsBall(Level level) {
        super(level);
    }

    @Override
    public CalculationResult customProcess() {
        List<Inverse_abs_see> iases = getInverse_abs_seeList().stream().filter(x -> (x.getDomain() instanceof PlayerC)).collect(Collectors.toList());

        Ball ball = null;

        List<Abs_see> abs_sees = getAbs_seeList()
                .stream()
                .filter(x -> (x.getRange() instanceof BallC))
                .collect(Collectors.toList());

        if (!abs_sees.isEmpty()) {
            ball = (Ball) abs_sees.get(0).getRange();
        }

        if (ball != null && !iases.isEmpty()) {

            List<Player> players = iases
                    .stream()
                    .map(x -> (Player) x.getDomain())
                    .collect(Collectors.toList());


            for (Player player : players) {

                double d = Math.sqrt(
                        Math.pow(player.getX() - ball.getX(), 2)
                                +
                                Math.pow(player.getY() - ball.getY(), 2)
                );

                if (d <= Constants.POSSESSING_AREA.getValue()) {
                    System.out.println("Player " + player.getNumber() + " posses ball");
                } else {
                    System.out.println("Player " + player.getNumber() + " does not posses ball");
                }
            }
        }
        System.out.println("-===========================================-");
        return (CalculationResult.UNKNOWN);
    }
}
