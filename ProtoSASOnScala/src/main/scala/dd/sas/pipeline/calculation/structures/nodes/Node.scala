package dd.sas.pipeline.calculation.structures.nodes

import dd.sas.pipeline.calculation.structures.Level
import dd.sas.pipeline.calculation.structures.flows.Flow

/**
  * Created by Sergey on 24.09.2016.
  */
abstract class Node(level: Level) {

  private var outFlows: List[Flow] = List[Flow]()

  def process(): Unit = {
    outFlows foreach (_.process)
    outFlows = List[Flow]()
  }

  def addUpdatableFlows(flow: Flow): Unit = outFlows = flow :: outFlows
}
