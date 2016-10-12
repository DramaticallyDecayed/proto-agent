package dd.sas.pipeline.calculation.structures

import dd.sas.pipeline.calculation.structures.nodes.Node

/**
  * Created by Sergey on 02.10.2016.
  */
class Level(number: Int){

  private var nodes: List[Node] = List[Node]()

  def process(): Unit = {
    println("level = " + number + " nodes = " + nodes.size)
    nodes foreach (_.process)
    nodes = List[Node]()
  }

  def addUpdatableNodes(node: Node): Unit = nodes = node :: nodes
  def getNode(index: Int): Node = nodes(index)


}
