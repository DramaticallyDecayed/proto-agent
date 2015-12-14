package dd.soccer.sas.presentation;

import dd.protosas.presentation.ElementIdent;
/**
 * Created by Sergey on 30.09.2015.
 */
public class PlayerIdent extends ElementIdent<Player> {

    public PlayerIdent() {
    }

    public PlayerIdent(Player element) {
        super(element);
    }

    public PlayerIdent(Player element, String msg) {
        super(element);
        System.out.println(msg);
    }

    @Override
    public void updateElement(Player element) {
        super.updateElement(element);
        System.out.println("Updated: " + element.getNumber());
    }

}
