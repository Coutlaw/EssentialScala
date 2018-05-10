// Genaric Types
// pandora's box, we don't care what type is in the box, only that it can have somethign

final case class Box[A](value: A)

Box(2)
Box(2).value

// the A is generic
def generic[A](in: A): A = in

generic[String]("foo")
// we can declare that it is a string

generic("still a string")
// or scala will infer it

// Generic sum pattern

// created the linked list to find the length of a generic list

// these prevent the apply method from throwing an exception
// created a result that can be returned instead of an exception

sealed trait Result[A]
case class Success[A](result: A) extends Result[A]
case class Failure[A](reason: String) extends Result[A]

sealed trait LinkedListx[A]{
  def length: Int =
    this match {
      case Pair(h, t) => 1 + t.length
      case End() => 0
    }
  def contains(x: A): Boolean =
    this match{
      case Pair(h, t) =>
        if(h == x)
          true
        else
          t.contains(x)
      case End() => false
    }
  def apply(index: Int): Result[A] =
    this match{
      case Pair(h,t) =>
        if(index == 0)
          Success(h)
        else
        // moves the index closer to 0 per call
          t(index-1)
      case End() =>
        Failure("Index out of bounds")
    }

}

final case class Pair[A](head: A, tail: LinkedListx[A]) extends LinkedListx[A]
final case class End[A]() extends LinkedListx[A]

val example = Pair(1,Pair(2,Pair(3,End())))
// test length method
example.length
// test contains method
example.contains(1)

// tests apply method
example(1)
// tests exception case
example(4)

// FUNCTIONS
val sayHi = () => "Hi!"
sayHi()

val add1 = (x: Int) => x+1
add1(10)

val sum = (x: Int, y: Int) => x+y
sum(10,20)

// function structure is
// (param: Type) => expression

// getting into some abstraction
// sealed trait, like an interface that knows all classes it will have
sealed trait IntList {
  def fold(end: Int, f: (Int, Int) => Int): Int =
    this match {
      case End => end
      case Pair2(hd, tl) => f(hd, tl.fold(end, f))
    }
  def length: Int =
    fold(0, (_,tl) => 1 +tl)
  def product: Int =
    fold(1,(hd,tl) => hd * tl)
  def sum: Int =
    fold(0, (hd, tl) => hd + tl)

}

case object End extends IntList
final case class Pair2(head: Int, tail: IntList) extends IntList



