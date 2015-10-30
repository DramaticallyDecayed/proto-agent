package dd.protosas.computability;

import dd.protosas.computation.Level;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey on 30.10.2015.
 */
public class ElementIdentSpecification {
    private Map<Integer,Level> levelMapper = new HashMap<>();

    public Collection<Level> getDependentLevels(){
        return levelMapper.values();
    }
}
