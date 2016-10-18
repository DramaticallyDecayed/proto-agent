package domain.calculation.processors;

import dd.sas.pipeline.calculation.processors.associativeprocessors.AssociativeProcessor;
import dd.sas.pipeline.worldmodel.Relation;
import domain.worldmodel.relations.See;
import domain.worldmodel.worldobjects.*;

import java.util.function.BiFunction;

/**
 * Created by Sergey on 02.10.2016.
 */
public class SeeProcessor extends AssociativeProcessor<Viewer, VisibleObject> {

    @Override
    public <D extends Viewer, R extends VisibleObject>
    See<D, R> customCommonExpression(D d, R r) {
        System.out.println(this.getClass().getSimpleName()
                + " "
                + d.getClass().getSimpleName()
                + " "
                + r.getClass().getSimpleName()
        );
        return new See(d, r);
    }

    public BiFunction<Ego, Ball, See<Ego, Ball>> ego_see_ball() {
        return (Ego ego, Ball ball) -> (See<Ego, Ball>) commonExpression(ego, ball);
    }

    public BiFunction<Ego, Player, See<Ego, Player>> ego_see_player() {
        return (Ego ego, Player player) -> (See<Ego, Player>) commonExpression(ego, player);
    }

    public BiFunction<Ego, Landmark, See<Ego, Landmark>> ego_see_landmark() {
        return (Ego ego, Landmark landmark) -> (See<Ego, Landmark>) commonExpression(ego, landmark);
    }

}
