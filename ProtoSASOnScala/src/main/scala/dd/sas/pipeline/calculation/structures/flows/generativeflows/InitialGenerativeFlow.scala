package dd.sas.pipeline.calculation.structures.flows.generativeflows


import dd.sas.pipeline.calculation.structures.nodes.Node
import dd.sas.pipeline.worldmodel.WorldObject

/**
  * Created by Sergey on 25.09.2016.
  */
class InitialGenerativeFlow[O <: WorldObject](node: Node) extends ObjectFlow[O](node) {
  private var objects: List[O] = List()

  override def apply(): List[O] = {
    objects
  }

  override def customProcess: Unit = {
    inform()
  }

  def setObjects(objects: List[O]): Unit = {
    this.objects = objects
    setToBeUpdated()
  }
}
