package dd.sas.computation;

import java.util.List;

/**
 * Created by Sergey on 06.06.2016.
 */
@FunctionalInterface
public interface WrappingGetter<T> {
    List<T> getObjectList();
}
