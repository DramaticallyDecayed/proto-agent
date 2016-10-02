package dd.sas.pipeline.calculation.structures.flows.associativeflows

import java.util.function.BiFunction

import dd.sas.pipeline.calculation.structures.flows.Flow
import dd.sas.pipeline.calculation.structures.flows.generativeflows.ObjectFlow
import dd.sas.pipeline.calculation.structures.nodes.Node
import dd.sas.pipeline.worldmodel.{Relation, WorldObject}

/**
  * Created by Sergey on 24.09.2016.
  */
class AssociativePlainFlow[D <: WorldObject, R <: WorldObject, S<:Relation[D, R]]
(node: Node, expression: BiFunction[D, R, S]) extends Flow(node) {

  private var domainFlow: ObjectFlow[D] = _
  private var rangeFlow: ObjectFlow[R] = _

  private var result: List[S] = _

  def apply(): List[S] = {
    result
  }

  override def customProcess: Unit = {
    result = for {
      d <- domainFlow()
      r <- rangeFlow()
    } yield expression(d, r)
    resubcribe()
  }


  def setDomainFlow(domainFlow: Flow): Unit = {
    this.domainFlow = domainFlow.asInstanceOf[ObjectFlow[D]]
    donors.put(domainFlow, this.setDomainFlow)
    setToBeUpdated()
  }

  def setRangeFlow(rangeFlow: Flow): Unit = {
    this.rangeFlow = rangeFlow.asInstanceOf[ObjectFlow[R]]
    donors.put(rangeFlow, this.setRangeFlow)
    setToBeUpdated()
  }

}
