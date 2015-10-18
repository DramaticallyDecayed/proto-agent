package dd.soccer.sas.computability;

import dd.protosas.computation.levelnode.Node;
import dd.protosas.computability.NodeSpecification;
import dd.soccer.sas.computation.PlayerIdentification;

/**
 * Created by Sergey on 05.10.2015.
 */
public class PlayerIdentificationNode extends Node{

    public PlayerIdentificationNode(NodeSpecification spec) {
        super(spec, new PlayerIdentification());
    }

}
