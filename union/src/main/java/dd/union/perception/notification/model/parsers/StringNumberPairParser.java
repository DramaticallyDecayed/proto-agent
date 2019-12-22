package dd.union.perception.notification.model.parsers;

/**
 * Created by slebedev on 29.06.2017.
 */
public class StringNumberPairParser implements Parsing<String[]> {

    public static final String SPLITTER = "    ";

    @Override
    public String[] parse(String value) {
        return value.split(SPLITTER);
    }
}
