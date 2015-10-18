package dd.protosas.computation.levelnode;

/**
 * Created by Sergey on 05.10.2015.
 */
public abstract class NodeProcessor {

    private NodeRegister register;

    public abstract void create();

    public abstract void update();

    public void initialize(NodeRegister register) {
        this.register = register;
    }

    public NodeRegister getRegister(){
        return register;
    }
}
