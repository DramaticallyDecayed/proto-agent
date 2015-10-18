package dd.soccer.sas.computation;

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
        List<ElementIdent> playerElementIdents = getRegister().getBaseInput().get(Player.NAME);
        List<ElementIdent> knownPlayers = getRegister().getChildren();

        for (ElementIdent ei : playerElementIdents) {
            Player p = (Player)ei.getElement();

            boolean updated = false;
            if (knownPlayers.isEmpty()) {
                knownPlayers.add(createPlayer(p));
            } else {
                for (ElementIdent ident : knownPlayers) {
                    int num = ((Player)ident.getElement()).getNumber();
                    if (num == p.getNumber()) {
                        updated = true;
                        ident.updateElement(ei.getElement());
                        break;
                    }
                }
                if (!updated) {
                    knownPlayers.add(createPlayer(p));
                }
            }
        }
    }

    private PlayerIdent createPlayer(Player newPlayer){
        return new PlayerIdent(newPlayer, "Creat player: " + newPlayer.getNumber());
    }


}
