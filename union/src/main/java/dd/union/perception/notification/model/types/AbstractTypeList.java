package dd.union.perception.notification.model.types;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * Список абстрактных типов
 * <p>
 * Created by slebedev on 23.06.2017.
 */
public class AbstractTypeList<T extends IType> extends ArrayList<T> implements IType {

    private String path;

    public AbstractTypeList() {
    }

    public AbstractTypeList(String path) {
        this.path = path;
    }

    public AbstractTypeList<T> build(T abstractType) {
        this.add(abstractType);
        return this;
    }

    public AbstractTypeList build(AbstractTypeList list) {
        if (list.path != null) {
            T type = (T) new AbstractTypeList<>(list.path);
            build(type);
        }
        this.addAll(list);
        return this;
    }

    public AbstractTypeList<T> sort() {
        Collections.sort(this, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.getPath().compareTo(o2.getPath());
            }
        });
        return this;
    }

    @Override
    public boolean isEmpty() {
        return get(0).isEmpty();
    }

    @Override
    public String translate() {
        return "";
    }

    @Override
    public String getValue() {
        return "";
    }

    @Override
    public AbstractTypeList<IType> buildIn(AbstractTypeList list) {
        list.add(new AbstractTypeList(this.path));
        for (int i = 0; i < this.size(); i++) {
            this.get(i).buildIn(list);
        }
        return list;
    }

    @Override
    public String getPath() {
        return path;
    }
}
