package onflows.calculation.structure.flows;

import onflows.calculation.structure.flows.Flow;

import java.util.List;

/**
 * Created by Sergey on 13.09.2016.
 */
public interface ObjectFlow<T> extends Flow {
    List<T> getResult();
}
