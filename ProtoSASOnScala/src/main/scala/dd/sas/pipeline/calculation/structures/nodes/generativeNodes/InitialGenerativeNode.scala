package dd.sas.pipeline.calculation.structures.nodes.generativeNodes

import dd.sas.pipeline.calculation.structures.Level
import dd.sas.pipeline.calculation.structures.flows.generativeflows.{InitialGenerativeFlow, ObjectFlow}
import dd.sas.pipeline.calculation.structures.nodes.Node
import dd.sas.pipeline.worldmodel.WorldObject

/**
  * Created by Sergey on 02.10.2016.
  */
class InitialGenerativeNode[WO <: WorldObject]
(level: Level) extends Node(level) {

  private var initialObjectFlow: ObjectFlow[WO] = _

  def getOutFlow = initialObjectFlow

  def setOutFlow(initialObjectFlow: ObjectFlow[WO]) = this.initialObjectFlow = initialObjectFlow
}
