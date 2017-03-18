
package dd.smda.sas.objectproperty;

import dd.sas.presentation.WorldEntity;
import dd.smda.sas.worldentity.GraphFragment;
import dd.smda.sas.worldentity.GraphStructureRule;

public class GraphStructureRuleFor
    implements WorldEntity
{

    private GraphStructureRule domain;
    private GraphFragment range;

    public GraphStructureRule getDomain() {
        return domain;
    }

    public void setDomain(GraphStructureRule domain) {
        this.domain = domain;
    }

    public GraphFragment getRange() {
        return range;
    }

    public void setRange(GraphFragment range) {
        this.range = range;
    }

}
