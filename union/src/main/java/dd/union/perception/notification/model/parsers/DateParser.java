package dd.union.perception.notification.model.parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by slebedev on 13.06.2017.
 */
public class DateParser implements Parsing<Date> {

    private static Logger logger = LoggerFactory.getLogger(DateParser.class);
    private static String format = "dd-MMM-yyyy";
    private static Locale locale = new Locale("en");

    @Override
    public Date parse(String value) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        try {
            return sdf.parse(value);
        } catch (ParseException e) {
            logger.debug("В процессе синтаксического разбора даты возникла ошибка", e);
        }
        return null;
    }
}
