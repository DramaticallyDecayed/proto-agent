package dd.union.perception.notification.model.types;


import dd.union.perception.notification.model.parsers.PointParser;

import java.util.List;

/**
 * Хранение пары координат
 *
 * Created by slebedev on 15.06.2017.
 */
public class PointType extends AbstractType {
    private static String commaSplitter = ", ";

    private CoordinateType latitude;
    private CoordinateType longitude;

    public PointType(String path, String value) {
        super(path, value);
        List<String> values = new PointParser().parse(getValue());
        latitude = new CoordinateType(values.get(0));
        longitude = new CoordinateType(values.get(1));
    }

    public PointType(CoordinateType latitude, CoordinateType longitude) {
        super(null, null);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String translate() {
        return latitude.translate() + commaSplitter + longitude.translate();
    }
}
