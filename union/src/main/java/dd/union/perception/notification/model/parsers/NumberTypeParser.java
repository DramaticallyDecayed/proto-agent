package dd.union.perception.notification.model.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by slebedev on 29.06.2017.
 */
public class NumberTypeParser implements Parsing<Double> {

    private Pattern pattern = Pattern.compile("[+|-]?\\d+(.\\d+)?");

    @Override
    public Double parse(String value) {
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group());
        }
        return null;
    }
}
