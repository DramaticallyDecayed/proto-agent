package dd.soccer.common;

/**
 * Created by Sergey on 11.12.2015.
 */
public class Topic {

    private Class c;

    public Topic(Class c){
        this.c = c;
    }

    @Override
    public boolean equals(Object c){
        return this.c.equals(c);
    }

    @Override
    public int hashCode(){
        return c.hashCode();
    }
}
