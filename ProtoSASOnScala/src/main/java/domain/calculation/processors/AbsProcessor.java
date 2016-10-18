package domain.calculation.processors;

import dd.sas.pipeline.calculation.processors.associativeprocessors.AssociativeProcessor;
import domain.worldmodel.relations.Abs;
import domain.worldmodel.worldobjects.CoordinateCenter;
import domain.worldmodel.worldobjects.Landmark;
import domain.worldmodel.worldobjects.Point;

import java.util.function.BiFunction;

/**
 * Created by Sergey on 12.10.2016.
 */
public class AbsProcessor extends AssociativeProcessor<CoordinateCenter, Point> {

    @Override
    public <D extends CoordinateCenter, R extends Point>
    Abs<D, R> customCommonExpression(D d, R r) {
        System.out.println(this.getClass().getSimpleName()
                + " "
                + d.getClass().getSimpleName()
                + " "
                + r.getClass().getSimpleName());
        return new Abs<>(d, r);
    }

    public BiFunction<CoordinateCenter, Landmark, Abs<CoordinateCenter, Landmark>> coordinatecenter_abs_landmark() {
        return (CoordinateCenter coordinateCenter, Landmark landmark) -> customCommonExpression(coordinateCenter, landmark);
    }


}