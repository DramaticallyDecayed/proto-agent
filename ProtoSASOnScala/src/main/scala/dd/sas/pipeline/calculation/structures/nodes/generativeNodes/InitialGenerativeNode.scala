package dd.sas.pipeline.calculation.structures.nodes.generativeNodes

import dd.sas.pipeline.calculation.structures.Level
import dd.sas.pipeline.calculation.structures.flows.Flow
import dd.sas.pipeline.calculation.structures.flows.generativeflows.{InitialGenerativeFlow, ObjectFlow}
import dd.sas.pipeline.calculation.structures.nodes.Node
import dd.sas.pipeline.worldmodel.WorldObject

/**
  * Created by Sergey on 02.10.2016.
  */
class InitialGenerativeNode[WO <: WorldObject]
(level: Level) extends Node(level) {

  def setOutFlow(initialObjectFlow: InitialGenerativeFlow[WO]) = addUpdatableFlows(initialObjectFlow)

  def getOutFlow(): InitialGenerativeFlow[WO] = getOutFlow(0).asInstanceOf[InitialGenerativeFlow[WO]]
}
