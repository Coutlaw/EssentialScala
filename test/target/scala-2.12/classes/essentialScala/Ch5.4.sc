sealed trait LinkedList[A] {
  def map[B](fn: A => B): LinkedList[B] =
    this match{
      case Pair(hd, tl) => Pair(fn(hd), tl.map(fn))
      case End() => End[B]
    }
}

final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
final case class End[A]() extends LinkedList[A]

val list1: LinkedList[Int] = Pair(1, Pair(2,Pair(3, End())))

list1.map(_*2)
list1.map(_+1)
list1.map(_/3)


sealed trait Maybe[A]{
  def flatMap[B](fn: A=> Maybe[B]): Maybe[B] =
    this match{
      case Full(v) => fn(v)
      case Empty() => Empty[B]()
    }
  def map[B](fn: A => B): Maybe[B] =
    flatMap[B](v => Full(fn(v)))
}
final case class Full[A](value: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]


val list2 = List(1,2,3)

list2.map(x => List(x, -x))
list2.flatMap(x => List(x,-x))

val list3: List[Maybe[Int]] = List(Full(3), Full(2), Full(1))


list3.map(maybe => maybe.flatMap[Int] {
  x => if(x % 2 == 0)
    Full(x)
  else
    Empty()
  }
)
