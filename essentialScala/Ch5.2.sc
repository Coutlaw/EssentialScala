// Touples
// topules can be declared and used as follows
Tuple2("hi", 1)
// or
("Still a touple", 1)
//We can define methods that accept tuples as parameters using the same syntax:

def tuplized[A, B](in: (A, B)) = in._1
// tuplized: [A, B](in: (A, B))A

tuplized(("a", 1))
// res5: String = a
// We can also pattern match on tuples as follows:

(1, "a") match {
  case (a, b) => a + b
}
// res6: String = 1a


// Although pattern matching is the natural way to deconstruct a tuple,
// each class also has a complement of fields named _1, _2 and so on:

val x = (1, "b", true)
// x: (Int, String, Boolean) = (1,b,true)

x._1
// res7: Int = 1

x._3
// res8: Boolean = true

def intOrString(input: Boolean): Sum[Int, String] =
  if(input == true) {
    Left[Int, String](123)
  } else {
    Right[Int, String]("abc")
  }

Left[Int, String](1).value
// res9: Int = 1

Right[Int, String]("foo").value
// res10: String = foo

val sum: Sum[Int, String] = Right("foo")
// sum: sum.Sum[Int,String] = Right(foo)

sealed trait Sum[A, B]{
  def fold[C](left: A => C, right: B => C): C =
    this match {
      case Left(a) => left(a)
      case Right(b) => right(b)
    }
}
final case class Left[A, B](value: A) extends Sum[A, B]
final case class Right[A, B](value: B) extends Sum[A, B]

sealed trait Maybe[A]{
  def fold[B](full: A => B, empty: B): B =
    this match {
      case Full(v) => full(v)
      case Empty() => empty
    }
}
final case class Full[A](inVal: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]

