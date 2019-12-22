class GroupingClass(field: Integer) {
  override def toString(): String = field.toString

  override def equals(that: Any): Boolean =
    that match {
      case that: GroupingClass => this.hashCode == that.hashCode
      case _ => false
    }

  override def hashCode: Int = {
    val prime = 31
    var result = 1
    result = prime * result + field;
    return result
  }
}

val list = List(1, 2, 4, 3, 4);


val group = list.groupBy(new GroupingClass(_))
//list.groupBy(x => x)