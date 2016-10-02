package dd.sas.pipeline.calculation.processors.associativeprocessors

import dd.sas.pipeline.calculation.processors.Processor
import dd.sas.pipeline.worldmodel.{Relation, WorldObject}

/**
  * Created by Sergey on 02.10.2016.
  */
abstract class AssociativeProcessor[D <: WorldObject, R <: WorldObject] extends Processor {

  def expression[D1 <: D, R1 <: R](d: D1, r: R1): Relation[D1, R1]

  def generator[D1 <: D, R1 <: R, S <: Relation[D1, R1]]
  (creator: () => S): S = {
    val see: S = creator()
    see
  }


}
