sealed trait LinkedList[A] {
  def map[B](fn: A => B): LinkedList[B] =
    this match{
      case Pair(hd, tl) => Pair(fn(hd), tl.map(fn))
      case End() => End[B]
    }
}

final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
final case class End[A]() extends LinkedList[A]

sealed trait Maybe[A] {
  def flatMap[B](fn: A => Maybe[B]): Maybe[B] =
    this match{
      case Full(V) => fn(v)
      case Empty() => Empty[B]()
    }
}
final case class Full[A](value: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]

