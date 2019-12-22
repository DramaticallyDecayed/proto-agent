package dd.union.perception.notification.model.types;

import dd.union.perception.notification.model.parsers.IdentifierParser;
import dd.union.perception.notification.model.parsers.NumberCollectionParser;
import dd.union.perception.notification.recognition.translators.Vocabulary;

import java.util.List;

/**
 * Хранение регистрационного номера уведомления
 * <p/>
 * Created by slebedev on 23.06.2017.
 */
public class IdentNumberType extends AbstractType {

    private String treaty;
    private Integer year;
    private Integer number;
    private Integer format;
    private String fullNumber;

    public IdentNumberType(String path, String value) {
        super(path, value);
        List<Integer> numbers = new NumberCollectionParser(false).parse(value);
        year = numbers.get(0);
        number = numbers.get(1);
        format = numbers.get(2);
        this.treaty = new IdentifierParser().parse(value);
        this.fullNumber = value.replace(treaty,"").trim();
    }

    @Override
    public String translate() {
        return getValue().replace(treaty, new Vocabulary().translate(treaty));
    }

    public Integer getYear() {
        return year;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getFormat() {
        return format;
    }

    public String getTreaty() {
        return treaty;
    }

    public String getFullNumber() {
        return fullNumber;
    }

    public IType buildIn(){
        return this;
    }
}
