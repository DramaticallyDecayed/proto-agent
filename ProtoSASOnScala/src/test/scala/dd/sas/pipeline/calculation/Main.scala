package dd.sas.pipeline.calculation

import dd.sas.pipeline.calculation.structures.flows.generativeflows.InitialGenerativeFlow
import dd.sas.pipeline.calculation.structures.{Level, LevelHolder}
import domain.calculation.structure.nodes._
import domain.worldmodel.worldobjects._

/**
  * Created by Sergey on 12.10.2016.
  */
object Main extends App {

  val levelHolder = new LevelHolder

  val level0 = new Level(0)
  levelHolder.addUpdatableLevels(level0)
  val level1 = new Level(1)
  levelHolder.addUpdatableLevels(level1)
  val level2 = new Level(2)
  levelHolder.addUpdatableLevels(level2)

  val nodeEgo = new Node_cu_ego(level0)
  val nodeBall = new Node_cu_ball(level0)
  val nodePlayer = new Node_cu_player(level0)
  val nodeLandmark = new Node_cu_landmark(level0)
  val nodeCoordinateCenter = new Node_cu_coordinatecenter(level0)

  val ballFlow = new InitialGenerativeFlow[Ball](nodeBall)
  ballFlow.setObjects(List(new Ball))

  val egoFlow = new InitialGenerativeFlow[Ego](nodeEgo)
  egoFlow.setObjects(List(new Ego))

  val playerFlow = new InitialGenerativeFlow[Player](nodePlayer)
  playerFlow.setObjects(List(new Player))

  val landmarkFlow = new InitialGenerativeFlow[Landmark](nodeLandmark)
  landmarkFlow.setObjects(List(new Landmark))

  val coordinateCenterFlow = new InitialGenerativeFlow[CoordinateCenter](nodeCoordinateCenter)
  coordinateCenterFlow.setObjects(List(new CoordinateCenter))


  val nodeSee = new Node_cu_see(level1)
  nodeSee.setDonor(nodeEgo)
  nodeSee.setDonor(nodeBall)
  nodeSee.setDonor(nodePlayer)
  nodeSee.setDonor(nodeLandmark)

  val nodeAbs = new Node_cu_abs(level1)
  nodeAbs.setDonor(nodeCoordinateCenter)
  nodeAbs.setDonor(nodeLandmark)

  val nodeAbsSeenBy = new Node_cu_abs_seeby(level2)
  nodeAbsSeenBy.setDonor(nodeAbs)
  nodeAbsSeenBy.setDonor(nodeSee)

  println("---------------------------------PROCESS---------------------------------")
  levelHolder.process()
  println("---------------------------------END---------------------------------")

  playerFlow.setObjects(List(new Player))
  println("---------------------------------PROCESS---------------------------------")
  levelHolder.process()
  println("---------------------------------END---------------------------------")
  ballFlow.setObjects(List(new Ball))
  println("---------------------------------PROCESS---------------------------------")
  levelHolder.process()
  println("---------------------------------END---------------------------------")
  egoFlow.setObjects(List(new Ego))
  println("---------------------------------PROCESS---------------------------------")
  levelHolder.process()
  println("---------------------------------END---------------------------------")
}
