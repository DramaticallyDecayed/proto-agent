package dd.sas.pipeline.calculation.structures.flows.associativeflows

import java.util.function.BiFunction

import dd.sas.pipeline.calculation.structures.flows.Flow
import dd.sas.pipeline.calculation.structures.nodes.Node
import dd.sas.pipeline.worldmodel.{Relation, WorldObject}

/**
  * Created by Sergey on 12.10.2016.
  */
class AssociativeRefiningFlow
[D <: WorldObject, M <: WorldObject, R <: WorldObject, S <: Relation[D, R]]
(node: Node, expression: BiFunction[D, R, S]) extends RelationFlow[D, R, S](node) {

  private var firstFlow: RelationFlow[D, M, Relation[D, M]] = _
  private var secondFlow: RelationFlow[M, R, Relation[M, R]] = _

  private var result: List[S] = _

  override def apply(): List[S] = {
    result
  }

  override def customProcess: Unit = {
    result = for {
      first <- firstFlow()
      second <- secondFlow()
      if (first.range == second.domain)
    } yield expression(first.domain, second.range)
    resubcribe()
  }


  def setDomainFlow(domainFlow: Flow): Unit = {
    this.firstFlow = domainFlow.asInstanceOf[RelationFlow[D, M, Relation[D, M]]]
    donors.put(domainFlow, this.setDomainFlow)
    setToBeUpdated()
  }

  def setRangeFlow(rangeFlow: Flow): Unit = {
    this.secondFlow = rangeFlow.asInstanceOf[RelationFlow[M, R, Relation[M, R]]]
    donors.put(rangeFlow, this.setRangeFlow)
    setToBeUpdated()
  }

}
