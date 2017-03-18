
package dd.smda.sas.objectproperty;

import dd.sas.presentation.WorldEntity;
import dd.smda.sas.worldentity.GraphFragment;
import dd.smda.sas.worldentity.Patient;

public class HasGraph
    implements WorldEntity
{

    private Patient domain;
    private GraphFragment range;

    public Patient getDomain() {
        return domain;
    }

    public void setDomain(Patient domain) {
        this.domain = domain;
    }

    public GraphFragment getRange() {
        return range;
    }

    public void setRange(GraphFragment range) {
        this.range = range;
    }

}
