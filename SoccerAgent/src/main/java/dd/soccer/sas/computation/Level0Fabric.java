package dd.soccer.sas.computation;

import dd.protosas.computability.NodeSpecification;
import dd.protosas.computation.Level;
import dd.protosas.computation.levelnode.IdentNode;
import dd.protosas.computation.levelnode.NodeProcessor;
import dd.protosas.presentation.ElementIdent;
import dd.soccer.perception.perceptingobjects.BodyState;
import dd.soccer.sas.presentation.soccerobjects.EgoState;

/**
 * Created by Sergey on 26.10.2015.
 */
public class Level0Fabric {

    public static Level createLevel0() {
        Level level0 = new Level(0);


        String[] base = new String[]{BodyState.class.getName()};
        String derivative = EgoState.class.getName();

        NodeProcessor nodeProcessor = new NodeProcessor() {

            @Override
            public void create() {
                BodyState bodyState = (BodyState) getRegister().getBaseInput().get(BodyState.class.getName()).get(0).getElement();
                getRegister().addChild(new ElementIdent(new EgoState(bodyState)));
            }

            @Override
            public void update() {
                ElementIdent child = getRegister().getChildren().get(0);
                BodyState bodyState = (BodyState) getRegister().getBaseInput().get(BodyState.class.getName()).get(0).getElement();
                child.updateElement(new EgoState(bodyState));
                getRegister().clearResterRecord();
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
