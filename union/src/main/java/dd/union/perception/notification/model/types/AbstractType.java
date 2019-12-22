package dd.union.perception.notification.model.types;

/**
 * Исходный абстрактный тип, не привязанный ни к какому значению
 *
 * Created by slebedev on 15.06.2017.
 */
public abstract class AbstractType implements IType {

    private String path;
    private String value;
    private boolean empty;

    public AbstractType(String path, String value) {
        this.path = path;
        this.value = value;
    }

    public AbstractType(String path, String value, boolean empty) {
        this(path, value);
        this.empty = empty;
    }

    @Override
    abstract public String translate();

    public String userString() {
        return translate();
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public boolean isEmpty() {
        return empty;
    }

    @Override
    public IType buildIn(AbstractTypeList<IType> list) {
        return list.build(this);
    }
}
