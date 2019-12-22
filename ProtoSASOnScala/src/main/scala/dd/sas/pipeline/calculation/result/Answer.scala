package dd.sas.pipeline.calculation.result

import dd.sas.pipeline.worldmodel.WorldObject

/**
  * Created by Sergey on 01.11.2016.
  */
sealed trait Answer

case class PositiveAnswer[WO <: WorldObject](wo: WO) extends Answer

case object NegativeAnswer extends Answer

case object UndefinedAnswer extends Answer