package dd.sas.computation;

/**
 * Created by Sergey on 29.05.2016.
 */
public class OntologyActivator extends DerivativeActivable {

    protected OntologyActivator(Node node) {
        super(node);
    }

    @Override
    public void activateDerivative() {
        getNode().activateNode();
    }

    public void switchStrategy(){
        getNode().setActivator(new HierarchyActivator(getNode()));
    }

}
