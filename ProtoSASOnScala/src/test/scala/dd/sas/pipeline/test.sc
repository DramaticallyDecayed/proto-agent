
import dd.sas.pipeline.calculation.structures.Level
import dd.sas.pipeline.calculation.structures.flows.associativeflows.AssociativePlainFlow
import dd.sas.pipeline.calculation.structures.flows.generativeflows.InitialGenerativeFlow
import dd.sas.pipeline.worldmodel.Relation
import domain._
import domain.calculation.structure.nodes.{Node_cu_ball, Node_cu_ego, Node_cu_player, Node_cu_see}
import domain.worldmodel._

def expression[D <: Viewer, R <: VisibleObject](d: D, r: R): Relation[D, R] = {
  d compute()
  r compute()
  new Relation[D, R](d, r)
}

def expression_EgoBall(d: Ego, r: Ball): Relation[Ego, Ball] = {
  expression(d, r)
}

def expression_EgoPlayer(d: Ego, r: Player): Relation[Ego, Player] = {
  expression(d, r)
}

val level0 = new Level(0)
val level1 = new Level(1)

val nodeEgo = new Node_cu_ego(level0)
val nodeBall = new Node_cu_ball(level0)
val nodePlayer = new Node_cu_player(level0)


val nodeSee = new Node_cu_see(level1)
nodeSee.setDonor(nodeEgo)
nodeSee.setDonor(nodeBall)
nodeSee.process()

