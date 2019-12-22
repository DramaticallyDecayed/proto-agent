package dd.union.perception.notification.model.types;


import dd.union.perception.notification.model.parsers.StringNumberPairParser;

/**
 * Хранение пары число-строка
 * <p/>
 * Используется только для хранение строк в таблице 15 уведомления
 * <p/>
 * Created by slebedev on 29.06.2017.
 */
public class StringNumberPair extends AbstractType implements ILocation {

    private VocabularyStringType string;
    private NumberType number;

    public StringNumberPair(String path, String value) {
        super(path, value);
        String[] strings = new StringNumberPairParser().parse(value);
        string = new VocabularyStringType(null, strings[1]);
        number = new NumberType(null, strings[0]);
    }

    @Override
    public String translate() {
        return number.translate() + StringNumberPairParser.SPLITTER + string.translate();
    }

    public VocabularyStringType getString() {
        return string;
    }

    public NumberType getNumber() {
        return number;
    }

    @Override
    public String[][] getNames() {
        if (getValue().equals("NOT APPLICABLE")) {
            return new String[0][0];
        } else {
            return new String[][]{{getValue()}};
        }
    }
}
