package dd.soccer.sas.presentation;


import dd.protosas.presentation.Element;

/**
 * P2IPRelation - player to identified player relation
 *
 * Created by Sergey on 05.10.2015.
 */
public class P2IPRelation extends Element {

    private final static String NAME = "player to identified player relation";
    private IdentifiedPlayerIdent identifiedPlayer;
    private Player player;


    public P2IPRelation() {
        super(NAME);
    }
}
