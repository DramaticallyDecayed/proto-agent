package dd.sas.computation;

/**
 * Created by Sergey on 29.05.2016.
 */
public class HierarchyActivator extends DerivativeActivable  {


    protected HierarchyActivator(Node node) {
        super(node);
    }

    @Override
    public void activateDerivative() {
        getNode().getSubscribers().forEach(Node::processNode);
    }
}
