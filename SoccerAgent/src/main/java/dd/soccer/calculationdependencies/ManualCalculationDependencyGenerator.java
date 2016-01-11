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

            private Logger logger = Logger.getLogger("navigationObjIdentifierProcessor");

            @Override
            public void create() {
                logger.info("here we create navigation object");
            }

            @Override
            public void update() {
                logger.info("here we update navigation object");
            }
        };

        Dependency[] base = new Dependency[]{
                new Dependency(NavigatingLandmark.class)
        };

        Dependency derivative = new Dependency(NavigatingLandmark.class);

        NodeSpecification spec = new NodeSpecification(base, derivative) {
            private Logger logger = Logger.getLogger("navigationObjSpecification");
            @Override
            public IdentNode createNode() {
                logger.info("create node");
                return new IdentNode(this, navigationObjIdentifierProcessor);
            }
        };

        perceptor.getPublisher()
                .subscribe(
                        new Dependency(Flag.class),
                        level.getLevelTransmitter());

        level.addSpec(spec);
    }
}
