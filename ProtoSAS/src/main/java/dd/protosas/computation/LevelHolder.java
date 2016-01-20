package dd.protosas.computation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 20.01.2016.
 */
public class LevelHolder {

    private List<LightweightLevel> levels = new ArrayList<>();

    public void process(){
        levels.forEach(LightweightLevel::process);
    }

    public void addLevel(LightweightLevel level){
        levels.add(level);
    }

    public LightweightLevel getLevel(int levelNum){
        return levels.get(levelNum);
    }
}
