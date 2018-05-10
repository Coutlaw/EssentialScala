import cats.Eq
import cats.instances.int._
import cats.Show
import cats.syntax.show._

val eqInt = Eq[Int]
eqInt.eqv(123,123)
eqInt.eqv(123,124)

//eqInt.eqv(123, "123")
// above is comiler error
123.show =="123"

// import cats interface syntax
import cats.syntax.eq._

123 === 123
123 === 134
// same as eqInt.eqv(123,123)


// comparing objects
import cats.instances.int._
import cats.instances.option._

//Some(1) === None
(Some(1): Option[Int]) === (None: Option[Int])
Option(1) === Option.empty[Int]

import cats.syntax.option._
1.some === none[Int]
1.some =!= none[Int]

final case class Cat(name: String, age: Int, color: String)


import cats.syntax.eq._
import cats.Eq
import cats.instances.int._
import cats.instances.string._

implicit val catEq: Eq[Cat] =
  Eq.instance[Cat] { (cat1, cat2) =>
    (cat1.name === cat2.name) &&
      (cat1.age === cat2.age) &&
      (cat1.color === cat1.color)
  }

val cat1 = Cat("Garfield",   38, "orange and black")
val cat2 = Cat("Heathcliff", 33, "orange and black")

cat1 === cat2
