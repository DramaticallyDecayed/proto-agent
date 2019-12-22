package dd.union.perception.notification.model.types;

import dd.union.perception.notification.model.parsers.NumberCollectionParser;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Хранение списка целых чисел
 * <p>
 * Created by slebedev on 15.06.2017.
 */
public class NumberCollectionType extends AbstractAtomType<List<Integer>> implements ILocation {

    public NumberCollectionType(String path, String value) {
        super(path, value, new NumberCollectionParser(true).parse(value));
    }

    @Override
    public String translate() {
        return getValue();
    }

    @Override
    public String[][] getNames() {
        if (getValue().equals("NOT APPLICABLE")) {
            return new String[0][0];
        } else {
            String[] strings = new String[getTypedValue().size()];
            for (int i = 0; i < getTypedValue().size(); i++) {
                strings[i] = Integer.toString(getTypedValue().get(i));
            }
            return new String[][]{strings};
        }
    }
}
