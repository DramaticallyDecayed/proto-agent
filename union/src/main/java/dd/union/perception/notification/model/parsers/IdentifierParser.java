package dd.union.perception.notification.model.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by slebedev on 23.06.2017.
 */
public class IdentifierParser implements Parsing<String> {

    private static Pattern pattern = Pattern.compile("[A-Z]\\S+");

    @Override
    public String parse(String value) {
        Matcher matcher = pattern.matcher(value);
        if(matcher.find()){
            return matcher.group();
        }
        return null;
    }
}
