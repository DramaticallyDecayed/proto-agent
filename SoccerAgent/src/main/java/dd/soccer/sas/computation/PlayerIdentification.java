package dd.soccer.sas.computation;

import common.Dependency;
import commonmodel.ElementState;
import dd.protosas.presentation.ElementIdent;
import dd.protosas.computation.levelnode.NodeProcessor;
import dd.soccer.sas.presentation.PlayerIdent;
import dd.soccer.sas.presentation.Player;

import java.util.List;

/**
 * Created by Sergey on 05.10.2015.
 */
public class PlayerIdentification extends NodeProcessor {

    @Override
    public void create() {
        update();
    }

    @Override
    public void update() {
        List<ElementState> playerElementIdents = getRegister().getBaseInput().get(new Dependency(Player.class));
        List<ElementState> knownPlayers = getRegister().getDerivative();

        for (ElementState ei : playerElementIdents) {
            Player p = (Player)ei;

            boolean updated = false;
            if (knownPlayers.isEmpty()) {
                knownPlayers.add(createPlayer(p));
            } else {
                for (ElementState state : knownPlayers) {
                    int num = ((Player)state).getNumber();
                    if (num == p.getNumber()) {
                        updated = true;
                        break;
                    }
                }
                if (!updated) {
                    knownPlayers.add(createPlayer(p));
                }
            }
        }
    }

    private Player createPlayer(Player newPlayer){
        return new Player(newPlayer.getNumber());
    }


}
