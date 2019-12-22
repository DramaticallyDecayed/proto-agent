package dd.union.perception.notification.model.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by slebedev on 15.06.2017.
 */
public class LocationParser implements Parsing<List<String>> {

    private static final String[] FACILITY =
            new String[]{"MAINTENANCE FACILITY"};
            //new String[]{"STRATEGIC WEAPONS FACILITY","MAINTENANCE FACILITY"};
    private static Pattern namePattern =  Pattern.compile("^[^\\/]*");


    @Override
    public List<String> parse(String value) {
        List<String> result = new ArrayList<>();

        String facility = null;
        for(String knownFacility : FACILITY) {
            if(value.contains(knownFacility)){
                facility = knownFacility;
                break;
            }
        }

        if (facility != null) {
            value = value.replace(facility + ", ", "");
        }

        findFragment(namePattern, value, result);
        result.addAll(new PointParser().parse(value));

        if (facility != null) {
            result.add(facility);
        }

        return result;
    }

    private void findFragment(Pattern pattern, String value, List<String> result) {
        Matcher matcher = pattern.matcher(value);
        while(matcher.find()){
            result.add(matcher.group());
        }
    }
}
