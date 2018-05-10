// MONOIDS IN CATS

// THE MONOID TYPE CLASS
// we usually import type classes from the cats package
import cats.Monoid
import cats.Semigroup

// examples
import cats.instances.string._

Monoid[String].combine("Hi", "there")
Monoid[String].empty

// both of the above are equal to
// Monoid.apply[String].combine("hi", "there")
// Monoid.apply[String].empty

// since monoid extends semigroup, if no empyt is needed we can
// write the following

Semigroup[String].combine("Hi", "there")

// pull in int instead of String
import cats.instances.int._
Monoid[Int].combine(32,10)

// with options
import cats.instances.option._

val a = Option(22)
val b = Option(20)
Monoid[Option[Int]].combine(a, b)

// NEW SYNTAX
import cats.syntax.semigroup._

val stringResult = "Hi" |+| "there" |+| Monoid[String].empty
val intResult = 1 |+| 2 |+| Monoid[Int].empty


// examples
// make an add for a list
def add(items: List[Int]): Int =
  // fold from left, starting at 0, add everything
  items.foldLeft(Monoid[Int].empty)(_+_)

add(List(1,2,3,4,5))

// make a better add that works for List[Option[Int]]
def betterADD[A: Monoid](items: List[A]):
A = items.foldLeft(Monoid[A].empty)(_+_)

// making a monoid class for adding orders
case class Order(totalCost: Double, quantity: Double)
implicit val monoid: Monoid[Order] = new Monoid[Order]{
  def combine(o1: Order, o2: Order) =
    Order(
      o1.totalCost + o2.totalCost,
      o1.quantity + o2.quantity
    )
  def empty = Order(0,0)
}








