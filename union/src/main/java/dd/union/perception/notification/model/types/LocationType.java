package dd.union.perception.notification.model.types;

import dd.union.perception.notification.model.parsers.LocationParser;

import java.util.List;

/**
 * Хранение информации о локации (тип-название-координаты)
 * <p>
 * Created by slebedev on 15.06.2017.
 */
public class LocationType extends AbstractType implements ILocation {

    private static String commaSplitter = ", ";
    private static String slashSplitter = "/ ";
    private PointType pointType;
    private String name;
    private String type;

    public LocationType(String path, String value) {
        super(path, value);
        List<String> values = new LocationParser().parse(getValue());
        name = values.get(0);
        pointType = new PointType(new CoordinateType(values.get(1)), new CoordinateType(values.get(2)));
        if (values.size() == 4) {
            type = values.get(3);
        }
    }

    @Override
    public String translate() {
        if (type != null) {
            return type + commaSplitter + name + slashSplitter + pointType.translate();
        } else {
            return name + slashSplitter + pointType.translate();
        }
    }

    public String getName() {
        return name;
    }

    public PointType getPointType() {
        return pointType;
    }

    public String getType() {
        return type;
    }

    @Override
    public String userString() {
        return getTypeAndName();
    }

    public String getTypeAndName() {
        if (type == null) {
            return name;
        } else {
            return type + commaSplitter + name;
        }
    }

    @Override
    public String[][] getNames() {
        if (type != null) {
            return new String[][]{{getName()}, {getType()}};
        } else {
            return new String[][]{{getName()}};
        }
    }
}
