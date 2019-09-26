// Ordering
import scala.math.Ordering
val minOrdering = Ordering.fromLessThan[Int](_<_)
val maxOrdering = Ordering.fromLessThan[Int](_>_)
List(1,2,3,4,5).sorted(maxOrdering)
implicit val ordering = Ordering.fromLessThan[Int](_<_)
List(2,4,3).sorted
List(1,10,5,8,2).sorted
// this can lead to ambituity, one implicit value per operation

// Exercises
val absOrdering = Ordering.fromLessThan[Int]{
  (x,y) => Math.abs(x) < Math.abs(y)
}

final case class Rational(numerator: Int, denominator: Int)
