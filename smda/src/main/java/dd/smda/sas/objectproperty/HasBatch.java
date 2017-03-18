
package dd.smda.sas.objectproperty;

import dd.sas.presentation.WorldEntity;
import dd.smda.sas.worldentity.ParameterBatch;
import dd.smda.sas.worldentity.Patient;

public class HasBatch
    implements WorldEntity
{

    private Patient domain;
    private ParameterBatch range;

    public Patient getDomain() {
        return domain;
    }

    public void setDomain(Patient domain) {
        this.domain = domain;
    }

    public ParameterBatch getRange() {
        return range;
    }

    public void setRange(ParameterBatch range) {
        this.range = range;
    }

}
