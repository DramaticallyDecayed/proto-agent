package dd.sas.pipeline.calculation.structures.flows.generativeflows


import java.util.function.Supplier

import dd.sas.pipeline.calculation.structures.nodes.Node
import dd.sas.pipeline.worldmodel.WorldObject

/**
  * Created by Sergey on 25.09.2016.
  */
class InitialGenerativeFlow[O <: WorldObject](node: Node, objectFlow: Supplier[O]) extends ObjectFlow[O](node) {
  override def apply(): List[O] = {
    //inform()
    List(objectFlow.get())
  }

  override def customProcess: Unit = ???
}
