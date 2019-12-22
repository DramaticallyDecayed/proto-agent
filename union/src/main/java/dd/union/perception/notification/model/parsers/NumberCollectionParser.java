package dd.union.perception.notification.model.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by slebedev on 15.06.2017.
 */
public class NumberCollectionParser implements Parsing<List<Integer>> {

    private static Pattern singedNumberPattern = Pattern.compile("[+|-]?\\d+");
    private static Pattern unsignedNumberPattern = Pattern.compile("\\d+");
    private Pattern pattern;

    public NumberCollectionParser(boolean signed){
        if(signed) {
            pattern = singedNumberPattern;
        }else {
            pattern = unsignedNumberPattern;
        }
    }

    @Override
    public List<Integer> parse(String value) {
        List<Integer> result = new ArrayList<>();
        Matcher matcher = pattern.matcher(value);
        while(matcher.find()){
            result.add(Integer.parseInt(matcher.group()));
        }
        return result;
    }
}
