// Monads
import scala.util.Try

// Option values
val opt1 = Some(1)
val opt2 = Some(2)
val opt3 = Some(3)

// sequence values
val seq1 = Seq(1)
val seq2 = Seq(2)
val seq3 = Seq(3)

// try values
val try1 = Try(1)
val try2 = Try(2)
val try3 = Try(3)


for {
  x <- opt1
  y <- opt2
  z <- opt3
} yield x+y+z

for {
  x <- seq1
  y <- seq2
  z <- seq3
} yield x + y + z

for {
  x <- try1
  y <- try2
  z <- try3
} yield x + y + z

// using comprehensions to filter
for(x <- Seq(-2,-1,0,1,2) if x >0) yield x

// matrix addition
for{
  x<- Seq(1,2,3)
  y<- Seq(4,5,6)
} yield x+y

Seq(1,2,3).zip(Seq(4,5,6))

for(x <- Seq(1, 2, 3).zip(Seq(4, 5, 6)))
  yield { val (a, b) = x; a + b }

for(x <- Seq(1, 2, 3).zipWithIndex) yield x
