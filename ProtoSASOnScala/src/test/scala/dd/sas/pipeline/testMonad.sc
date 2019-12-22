sealed trait Maybe[+A] {

  // >>=
  def flatMap[B](f: A => Maybe[B]): Maybe[B]
  def map[B](f: A => B): Maybe[B]
}

case class Just[+A](a: A) extends Maybe[A] {
  override def flatMap[B](f: A => Maybe[B]) = f(a)

  override def map[B](f: (A) => B): Maybe[B] = flatMap { a => Just(f(a)) }
}

// Nothing in the Haskel example
case object MaybeNot extends Maybe[Nothing] {
  override def flatMap[B](f: Nothing => Maybe[B]) = MaybeNot

  override def map[B](f: (Nothing) => B): Maybe[B] = MaybeNot
}

object Person {

  val persons = List("P", "MP", "MMP", "FMP", "FP", "MFP", "FFP") map { Person(_) }

  private val mothers = Map(
    Person("P") -> Person("MP"),
    Person("MP") -> Person("MMP"),
    Person("FP") -> Person("MFP"))

  private val fathers = Map(
    Person("P") -> Person("FP"),
    Person("MP") -> Person("FMP"),
    Person("FP") -> Person("FFP"))

  def mother(p: Person): Maybe[Person] = relation(p, mothers)

  def father(p: Person): Maybe[Person] = relation(p, fathers)

  private def relation(p: Person, relationMap: Map[Person, Person]) = relationMap.get(p) match {
    case Some(m) => Just(m)
    case None => MaybeNot
  }
}

case class Person(name: String) {
  def mother: Maybe[Person] = Person.mother(this)
  def father: Maybe[Person] = Person.father(this)
}

def maternalGrandfather(p: Person): Maybe[Person] =
  p.mother flatMap { _.father }

def maternalGrandfatherNoFlatMap(p: Person): Maybe[Person] =
  p.mother match {
    case Just(m) => m.father
    case MaybeNot => MaybeNot
  }

Person.persons foreach { p =>
  assert(maternalGrandfather(p) == maternalGrandfatherNoFlatMap(p))
}


def bothGrandfathersFlatMap(p: Person): Maybe[(Person, Person)] =
  p.mother flatMap { m =>
    m.father flatMap { fm =>
      p.father flatMap { f =>
        f.father flatMap { ff =>
          Just(fm, ff)
        }
      }
    }
  }

def bothGrandfathersNoFlatMap(p: Person): Maybe[(Person, Person)] =
  (p.mother, p.father) match {
    case (Just(m), Just(f)) =>
      (m.father, f.father) match {
        case (Just(fm), Just(ff)) => Just((fm, ff))
        case _ => MaybeNot
      }
    case _ => MaybeNot
  }

def assertBothGrandfathers(
                            bothGrandfathers1: Person => Maybe[(Person, Person)],
                            bothGrandfathers2: Person => Maybe[(Person, Person)]) =
  Person.persons foreach { p =>
    assert(bothGrandfathers1(p) == bothGrandfathers2(p))
  }

assertBothGrandfathers(bothGrandfathersFlatMap, bothGrandfathersNoFlatMap)

def bothGrandfathersForLoop(p: Person): Maybe[(Person, Person)] =
  for(
    m <- p.mother;
    fm <- m.father;
    f <- p.father;
    ff <- f.father)
    yield (fm, ff)

assertBothGrandfathers(bothGrandfathersForLoop, bothGrandfathersFlatMap)

bothGrandfathersForLoop(Person.persons(1))