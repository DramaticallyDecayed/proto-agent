package dd.sas.pipeline.calculation.structures.flows.associativeflows

import dd.sas.pipeline.calculation.structures.flows.Flow
import dd.sas.pipeline.calculation.structures.nodes.Node
import dd.sas.pipeline.worldmodel.{Relation, WorldObject}

/**
  * Created by Sergey on 12.10.2016.
  */
abstract class RelationFlow[D <: WorldObject, R <: WorldObject, S <: Relation[D, R]]
(node: Node) extends Flow(node) {
  def apply(): List[S]
}
