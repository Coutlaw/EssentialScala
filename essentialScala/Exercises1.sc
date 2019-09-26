// Sum Type
sealed trait A
final case class B() extends A
final case class C() extends A

//val maybeLenght: Option[String]
//  => Option[Int] =
//  _ match{
//  case Nil => 0
//  case h::t => h.as[Int](maybeLenght) + 1
//}

// Product Type

case class Pair(b: A, c: A)

val swap: Pair => Pair =
  _ match {
    case Pair(b, c) => Pair(c,b)
  }

val x = new B()
val y = new C()
val z = new Pair(x, y)

swap(z)


// pattern matching for length of a list
def nLength(intList: List[Int]): Int =
  intList match{
    case Nil => 0
    case _::rest => 1 + nLength(rest)
}

nLength(List(1,2,3))


// pattern matching for sum of a list
def nSum(iL: List[Int]): Int =
iL match{
  case Nil => 0
  case h::t => h + nSum(t)
}

nSum(List(1,2,3,4))




// Sequencing computations
// folds
// when given Result[J] => String
// changing the types
sealed trait Result[J]{
  def fold[K](isFailed: K, f: J => K): K =
    this match {
      case Success(j) => f(j)
      case Failure() => isFailed
    }
  def map[K](f: J => K): Result[K] =
    this match{
      case Success(j) => f(j)
      case Failure() => Failure[K]()
    }
}

final case class Success[J](j: J) extends Result[J]
final case class Failure[J]() extends Result[J]


val w: Int => Result[String] = n => Success(n.toString())
