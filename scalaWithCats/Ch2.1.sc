// MONOIDS

//formally a monoid for a type A is:
//  1: an operation combine with type (A,A) => A
//  2: an element empty of type A

//this definition translates nicely to scala code

trait Monoid[A] {
  def combine(x: A, y: A): A
  def empty: A
}

def associativeLaw[A](x: A, y: A, z: A)(implicit m: Monoid[A]):
Boolean = {
  m.combine(x, m.combine(y, z)) == m.combine(m.combine(x, y), z)
}
def identityLaw[A](x:A)(implicit m: Monoid[A]): Boolean ={
  (m.combine(x, m.empty) == x) && (m.combine(m.empty, x) == x)
}


// SEMIGROUPS

// a semigroup is just the combine part of a monoid.
// many semigroups are also monoids, but some data types for
// which we cannot define an empty element.
// for example, non empty sequences, and positive integers
// cannot sensibly be defined with empty elements

// cats NonEmptyList

trait Semigroup[A]{
  def combine(x: A, y: A)
}
trait Monoid2[A] extends Semigroup[A]{
  def empty: A
}

// Questions and work

// monoids of boolean values
implicit val booleanAnd: Monoid2[Boolean] =
  new Monoid2[Boolean] {
    def combine(a:Boolean, b: Boolean) = a && b
    def empty = true
  }
implicit val booleanOr: Monoid2[Boolean] =
  new Monoid2[Boolean]{
    def combine(a: Boolean, b: Boolean) = a || b
    def empty = false
  }
implicit val booleanEither: Monoid2[Boolean] =
  new Monoid2[Boolean]{
    def combine(a: Boolean, b: Boolean) = (!a && b) || (a && !b)
    def empty = false
  }
implicit val booleanXor: Monoid2[Boolean] =
  new Monoid2[Boolean] {
    def combine(a: Boolean, b: Boolean) = (!a || b) && (a && !b)
    def empty = true
  }



