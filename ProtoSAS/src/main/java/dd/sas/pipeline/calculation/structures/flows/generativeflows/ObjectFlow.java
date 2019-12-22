package dd.sas.pipeline.calculation.structures.flows.generativeflows;

import dd.sas.pipeline.calculation.structures.flows.Flow;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Sergey on 13.09.2016.
 */
public interface ObjectFlow<T> extends Flow {
    List<T> getResult();
    void subscribe(Consumer<ObjectFlow<T>> consumer);
}
