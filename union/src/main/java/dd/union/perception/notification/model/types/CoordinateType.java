package dd.union.perception.notification.model.types;

import dd.union.perception.notification.model.parsers.CoordinateParser;
import dd.union.perception.notification.recognition.translators.CoordinateTranslator;

/**
 * Хранение координаты
 *
 * Created by slebedev on 15.06.2017.
 */
public class CoordinateType extends AbstractAtomType<Double> {

    public CoordinateType(String value) {
        super(null, value, new CoordinateParser().parse(value));
    }

    @Override
    public String translate() {
        return new CoordinateTranslator().translate(getValue());
    }
}
