package dd.soccer.sas.computation;

import common.Dependency;
import commonmodel.ElementState;
import dd.protosas.computability.NodeSpecification;
import dd.protosas.computation.Level;
import dd.protosas.computation.levelnode.IdentNode;
import dd.protosas.computation.levelnode.NodeProcessor;
import dd.protosas.presentation.ElementIdent;
import dd.soccer.perception.perceptingobjects.BodyState;
import dd.soccer.sas.computation.egonodecomputation.EgoModelProcessor;
import dd.soccer.sas.presentation.soccerobjects.EgoState;

/**
 * Created by Sergey on 26.10.2015.
 */
public class Level0Fabric {

    public static Level createLevel0() {
        Level level0 = new Level(0);


        Dependency[] base = new Dependency[]{
                new Dependency(BodyState.class)
                //here must be some additional classes but firstly switch off complete function
        };
        Dependency derivative = new Dependency(EgoState.class);

        NodeProcessor nodeProcessor = new NodeProcessor() {

            private EgoModelProcessor egoModelProcessor;

            @Override
            public void create() {
                if (egoModelProcessor == null) {
                    egoModelProcessor = new EgoModelProcessor(new Level(0));
                }

                BodyState bodyState = (BodyState) getRegister().getBaseInput().get(BodyState.class.getName()).get(0);
                egoModelProcessor.push(bodyState);

                egoModelProcessor.cycle();

                if (egoModelProcessor.getOutstate() != null) {
                    getRegister().addDerivative(egoModelProcessor.getOutstate());
                }

                getRegister().clearResterRecord();
            }

            @Override
            public void update() {

                ElementState child = getRegister().getDerivative().get(0);
                BodyState bodyState = (BodyState) getRegister().getBaseInput().get(BodyState.class.getName()).get(0);

                egoModelProcessor.push(bodyState);

                egoModelProcessor.cycle();

                getRegister().update(egoModelProcessor.getOutstate());

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
