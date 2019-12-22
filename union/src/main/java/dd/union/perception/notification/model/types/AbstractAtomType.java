package dd.union.perception.notification.model.types;

/**
 * Абстрактный тип, хрянящий некоторое атомарное значение
 *
 * Created by slebedev on 15.06.2017.
 */
public abstract class AbstractAtomType<T> extends AbstractType {

    private T typedValue;

    public AbstractAtomType(String path, String value, T typedValue) {
        super(path, value);
        this.typedValue = typedValue;
    }

    public AbstractAtomType(String path, String value, T typedValue, boolean empty) {
        super(path, value, empty);
        this.typedValue = typedValue;
    }

    public T getTypedValue() {
        return typedValue;
    }

    public void setTypedValue(T typedValue) {
        this.typedValue = typedValue;
    }


}
