package dd.soccer.sas.computation;

import dd.protosas.computability.NodeSpecification;
import dd.protosas.computation.Level;
import dd.protosas.computation.levelnode.IdentNode;
import dd.protosas.computation.levelnode.NodeProcessor;
import dd.soccer.perception.perceptingobjects.Flag;
import dd.soccer.perception.perceptingobjects.Line;
import dd.soccer.sas.presentation.Player;
import dd.soccer.sas.presentation.soccerobjects.Ego;

/**
 * Created by Sergey on 26.10.2015.
 */
public class Level0Fabric {

    public static Level createLevel0() {
        Level level0 = new Level(0);


        String[] base = new String[]{Flag.class.getName(),Line.class.getName(),Player.class.getName()};
        String derivative = Ego.class.getName();

        NodeProcessor nodeProcessor = new NodeProcessor() {

            @Override
            public void create() {
                System.out.println("Trying to create EGO");
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


        level0.addSpec(nodeSpecification);

        return level0;
    }

}
