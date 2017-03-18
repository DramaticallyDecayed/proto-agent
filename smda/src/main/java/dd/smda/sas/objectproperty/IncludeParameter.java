
package dd.smda.sas.objectproperty;

import dd.sas.presentation.WorldEntity;
import dd.smda.sas.worldentity.Analysis;
import dd.smda.sas.worldentity.SystemParameter;

public class IncludeParameter
    implements WorldEntity
{

    private Analysis domain;
    private SystemParameter range;

    public Analysis getDomain() {
        return domain;
    }

    public void setDomain(Analysis domain) {
        this.domain = domain;
    }

    public SystemParameter getRange() {
        return range;
    }

    public void setRange(SystemParameter range) {
        this.range = range;
    }

}
