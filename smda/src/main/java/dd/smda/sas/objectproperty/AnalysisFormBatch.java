
package dd.smda.sas.objectproperty;

import dd.sas.presentation.WorldEntity;
import dd.smda.sas.worldentity.Analysis;
import dd.smda.sas.worldentity.ParameterBatch;

public class AnalysisFormBatch
    implements WorldEntity
{

    private Analysis domain;
    private ParameterBatch range;

    public Analysis getDomain() {
        return domain;
    }

    public void setDomain(Analysis domain) {
        this.domain = domain;
    }

    public ParameterBatch getRange() {
        return range;
    }

    public void setRange(ParameterBatch range) {
        this.range = range;
    }

}
