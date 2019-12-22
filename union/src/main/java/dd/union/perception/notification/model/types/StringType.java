package dd.union.perception.notification.model.types;

/**
 * Хранение строкового значение
 *
 * Created by slebedev on 23.06.2017.
 */
public class StringType extends AbstractAtomType<String> implements ILocation{

    public StringType(String path, String value) {
        super(path, value, value);
    }

    public StringType(String path, String value, boolean empty) {
        super(path, value, value, empty);
    }

    @Override
    public String translate() {
        return getValue();
    }

    @Override
    public String[][] getNames() {
        if(getValue().equals("NOT APPLICABLE")){
            return new String[0][0];
        }else {
            return new String[][]{{getValue()}};
        }
    }
}
