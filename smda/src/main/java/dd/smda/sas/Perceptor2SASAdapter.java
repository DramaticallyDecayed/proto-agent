
package dd.smda.sas;

import java.util.List;
import dd.sas.computation.Level;
import dd.sas.computation.Node;
import dd.smda.sas.worldentity.Analysis;
import dd.smda.sas.worldentity.GraphStructureRule;
import dd.smda.sas.worldentity.GroupRule;

public abstract class Perceptor2SASAdapter
    extends Node
{


    public Perceptor2SASAdapter() {
        super(new Level(0));
    }

    public abstract List<GroupRule> getGroupRuleList();

    public abstract List<GraphStructureRule> getGraphStructureRuleList();

    public abstract List<Analysis> getAnalysisList();

}
