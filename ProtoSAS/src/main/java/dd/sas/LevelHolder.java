package dd.sas;

import dd.sas.computation.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 29.05.2016.
 */
public class LevelHolder {

    private List<Level> levels = new ArrayList<>();

    public void addLevel(Level level){
        levels.add(level);
    }

    public List<Level> getLevels(){
        return levels;
    }
}
