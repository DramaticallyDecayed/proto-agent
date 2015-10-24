package commonmodel;

/**
 * Created by Sergey on 23.10.2015.
 */
public class ElementState {

    private final String name;

    public ElementState(String name) {
        this.name = name;
    }

    public ElementState(){
        this.name = this.getClass().getName();
    }
    
    public String getName() {
        return name;
    }
}
