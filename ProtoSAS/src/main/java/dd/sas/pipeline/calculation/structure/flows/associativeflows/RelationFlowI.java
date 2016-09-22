package dd.sas.pipeline.calculation.structure.flows.associativeflows;

import dd.sas.pipeline.calculation.structure.flows.Flow;
import dd.sas.pipeline.worldmodel.Relation;

import java.util.List;

/**
 * Created by Sergey on 13.09.2016.
 */
public interface RelationFlowI<R extends Relation> extends Flow {
    List<R> getResult();
}
