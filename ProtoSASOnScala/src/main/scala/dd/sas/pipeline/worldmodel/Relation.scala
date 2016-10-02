package dd.sas.pipeline.worldmodel

/**
  * Created by Sergey on 24.09.2016.
  */
class Relation[D, R](val domain: D, val range: R) {
  def getDomain: D = domain

  def getRange: R = range

  override def toString() = domain + " " + range
}
