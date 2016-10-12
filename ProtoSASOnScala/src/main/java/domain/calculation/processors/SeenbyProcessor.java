package domain.calculation.processors;

import dd.sas.pipeline.calculation.processors.associativeprocessors.AssociativeProcessor;
import domain.worldmodel.relations.Seenby;
import domain.worldmodel.worldobjects.*;

import java.util.function.BiFunction;

/**
 * Created by Sergey on 12.10.2016.
 */
public class SeenbyProcessor extends AssociativeProcessor<VisibleObject, Viewer> {

    @Override
    public <D extends VisibleObject, R extends Viewer>
    Seenby<D, R> expression(D d, R r) {
        System.out.println(this.getClass().getSimpleName() + " " + d + " " + r);
        return new Seenby<>(d, r);
    }

    public BiFunction<Ball, Ego, Seenby<Ball, Ego>> ball_seenby_ego() {
        return (Ball ball, Ego ego) -> expression(ball, ego);
    }

    public BiFunction<Player, Ego, Seenby<Player, Ego>> player_seenby_ego() {
        return (Player player, Ego ego) -> expression(player, ego);
    }

}
