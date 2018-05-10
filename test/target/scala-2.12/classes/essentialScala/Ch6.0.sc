// SEQUENCES
//**************************************
val sequence = Seq(1,2,3)

sequence.apply(1)
// gets the value at position 1
// can also be written
sequence(1)

// you can get the head and tail of a sequence
sequence.head
// tail returns the list minus the head
sequence.tail
// returns the first thing in the tail list
sequence.tail.head

// get the head if its optional
sequence.headOption
//seq().headOption

// get the length of a sequence
sequence.length

// searching if somehting is contained
sequence.contains(2)

sequence.find(_ ==2)
sequence.find(_>0)

sequence.filter(_>1)

// Sorting the sequence
sequence.sortWith(_>_)
// sorts largest to smallest

// appending with infix
//sequence.:+(4)
// or
sequence :+ 4

// appending with prepend
0 +: sequence
// or
sequence.+:(0)

// appending with concatenation
sequence ++ Seq(4,5,6)
// ***************************************
// END


// Lists
// ***************************************
val list = 1::2::3::Nil
4::5::list

// same list constructor using the apply method
List(1,2,3)

List(1,2,3):::List(4,5,6)

// import wildcards
import scala.collection.immutable._
Vector(1,2,3)
Queue(1,2,3)


Seq(1,2,3).mkString(",")
Seq(1, 2, 3).mkString("[ ", ", ", " ]")

Some(123).isDefined // returns true
Some(123).isEmpty   // returns false
None.isDefined      // returns false
None.isEmpty        // returns true

val animals = Seq("cat", "dog", "penguin")







