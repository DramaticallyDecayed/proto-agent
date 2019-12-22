package dd.union.perception.notification.model.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by slebedev on 15.06.2017.
 */
public class CoordinateParser implements Parsing<Double> {

    private static Pattern numberPattern = Pattern.compile("\\d+");
    private static Pattern letterPattern = Pattern.compile("\\w");

    @Override
    public Double parse(String value) {
        return getDecimalCoordinate(getNumbers(value), getLetter(value));
    }

    private Double getDecimalCoordinate(Double[] numbers, String letter) {
        double seconds = (numbers[1] * 60.0d + numbers[2]);
        double fractional = seconds / 3600.0d;
        double decimalDegrees = numbers[0] + fractional;
        switch (letter) {
        case "S":
        case "W":
            decimalDegrees = -decimalDegrees;
            break;
        }
        return decimalDegrees;
    }

    private String getLetter(String value) {
        Matcher matcher = letterPattern.matcher(value);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    private Double[] getNumbers(String value) {
        Double[] numbers = new Double[]{ 0.0d, 0.0d, 0.0d };
        Matcher matcher = numberPattern.matcher(value);
        for (int i = 0; matcher.find(); i++) {
            numbers[i] = Double.parseDouble(matcher.group());
        }
        return numbers;
    }
}
