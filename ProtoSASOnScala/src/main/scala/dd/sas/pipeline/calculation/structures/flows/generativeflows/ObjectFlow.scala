package dd.sas.pipeline.calculation.structures.flows.generativeflows

import dd.sas.pipeline.calculation.structures.flows.Flow
import dd.sas.pipeline.calculation.structures.nodes.Node
import dd.sas.pipeline.worldmodel.WorldObject

/**
  * Created by Sergey on 25.09.2016.
  */
abstract class ObjectFlow[O <: WorldObject](node: Node) extends Flow(node){
  def apply(): List[O]
}
