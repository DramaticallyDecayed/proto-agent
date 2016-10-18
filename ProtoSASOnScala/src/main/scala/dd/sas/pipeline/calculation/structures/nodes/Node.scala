package dd.sas.pipeline.calculation.structures.nodes

import dd.sas.pipeline.calculation.structures.Level
import dd.sas.pipeline.calculation.structures.flows.Flow

import scala.collection.mutable.ListBuffer

/**
  * Created by Sergey on 24.09.2016.
  */
abstract class Node(level: Level) {

  private var toBeProcessed: Boolean = false

  private var outFlows: ListBuffer[Flow] = ListBuffer[Flow]()

  def process(): Unit = {
    println("Node: " + this.getClass.getCanonicalName + " flows = " + outFlows.size)
    outFlows foreach (_.process)
    outFlows.clear()
    toBeProcessed = false
  }

  def addUpdatableFlows(flow: Flow): Unit = {
    outFlows += flow
    setToBeUpdated()
  }

  def getOutFlow(index: Int): Flow = outFlows(index)

  def setToBeUpdated(): Unit = {
    if (!toBeProcessed) {
      toBeProcessed = true
      level.addUpdatableNodes(this)
    }
  }
}
