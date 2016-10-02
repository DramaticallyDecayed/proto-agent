package dd.sas.pipeline.worldmodel

/**
  * Created by Sergey on 24.09.2016.
  */
trait WorldObject {
  def name(): String = getClass.getSimpleName
  def compute(): Unit;
  override def toString: String = name()
}

object Nihility extends WorldObject {
  override def compute(): Unit = ???
}
