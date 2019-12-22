package dd.union.perception.notification.model.parsers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slebedev on 23.06.2017.
 */
public class StringCollectionParcer implements Parsing<List<String>> {

    private static String splitter = ",";

    @Override
    public List<String> parse(String value) {
        List<String> strings = new ArrayList<>();
        for(String chunk : value.split(splitter)){
            strings.add(chunk.trim());
        }
        return strings;
    }
}
