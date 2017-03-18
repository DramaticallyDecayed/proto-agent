
package dd.smda.sas.objectproperty;

import dd.sas.presentation.WorldEntity;
import dd.smda.sas.worldentity.GroupRule;
import dd.smda.sas.worldentity.PatientGroupRule;

public class BeGroupRuleBaseFor
    implements WorldEntity
{

    private GroupRule domain;
    private PatientGroupRule range;

    public GroupRule getDomain() {
        return domain;
    }

    public void setDomain(GroupRule domain) {
        this.domain = domain;
    }

    public PatientGroupRule getRange() {
        return range;
    }

    public void setRange(PatientGroupRule range) {
        this.range = range;
    }

}
