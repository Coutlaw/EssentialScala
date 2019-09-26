/*
 * sealed trait A
final case class B() extends A
final case class C() extends A
*/

sealed trait A
final case class B(d: D, e: E) extends A
final case class C(f: F, g: G) extends A


val foo = 1 + 1

sealed trait TrafficLight{
  def next: TrafficLight =
    this match{
      case Red => Green
      case Green => Yellow
      case Yellow => Red
    }
}

case object Red extends TrafficLight
case object Green extends TrafficLight
case object Yellow extends  TrafficLight


sealed trait A {
  def doSomething: H = {
    this match {
      case B(j,_) => doB(j)
      case C(f,g) => doC(f,g)
    }
  }
}
final case class B(d: D, e: E) extends A
final case class C(f: F, g: G) extends A


sealed trait IntList
case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList

def sum(list: IntList): Int =
  list match {
    case End => 0
    case Pair(hd, tl) => hd + sum(tl)
  }


// A => B => C




sealed trait Result[A] {
  def fold[B](s: A => B, f: B): B =
    this match {
      case Failure() => f  //simple case
      case Success(v) => s(v)
    }
}


final case class Success[A](value: A) extends Result[A]
final case class Failure[A]() extends Result[A]

final def fold[B](ifEmpty: => B)(f: (A) => B): B