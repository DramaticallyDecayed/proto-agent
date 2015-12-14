package dd.soccer.calculationdependencies;

import common.Dependency;
import dd.protosas.computability.NodeSpecification;
import dd.protosas.computation.Level;
import dd.protosas.computation.levelnode.IdentNode;
import dd.protosas.computation.levelnode.NodeProcessor;
import dd.soccer.perception.Perceptor;
import dd.soccer.perception.perceptingobjects.*;


import java.util.logging.Logger;

/**
 * Created by Sergey on 11.12.2015.
 */
public class ManualCalculationDependencyGenerator {

    public static void initialize(Perceptor perceptor, Level level) {

        /* NodeSpecification can be null because we manually set calculation dependencies */


        NodeProcessor navigationObjIdentifierProcessor = new NodeProcessor() {

            Logger logger = Logger.getLogger("Navigation objIdentifier processor");

            @Override
            public void create() {
                logger.log(java.util.logging.Level.INFO, "here we create navigation object");
            }

            @Override
            public void update() {
                logger.log(java.util.logging.Level.INFO, "here we update navigation object");
            }
        };

        Dependency[] base = new Dependency[]{
                new Dependency(Flag.class)
        };

        Dependency derivative = new Dependency(NavigatingLandmark.class);

        NodeSpecification spec = new NodeSpecification(base, derivative) {
            @Override
            public IdentNode createNode() {
                return new IdentNode(this, navigationObjIdentifierProcessor);
            }
        };


        perceptor.getPublisher()
                .subscribe(
                        new Dependency(NavigatingLandmark.class),
                        level.getLevelTransmitter());


    }
}
