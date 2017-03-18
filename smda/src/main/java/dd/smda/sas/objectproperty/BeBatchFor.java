
package dd.smda.sas.objectproperty;

import dd.sas.presentation.WorldEntity;
import dd.smda.sas.worldentity.GraphFragment;
import dd.smda.sas.worldentity.ParameterBatch;

public class BeBatchFor
    implements WorldEntity
{

    private ParameterBatch domain;
    private GraphFragment range;

    public ParameterBatch getDomain() {
        return domain;
    }

    public void setDomain(ParameterBatch domain) {
        this.domain = domain;
    }

    public GraphFragment getRange() {
        return range;
    }

    public void setRange(GraphFragment range) {
        this.range = range;
    }

}
