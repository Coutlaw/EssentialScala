// FUNCTORS
// informally a functor is anything with a map method
// instead of traversing and modifying a list, think of a map
// as one transformation of a list, using a function and applying it
// to the list in one go

List(1,2,3).map(n => n+1)

List(1,2,3).
  map(n => n+1).
  map(n => n*2).
  map(n => n +"!")

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

val future: Future[String] =
  Future(123).
    map(n => n + 1).
    map(n => n * 2).
    map(n => n + "!")

Await.result(future, 1.second)

import scala.util.Random

val future1 = {
  // Initialize Random with a fixed seed:
  val r = new Random(0L)

  // nextInt has the side-effect of moving to
  // the next random number in the sequence:
  val x = Future(r.nextInt)

  for {
    a <- x
    b <- x
  } yield (a, b)
}

val future2 = {
  val r = new Random(0L)

  for {
    a <- Future(r.nextInt)
    b <- Future(r.nextInt)
  } yield (a, b)
}

val result1 = Await.result(future1, 1.second)
// result1: (Int, Int) = (-1155484576,-1155484576)

val result2 = Await.result(future2, 1.second)
// result2: (Int, Int) = (-1155484576,-723955400)

// these resutls are different, because of how .nextInt is called
// future 2 calls the method twice, and future 1 calls it once
// .nextInt will get a different number every time, hence the different numbers

val func1: Int => Double =
  (x: Int) => x.toDouble

val func2: Double => Double =
  (y: Double) => y * 2

(func1  andThen func2)(1)
func2(func1(1))

val func =
  ((x: Int) => x.toDouble)
  .map(x => x + 1)
  .map(x => x * 2)
  .map(x => x + "!")
func(123)







