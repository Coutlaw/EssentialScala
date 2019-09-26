// Comprehensions
Seq(1,2,3).map(_*2)

// The equivelent for comprehension is
for{
  x <- Seq(1,2,3)
} yield x * 2


val data = Seq(Seq(1), Seq(2, 3), Seq(4, 5, 6))
// return data *2 flattened
data.flatMap(_.map(_*2))

// the comprehension is
// take the data, break into subsequences,
// then take subsequence and break into elements
// multiply all elements by 2
for{
  subseq <- data
  element <- subseq
} yield element * 2

// if there is only one generator
for(x <- Seq(1, 2, 3)) yield {
  x * 2
}

sealed trait Option[+A] {
  def getOrElse[B >: A](default: B): B

  def isEmpty: Boolean
  def isDefined: Boolean = !isEmpty

  def filter(func: A => Boolean): Option[A]
  def find(func: A => Boolean): Option[A]

  def map[B](func: A => B): Option[B]
  def flatMap[B](func: A => Option[B]): Option[B]
  def foreach(func: A => Unit): Unit

  def foldLeft[B](initial: B)(func: (B, A) => B): B
  def foldRight[B](initial: B)(func: (A, B) => B): B
}

// Option
def readInt(str: String): Option[Int] =
  if(str matches "-?\\d+") Some(str.toInt) else None

readInt("123")
readInt("abc")

// to insure we get something that is an int
readInt("abc").getOrElse(0)

// pattern matching with some and none
readInt("123") match {
  case Some(number) => number + 1
  case None         => 0
}

sealed trait Option[+A] {
  def getOrElse[B >: A](default: B): B

  def isEmpty: Boolean
  def isDefined: Boolean = !isEmpty

  def filter(func: A => Boolean): Option[A]
  def find(func: A => Boolean): Option[A]

  def map[B](func: A => B): Option[B]
  def flatMap[B](func: A => Option[B]): Option[B]
  def foreach(func: A => Unit): Unit

  def foldLeft[B](initial: B)(func: (B, A) => B): B
  def foldRight[B](initial: B)(func: (A, B) => B): B
}

def sum(optionA: Option[Int], optionB: Option[Int]): Option[Int] =
  optionA.flatMap(a => optionB.map(b => a + b))