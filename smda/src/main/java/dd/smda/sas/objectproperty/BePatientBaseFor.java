
package dd.smda.sas.objectproperty;

import dd.sas.presentation.WorldEntity;
import dd.smda.sas.worldentity.Patient;
import dd.smda.sas.worldentity.PatientGroupRule;

public class BePatientBaseFor
    implements WorldEntity
{

    private Patient domain;
    private PatientGroupRule range;

    public Patient getDomain() {
        return domain;
    }

    public void setDomain(Patient domain) {
        this.domain = domain;
    }

    public PatientGroupRule getRange() {
        return range;
    }

    public void setRange(PatientGroupRule range) {
        this.range = range;
    }

}
