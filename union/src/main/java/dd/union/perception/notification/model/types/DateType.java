package dd.union.perception.notification.model.types;


import dd.union.perception.notification.model.parsers.DateParser;
import dd.union.perception.notification.recognition.translators.DateTranslator;

import java.util.Date;

/**
 * Хранение даты
 * <p>
 * Created by slebedev on 15.06.2017.
 */
public class DateType extends AbstractAtomType<Date> {

    public DateType(String path, String value) {
        super(path, value, new DateParser().parse(value));
    }

    @Override
    public String translate() {
        return new DateTranslator().translate(getTypedValue());
    }


}
