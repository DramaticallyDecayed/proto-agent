package dd.sas.pipeline.calculation.structures.flows

import dd.sas.pipeline.calculation.structures.nodes.Node

import scala.collection.mutable

/**
  * Created by Sergey on 24.09.2016.
  */
abstract class Flow(node: Node) {

  private var toBeUpdated: Boolean = false
  private var acceptors = List[(Flow) => Unit]()

  var donors = mutable.HashMap[Flow, (Flow) => Unit]()

  def process: Unit = {
    customProcess
    toBeUpdated = false
  }

  def customProcess: Unit

  def subscribe(acceptor: (Flow) => Unit): Unit = {
    acceptors ::= acceptor
  }

  def resubcribe() = {
    donors foreach (x => x._1.subscribe(x._2))
    donors.clear()
  }

  def inform() = {
    acceptors.foreach(x => x(this))
    acceptors = List[(Flow) => Unit]()
  }

  def setToBeUpdated(): Unit = {
    if (!toBeUpdated) {
      toBeUpdated = true
      node.addUpdatableFlows(this)
    }
  }
}

