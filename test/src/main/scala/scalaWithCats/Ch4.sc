// Option monads
def parseInt(str: String): Option[Int] =
scala.util.Try(str.toInt).toOption

def divide(a: Int, b: Int): Option[Int]=
if (b ==0) None else Some(a/b)


def stringDivideBy(aStr: String, bStr: String): Option[Int] =
// parseInt(aStr) would return an option, (.flatmap) then flattens to an int
  parseInt(aStr).flatMap { aNum =>
    parseInt(bStr).flatMap { bNum =>
      divide(aNum, bNum)
    }
  }

stringDivideBy("4", "2")

def stringDivideBy2(aStr: String, bStr: String): Option[Int] =
  for {
    aNum <- parseInt(aStr)
    bNum <- parseInt(bStr)
    ans  <- divide(aNum, bNum)
  } yield ans

stringDivideBy2("4", "2")


// Lists monads
val stuff = for {
  x <- (1 to 3).toList
  y <- (4 to 5).toList
} yield (x, y)

// res5: List[(Int, Int)] = List((1,4), (1,5), (2,4), (2,5), (3,4), (3,5))

// sum all the touple pairs
val sums = stuff.map(t => t._1 + t._2)

// folds determine which end of the list you start with, and which position your acc is in
// fold right, starts on the right, and the accumulator is on the right
sums.foldRight(""){(z, acc) => acc + "+" + z}
sums.foldRight(""){(z, acc) => acc + z + "+"}


// fold left, starts on the left and accumulator is on the left
sums.foldLeft(""){(acc, z) => z + acc +  "+"}
sums.foldLeft(""){(acc, num) => acc + num +  "+"}

// fold can just be computed in parallel
sums.fold("")((z, acc) => z + acc.toString +  "+")

//final solution, fold from left, start with empty string
// accumulate the number + the string, then drop the last string
sums.foldLeft(""){(acc, num) => acc + num + "+"}.dropRight(1)

// mathematical calculation, fold left, start at 0, add numbers
sums.foldLeft(0){(acc, num) => acc + num}

//Monad Laws
//
//pure and flatMap must obey a set of laws that allow us to sequence operations freely without unintended glitches and side-effects:
//
//  Left identity: calling pure and transforming the result with func is the same as calling func:
//
//  pure(a).flatMap(func) == func(a)
//Right identity: passing pure to flatMap is the same as doing nothing:
//
//m.flatMap(pure) == m
//Associativity: flatMapping over two functions f and g is the same as flatMapping over f and then flatMapping over g:
//
//m.flatMap(f).flatMap(g) == m.flatMap(x => f(x).flatMap(g))}


import scala.language.higherKinds

trait Monad[F[_]] {
  def pure[A](a: A): F[A]

  def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]

  def map[A, B](value: F[A])(func: A => B): F[B] =
    flatMap(value)(a => pure(func(a)))
}
