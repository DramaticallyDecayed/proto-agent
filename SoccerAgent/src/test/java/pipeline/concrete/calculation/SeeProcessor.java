package pipeline.concrete.calculation;

import dd.sas.pipeline.calculation.processor.associativeprocessors.AssociativePlainProcessor;
import pipeline.concrete.worldmodel.*;

import java.util.function.BiFunction;

/**
 * Created by Sergey on 13.09.2016.
 */
public class SeeProcessor extends AssociativePlainProcessor<Viewer, VisibleObject> {


    @Override
    public <D extends Viewer, R extends VisibleObject> See<D, R> doStaffCommon(D d, R r) {
        System.out.println("N: " + d + " " + r);
        return generator(See::new, d, r);
    }


    public BiFunction<Ego, Ball, See<Ego, Ball>> ego_see_ball() {
        return (ego, ball) -> doStaffCommon(ego, ball);
    }

    public BiFunction<Ego, Player, See<Ego, Player>> ego_see_player() {
        return (ego, player) -> doStaffCommon(ego, player);
    }

    public BiFunction<Ego, Landmark, See<Ego, Landmark>> ego_see_landmark() {
        return (ego, landmark) -> doStaffCommon(ego, landmark);
    }

}
