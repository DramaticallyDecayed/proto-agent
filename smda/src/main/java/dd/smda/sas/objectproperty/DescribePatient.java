
package dd.smda.sas.objectproperty;

import dd.sas.presentation.WorldEntity;
import dd.smda.sas.worldentity.Analysis;
import dd.smda.sas.worldentity.Patient;

public class DescribePatient
    implements WorldEntity
{

    private Analysis domain;
    private Patient range;

    public Analysis getDomain() {
        return domain;
    }

    public void setDomain(Analysis domain) {
        this.domain = domain;
    }

    public Patient getRange() {
        return range;
    }

    public void setRange(Patient range) {
        this.range = range;
    }

}
