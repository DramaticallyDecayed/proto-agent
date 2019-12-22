package implementation

import java.util.UUID

import _root_.dd.union.sas.objectproperty.HasNfField
import _root_.dd.union.sas.worldentity._

import scala.collection.JavaConverters._
import scala.collection.mutable

object Node_cu_state_processing {

  def  customProcess(list: java.util.List[HasNfField]):
  java.util.Map[Notification, java.util.Map[NF3Statement, java.util.List[NfField]]] = {
    val contentFields = list.asScala.filter(x => filterPath(x.getRange.getNffield_path))
    val nf2field = contentFields.groupBy(_.getDomain)
    val nf2statement = nf2field
      .map(x => (x._1, x._2.map(_.getRange)))
      .map(x => (x._1, createStatement(x._2)))
    nf2statement.asJava
  }

  def createStatement(list: mutable.Buffer[NfField]):
  java.util.Map[NF3Statement, java.util.List[NfField]] = {
    list
      .groupBy(_.getNffield_path.split("_")(1))
      .map(x => (createNf3Statement(x._1), x._2.asJava)).asJava
  }

  def filterPath(path: String): Boolean = {
    path.charAt(0) == '3' && path.split("_").length > 2
  }

  def createNf3Statement(path : String) : NF3Statement = {
    val statement = (new NF3StatementC()).asInstanceOf[NF3Statement]
    statement.setStatementPath(path)
    statement.setUri(UUID.randomUUID.toString)
    statement
  }

}
