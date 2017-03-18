
package dd.smda.sas.objectproperty;

import dd.sas.presentation.WorldEntity;
import dd.smda.sas.worldentity.ParameterizedConcept;

public class ParameterizedProperty
    implements WorldEntity
{

    private ParameterizedConcept domain;
    private ParameterizedConcept range;

    public ParameterizedConcept getDomain() {
        return domain;
    }

    public void setDomain(ParameterizedConcept domain) {
        this.domain = domain;
    }

    public ParameterizedConcept getRange() {
        return range;
    }

    public void setRange(ParameterizedConcept range) {
        this.range = range;
    }

}
