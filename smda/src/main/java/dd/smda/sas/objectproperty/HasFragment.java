
package dd.smda.sas.objectproperty;

import dd.sas.presentation.WorldEntity;
import dd.smda.sas.worldentity.Graph;
import dd.smda.sas.worldentity.GraphFragment;

public class HasFragment
    implements WorldEntity
{

    private GraphFragment domain;
    private Graph range;

    public GraphFragment getDomain() {
        return domain;
    }

    public void setDomain(GraphFragment domain) {
        this.domain = domain;
    }

    public Graph getRange() {
        return range;
    }

    public void setRange(Graph range) {
        this.range = range;
    }

}
