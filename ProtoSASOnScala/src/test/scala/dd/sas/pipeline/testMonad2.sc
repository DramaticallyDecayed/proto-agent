
sealed trait Answer[+A]

case class PositiveAnswer[+A](atom: A) extends Answer[A]

case object NegativeAnswer extends Answer[Nothing]



val l1 = List(Some(PositiveAnswer(1)), Some(PositiveAnswer(2)), Some(NegativeAnswer))
val l2 = List(Some(PositiveAnswer(3)), Some(NegativeAnswer))


for{
  a <- l1
  b <- l2
  c <- a
  d <- b
} yield compose(c,d)


def compose[A,B](a:A, b:B) = (a,b) match {
  case (NegativeAnswer,_) |(_,NegativeAnswer) => NegativeAnswer
  case (_,_) => (a,b)
}