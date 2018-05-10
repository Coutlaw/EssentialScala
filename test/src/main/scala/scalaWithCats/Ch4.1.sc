// Monadic behavior is formally captured in two operations
// 1: Pure, of type A => F[A]
// 2: flatMap, of type (F[A], A => F[B]) => F[B]
import scala.language.higherKinds

trait Monad[F[_]] {
  def pure[A](value: A): F[A]
  def flatMap[A,B](value: F[A])(func: A => F[B]):
  F[B]
  def map[A, B](value: F[A])(func: A => B): F[B] =
    flatMap(value)(a => pure(func(a)))
}

//  Monad Laws
//
//  pure and flatMap must obey a set of laws that allow us to sequence operations freely without
//  unintended glitches and side-effects:
//
//    Left identity: calling pure and transforming the result with func is the same as calling func:
//
//    pure(a).flatMap(func) == func(a)
//  Right identity: passing pure to flatMap is the same as doing nothing:
//
//  m.flatMap(pure) == m
//  Associativity: flatMapping over two functions f and g is the same as flatMapping over f and then flatMapping over g:
//
//  m.flatMap(f).flatMap(g) == m.flatMap(x => f(x).flatMap(g))


import cats.Monad
import cats.instances.option._
import cats.instances.list._

val opt1 = Monad[Option].pure(3)

val opt2 = Monad[Option].map(opt1)(a => Some(a+2))
val opt3 = Monad[Option].flatMap(opt1)(a => Some(a+2))

val opt4 = Monad[Option].map(opt1)(a => Option(a+2))
val opt5 = Monad[Option].flatMap(opt1)(a => Option(a+2))





