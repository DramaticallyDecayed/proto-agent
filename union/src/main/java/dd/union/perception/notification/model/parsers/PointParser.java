package dd.union.perception.notification.model.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by slebedev on 15.06.2017.
 */
public class PointParser implements Parsing<List<String>>{

    private static Pattern coordinate = Pattern.compile("(\\d+\\-\\d+\\-\\d+\\w)|(\\d+\\-\\d+\\w)");

    @Override
    public List<String> parse(String value) {
        List<String> result = new ArrayList<>();
        findFragment(coordinate, value, result);
        return result;
    }


    private void findFragment(Pattern pattern, String value, List<String> result) {
        Matcher matcher = pattern.matcher(value);
        while(matcher.find()){
            result.add(matcher.group());
        }
    }
}
