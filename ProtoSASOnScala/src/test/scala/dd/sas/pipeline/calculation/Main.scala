package dd.sas.pipeline.calculation

import dd.sas.pipeline.calculation.structures.{Level, LevelHolder}
import domain.calculation.structure.nodes._
import domain.worldmodel._
import domain.worldmodel.worldobjects.{Ball, Ego, Player}

/**
  * Created by Sergey on 12.10.2016.
  */
object Main extends App{

  val levelHolder = new LevelHolder

  val level0 = new Level(0)
  levelHolder.addUpdatableLevels(level0)
  val level1 = new Level(1)
  levelHolder.addUpdatableLevels(level1)

  val nodeEgo = new Node_cu_ego(level0)
  val nodeBall = new Node_cu_ball(level0)
  val nodePlayer = new Node_cu_player(level0)
  val nodeLandmark= new Node_cu_landmark(level0)

  val ballFlow = nodeBall.getOutFlow()
  val egoFlow = nodeEgo.getOutFlow()
  val playerFlow = nodePlayer.getOutFlow()
  val landmarkFlow = nodeLandmark.getOutFlow()

  ballFlow.setObjects(List(new Ball))
  egoFlow.setObjects(List(new Ego))
  playerFlow.setObjects(List(new Player))

  val nodeSee = new Node_cu_see(level1)
  nodeSee.setDonor(nodeEgo)
  nodeSee.setDonor(nodeBall)
  nodeSee.setDonor(nodePlayer)


  //nodeBall process()
  levelHolder.process()

  playerFlow.setObjects(List(new Player))
  levelHolder.process()
  ballFlow.setObjects(List(new Ball))
  levelHolder.process()
  egoFlow.setObjects(List(new Ego))
  levelHolder.process()
}
