
package dd.smda.sas.objectproperty;

import dd.sas.presentation.WorldEntity;
import dd.smda.sas.worldentity.ParameterBatch;
import dd.smda.sas.worldentity.PatientGroupRule;

public class BePatientGroupRuleFor
    implements WorldEntity
{

    private PatientGroupRule domain;
    private ParameterBatch range;

    public PatientGroupRule getDomain() {
        return domain;
    }

    public void setDomain(PatientGroupRule domain) {
        this.domain = domain;
    }

    public ParameterBatch getRange() {
        return range;
    }

    public void setRange(ParameterBatch range) {
        this.range = range;
    }

}
