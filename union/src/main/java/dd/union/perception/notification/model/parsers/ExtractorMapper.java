package dd.union.perception.notification.model.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by slebedev on 13.06.2017.
 */
public class ExtractorMapper {

    private static Map<String, Pattern> mapper = new HashMap<>();

    static {
        mapper.put("2", Pattern.compile("\\d+\\-\\d+\\/\\d+"));
    }

    public static List<String> getMatcher(String marker, String line) {
        List<String> list = new ArrayList<>();
        Matcher matcher = mapper.get(marker).matcher(line);
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }
}
