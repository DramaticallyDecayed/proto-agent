package dd.sas.pipeline.calculation.structures.nodes

import java.util.function.BiFunction

/**
  * Created by Sergey on 02.10.2016.
  */
class AdapterFunction2[D, R, S](expression: BiFunction[D,R,S]) extends Function2[D, R, S]{
  override def apply(d: D, r: R): S = expression(d,r)
}
