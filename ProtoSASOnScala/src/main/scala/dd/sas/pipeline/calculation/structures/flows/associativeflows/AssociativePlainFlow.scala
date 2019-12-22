package dd.sas.pipeline.calculation.structures.flows.associativeflows

import java.util.function.BiFunction

import dd.sas.pipeline.calculation.result.Answer
import dd.sas.pipeline.calculation.structures.flows.Flow
import dd.sas.pipeline.calculation.structures.flows.generativeflows.ObjectFlow
import dd.sas.pipeline.calculation.structures.nodes.Node
import dd.sas.pipeline.worldmodel.{Relation, WorldObject}

/**
  * Created by Sergey on 24.09.2016.
  */
class AssociativePlainFlow[D <: WorldObject, R <: WorldObject, S <: Relation[D, R]]
(node: Node, expression: BiFunction[D, R, S]) extends RelationFlow[D, R, S](node) {

  private var domainFlow: ObjectFlow[D] = _
  private var rangeFlow: ObjectFlow[R] = _
  private var result: List[S] = _

  //need this method cuz we derive node when there are some flows, but not all of them
  private var customProcessVar: () => Unit = () => {
    if(domainFlow != null && rangeFlow != null){
      customProcessVar = customProcessActivated
      customProcessActivated()
    }
  }

  override def apply(): List[S] = {
    result
  }

  def customProcessActivated(): Unit = {
    result = for {
      d <- domainFlow()
      r <- rangeFlow()
    } yield expression(d, r)
    resubcribe()
  }

  override def customProcess(): Unit ={
    customProcessVar()
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
