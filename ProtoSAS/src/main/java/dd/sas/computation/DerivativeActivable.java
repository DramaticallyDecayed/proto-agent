package dd.sas.computation;

/**
 * Created by Sergey on 29.05.2016.
 */
public abstract class DerivativeActivable {

    private final Node node;

    protected DerivativeActivable(Node node) {
        this.node = node;
    }

    public abstract void activateDerivative();

    public Node getNode() {
        return node;
    }
}
