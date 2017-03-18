
package dd.smda.sas.objectproperty;

import dd.sas.presentation.WorldEntity;
import dd.smda.sas.worldentity.ParameterBatch;
import dd.smda.sas.worldentity.SystemParameter;

public class ParameterFormBatch
    implements WorldEntity
{

    private SystemParameter domain;
    private ParameterBatch range;

    public SystemParameter getDomain() {
        return domain;
    }

    public void setDomain(SystemParameter domain) {
        this.domain = domain;
    }

    public ParameterBatch getRange() {
        return range;
    }

    public void setRange(ParameterBatch range) {
        this.range = range;
    }

}
