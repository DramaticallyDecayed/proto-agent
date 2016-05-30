package dd.sas;

import dd.sas.computation.Level;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey on 29.05.2016.
 */
public class LevelHolder {

    private Map<Integer, Level> levels = new HashMap<>();

    public void addLevel(Level level){
        levels.put(level.getNumber(), level);
    }

    public Level getLevel(Integer num){
        return levels.get(num);
    }

    public Collection<Level> getLevels(){
        return levels.values();
    }
}
