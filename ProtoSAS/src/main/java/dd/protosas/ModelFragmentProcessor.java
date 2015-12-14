package dd.protosas;

import commonmodel.ElementState;
import dd.protosas.computation.Level;
import dd.protosas.presentation.ElementIdent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 01.11.2015.
 */
public class ModelFragmentProcessor {
    private Level baseLevel;
    private List pushedObjs = new ArrayList<>();

    public ModelFragmentProcessor(Level baseLevel) {
        this.baseLevel = baseLevel;
    }

    public void cycle() {
        for (ElementState es : (List<ElementState>) pushedObjs) {
            baseLevel.push(es);
        }
        baseLevel.process();
        pushedObjs.clear();
    }

    public List getPushedObjs(){
        return pushedObjs;
    }

    public void push(List list) {
        pushedObjs.addAll(list);
    }

    public void push(ElementState elementState) {
        pushedObjs.add(elementState);
    }

    public Level getBaseLevel() {
        return baseLevel;
    }


}
