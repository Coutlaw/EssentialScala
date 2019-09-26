// Monoid

trait Monoid[A] {
  def combine(x: A, y: A): A
  def empty: A
}

//def associateiveLaw[A](x: A, y: A, z: A)(implicit m: Monoid[A]): Boolean = {
//  m.combine(x,m.combine(y,z)) == m.combine(m.combine(x,y), z)
//}
//
//def identityLaw[A](x: A)(implicit m: Monoid[A]): Boolean = {
//  (m.combine(x,m.empty) ==x) && (m.combine(m.empty,x) ==x)
//}

implicit val booleanAnd: Monoid[Boolean] =
  new Monoid[Boolean] {
    def combine(a:Boolean, b: Boolean) = a && b
    def empty = true
  }
implicit val booleanOr: Monoid[Boolean] =
  new Monoid[Boolean]{
    def combine(a: Boolean, b: Boolean) = a || b
    def empty = false
  }
implicit val booleanEither: Monoid[Boolean] =
  new Monoid[Boolean]{
    def combine(a: Boolean, b: Boolean) = (!a && b) || (a && !b)
    def empty = false
  }
implicit val booleanXor: Monoid[Boolean] =
new Monoid[Boolean] {
  def combine(a: Boolean, b: Boolean) = (!a || b) && (a && !b)
  def empty = true
}


import cats.Monoid
import cats.instances.string._

Monoid[String].combine("Hi", "there")
Monoid[String].empty
//println("test")

import cats.instances.string._ // for Monoid
import cats.syntax.semigroup._ // for |+|

val stringResult = "Hi " |+| "there" |+| Monoid[String].empty
// stringResult: String = Hi there

import cats.instances.int._ // for Monoid

val intResult = 1 |+| 2 |+| Monoid[Int].empty
// intResult: Int = 3

def add(items: List[Int]): Int =
items.sum

import cats.instances.string._ // for Monoid
//import cats.syntax.semigroup._ // for |+|

//def addBetter[A](items: List[A])(implicit monoidID: Monoid[A]):
// A = items.foldLeft(monoidID.empty)(_ |+| _)