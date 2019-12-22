package dd.union.graphstate;

import dd.sas.pipeline.worldmodel.Relation;
import dd.sas.presentation.WorldEntity;

import java.util.ArrayList;
import java.util.List;

public class GraphState {

    private List<WorldEntity> entities = new ArrayList<>();
    private List<Relation> relations = new ArrayList<>();

    public void stateEntity(WorldEntity entity){
        entities.add(entity);
    }

    public void stateRelation(Relation relation){
        relations.add(relation);
    }


    public boolean hasStatedEntities(){
        return !entities.isEmpty();
    }

    public boolean hasStatedRelations(){
        return !relations.isEmpty();
    }

    public List<WorldEntity> getEntities() {
        return entities;
    }

    public List<Relation> getRelations() {
        return relations;
    }


}
