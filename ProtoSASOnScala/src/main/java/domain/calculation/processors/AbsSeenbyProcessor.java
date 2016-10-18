package domain.calculation.processors;

import dd.sas.pipeline.calculation.processors.associativeprocessors.AssociativeProcessor;
import domain.worldmodel.relations.AbsSeeby;
import domain.worldmodel.worldobjects.CoordinateCenter;
import domain.worldmodel.worldobjects.Ego;
import domain.worldmodel.worldobjects.Viewer;

import java.util.function.BiFunction;

/**
 * Created by Sergey on 12.10.2016.
 */
public class AbsSeenbyProcessor extends AssociativeProcessor<CoordinateCenter, Viewer> {
    @Override
    public <D extends CoordinateCenter, R extends Viewer> AbsSeeby<D, R> customCommonExpression(D d, R r) {
        System.out.println(this.getClass().getSimpleName()
                + " "
                + d.name()
                + " "
                + r.name()
        );
        return new AbsSeeby<>(d, r);
    }

    public BiFunction<CoordinateCenter, Ego, AbsSeeby<CoordinateCenter, Ego>> coordinatecenter_abssee_ego() {
        return (CoordinateCenter coordinateCenter, Ego ego) -> customCommonExpression(coordinateCenter, ego);
    }


}
