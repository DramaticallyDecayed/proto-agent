package dd.union.perception.notification.model.types;


import dd.union.perception.notification.model.parsers.StringCollectionParcer;

import java.util.List;

/**
 * Хранение списка строковых типов
 *
 * Created by slebedev on 23.06.2017.
 */
public class StringCollectionType extends AbstractAtomType<List<String>> {

    public StringCollectionType(String path, String value) {
        super(path, value, new StringCollectionParcer().parse(value));
    }

    @Override
    public String translate() {
        return getValue();
    }
}
