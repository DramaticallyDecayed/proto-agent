package dd.union.perception.notification.model.types;

import dd.union.perception.notification.model.parsers.ReferenceParser;

import java.util.List;

/**
 * Хранение списка ссылок на "родительские" уведомления
 * <p/>
 * Created by slebedev on 23.06.2017.
 */
public class ReferenceType extends AbstractAtomType<List<IdentNumberType>> {

    public ReferenceType(String path, String value) {
        super(path, value, new ReferenceParser().parse(value));
    }

    @Override
    public String translate() {
        StringBuilder translation = null;
        for (IdentNumberType number : getTypedValue()) {
            if (translation == null) {
                translation = new StringBuilder();
                translation.append(number.translate());
            } else {
                translation.append(", " + number.translate());
            }
        }
        return translation.toString();
    }
}
