package domain.calculation.processors;

import dd.sas.pipeline.calculation.processors.associativeprocessors.AssociativeProcessor;
import domain.worldmodel.relations.See;
import domain.worldmodel.worldobjects.*;

import java.util.function.BiFunction;

/**
 * Created by Sergey on 02.10.2016.
 */
public class SeeProcessor extends AssociativeProcessor<Viewer, VisibleObject> {

    @Override
    public <D extends Viewer, R extends VisibleObject>
    See<D, R> expression(D d, R r) {
        System.out.println(this.getClass().getSimpleName() + " " + d + " " + r);
        return new See<>(d, r);
    }

    public BiFunction<Ego, Ball, See<Ego, Ball>> ego_see_ball() {
        return (Ego ego, Ball ball) -> expression(ego, ball);
    }

    public BiFunction<Ego, Player, See<Ego, Player>> ego_see_player() {
        return (Ego ego, Player player) -> expression(ego, player);
    }

}
