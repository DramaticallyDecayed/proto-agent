package dd.sas.pipeline.calculation.structures.flows

import dd.sas.pipeline.calculation.structures.nodes.Node

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * Created by Sergey on 24.09.2016.
  */
abstract class Flow(node: Node){

  private var toBeProcessed: Boolean = false
  private var acceptors = ListBuffer[(Flow) => Unit]()

  var donors = mutable.HashMap[Flow, (Flow) => Unit]()

  def process(): Unit = {
    customProcess
    toBeProcessed = false
  }

  def customProcess(): Unit = ???

  def subscribe(acceptor: (Flow) => Unit): Unit = {
    acceptors += acceptor
  }

  def resubcribe() = {
    donors foreach (x => x._1.subscribe(x._2))
    donors.clear()
  }

  def inform() = {
    acceptors.foreach(x => x(this))
    acceptors.clear()
  }

  def setToBeUpdated(): Unit = {
    if (!toBeProcessed) {
      toBeProcessed = true
      node.addUpdatableFlows(this)
    }
  }
}

