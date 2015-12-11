package dd.soccer.calculationdependencies;

import dd.protosas.computation.levelnode.NodeProcessor;
import dd.soccer.common.Topic;
import dd.soccer.perception.Perceptor;
import dd.soccer.perception.perceptingobjects.ObservableSoccerObject;
import dd.soccer.sas.computation.nodes.NavigationObjIdentifier;

/**
 * Created by Sergey on 11.12.2015.
 */
public class ManualCalculationDependencyGenerator {

    public static void initialize(Perceptor perceptor) {

        /* NodeSpecification can be null because we manually set calculation dependencies */


        NodeProcessor navigationObjIdentifierProcessor = new NodeProcessor() {
            @Override
            public void create() {

            }

            @Override
            public void update() {

            }
        };

        perceptor.getPublisher()
                .subscribe(
                        new Topic(ObservableSoccerObject.class)
                        , new NavigationObjIdentifier(null, navigationObjIdentifierProcessor));
    }
}
