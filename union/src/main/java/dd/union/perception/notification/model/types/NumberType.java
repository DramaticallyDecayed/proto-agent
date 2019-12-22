package dd.union.perception.notification.model.types;


import dd.union.perception.notification.model.parsers.NumberTypeParser;

/**
 * Хранение вещественного числа
 *
 * Created by slebedev on 29.06.2017.
 */
public class NumberType extends AbstractAtomType<Double>{

    public NumberType(String path, String value) {
        super(path, value, new NumberTypeParser().parse(value));
    }

    @Override
    public String translate() {
        return getValue();
    }
}
