package domain.worldmodel;

import dd.sas.pipeline.worldmodel.WorldObject;

/**
 * Created by Sergey on 25.09.2016.
 */
public class JavaWorldObjectConnector implements WorldObject{

    private int computed;

    public String name(){
        return getClass().getSimpleName();
    }

    public String toString(){
        return name() + " " + computed;
    }

    @Override
    public void compute(){
        computed++;
    }
}
