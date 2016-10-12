package dd.sas.pipeline.calculation.structures

import scala.collection.mutable.ListBuffer


/**
  * Created by Sergey on 11.10.2016.
  */
class LevelHolder {
  private var levels: ListBuffer[Level] = ListBuffer[Level]()

  def process(): Unit = {
    println("levels = " + levels.size)
    levels foreach (_.process)
  }

  def addUpdatableLevels(level: Level): Unit = levels += level
}
