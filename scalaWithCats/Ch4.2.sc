import cats.instances.option._ // for Monad
import cats.Monad

Monad[Option].flatMap(Option(1))(a=> Option(a*2))

import cats.instances.list._
Monad[List].flatMap(List(1,2,3))(a => List(a, a*10))

import cats.instances.vector._
Monad[Vector].flatMap(Vector(1,2,3))(a => Vector(a, a*10))

import cats.instances.option._   // for Monad
import cats.instances.list._     // for Monad
import cats.syntax.applicative._ // for pure
import cats.instances.future._ // for Monad

1.pure[Option]
1.pure[List]


import cats.syntax.functor._
import cats.syntax.flatMap._
import scala.language.higherKinds

def sumSquare[F[_]: Monad](a: F[Int], b: F[Int]): F[Int] =
a.flatMap(x => b.map(y=> x*x + y*y))

def sumSquare2[F[_]: Monad](a: F[Int], b: F[Int]): F[F[Int]] =
  a.map(x => b.map(y=> x*x + y*y))

import cats.instances.option._
import cats.instances.list._

sumSquare(Option(3),Option(2))
sumSquare2(Option(3),Option(2))

sumSquare(List(1,2,3),List(4,5))
sumSquare2(List(1,2,3),List(4,5))


def sumSquare3[F[_]: Monad](a: F[Int], b: F[Int]): F[Int] =
for{
  x <- a
  y <- b
} yield x*x + y*y

// this method needs context because we specifited an F wrapper for the return value
sumSquare3(Option(3), Option(4))

// doesnt work!, this style does not have the litterals in wrappers
//sumSquare3(3,4)

import cats.Id

sumSquare(3: Id[Int], 4: Id[Int])

"Dave" : Id[String]
"Dave"
123: Id[Int]
List(1,2,3) : Id[List[Int]]

val a = Monad[Id].pure(3)
val b = Monad[Id].flatMap(a)(_ + 1)

val c = Monad[Id].pure(3)
val d = Monad[Id].flatMap(a)(_+1)

import cats.syntax.functor._
import cats.syntax.flatMap._

for {
  x <- a
  y <- b
} yield x + y

def pure[A](value: A): Id[A] =
  value

def map[A, B](init: Id[A])(func: A => B): Id[B] =
  func(init)

def flatMap[A, B](init: Id[A])(func: A=>Id[B]): Id[B] =
  func(init)


