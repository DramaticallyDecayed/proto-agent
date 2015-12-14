package dd.soccer;

import common.Dependency;
import dd.protosas.computation.Level;
import dd.protosas.computation.levelnode.IdentNode;
import dd.protosas.computability.NodeSpecification;
import dd.soccer.sas.computability.PlayerIdentificationNode;
import dd.soccer.sas.presentation.IdentifiedPlayerIdent;
import dd.soccer.sas.presentation.Player;

/**
 * Created by Sergey on 25.09.2015.
 */

/**
 * TODO: data representation and data access
 * 1. Relation as a method for those substances that shouldn't have properties. They can be realized with
 * the help of regular object references and methods for setting and removing those references;
 * 2. Relation as an object for those substances that should have properties. They can be realize with the
 * help of double dispatch technique. When inside one object we get object of a relation and call method
 * with this object as a parameter and as a return receive a set of other objects.
 * 3. Only within CN's process frame the corresponding element identity can be rewritten. All other CNs
 * only read the shared element identity's current view. The problem is how to actualize this shared copy
 * of element identity's view at CNs that use it.
 * <p>
 * <p>
 * TODO: process flow
 * 1. Somehow use generators to organize iterative element processing
 * <p>
 * TODO:control flow
 * <p>
 * <p>
 * <p>
 * TODO: ideas
 * 1. CN - is a unit of calculation process, whereas ElementIdent can be considered as a functional
 * representation, a kind of an element proxy, that really rewrites element's state. Element itself
 * is a representation of element at the current moment of time.
 */
public class Main {

    public static void main(String... args) {

        PlayerSensor playerSensor = new PlayerSensor();
        Level level_0 = new Level(0);

        level_0.addSpec(new NodeSpecification(
                new Dependency[]{new Dependency(Player.class)},
                new Dependency(IdentifiedPlayerIdent.class)) {
            @Override
            public IdentNode createNode() {
                return new PlayerIdentificationNode(this);
            }
        });

        for (int i = 0; i < 5; i++) {
            level_0.push(playerSensor.sense());
            level_0.process();
        }
        System.out.println("Fin");
    }

}
