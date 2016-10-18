package dd.sas.pipeline.calculation.processors.associativeprocessors

import dd.sas.pipeline.calculation.processors.Processor
import dd.sas.pipeline.worldmodel.{Nihility, Relation, WorldObject}

/**
  * Created by Sergey on 02.10.2016.
  */
abstract class AssociativeProcessor[D <: WorldObject, R <: WorldObject] extends Processor {

  def customCommonExpression[D1 <: D, R1 <: R](d: D1, r: R1): Relation[D1, R1]

  def commonExpression[D1 <: D, R1 <: R](d: D1, r: R1): Relation[D1, R1] = (d, r) match {
    case (Nihility, _) | (_, Nihility) => new Relation[D1, R1](d, r)
    case (_, _) => customCommonExpression(d, r)
  }

}
