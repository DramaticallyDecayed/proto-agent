package dd.union.perception.notification.recognition.translators;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by slebedev on 15.06.2017.
 */
public class DateTranslator implements Translation<Date>{
    private static String format = "dd-MMM-yyyy";
    private static Locale locale = new Locale("ru");

    @Override
    public String translate(Date value) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        return sdf.format(value).toUpperCase();
    }
}
