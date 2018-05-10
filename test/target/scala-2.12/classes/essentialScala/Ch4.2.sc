// Using pattern matching and recursion

sealed trait IntList
case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList

def sum(list: IntList): Int =
    list match {
      case End => 0
      case Pair(hd, tl) => hd + sum(tl)
    }

// recursion with end as the base case
Pair(1,Pair(2,Pair(3,End)))
// creates a linked list of pairs
// another way of writing the same list
//val d = End()
//val c = Pair(3, d)
//val b = Pair(2, c)
//val a = Pair(1, b)
// Where..
//a represents the sequence 1, 2, 3
//b represents the sequence 2, 3
//c represents the sequence 3 (only one element)
//d represents an empty sequence


val example = Pair(1, Pair(2, Pair(3, End)))
assert(sum(example) == 6)
assert(sum(example.tail) == 5)
assert(sum(End) == 0)

// formula for recursion in scala, base case and recursive case defined
sealed trait RecursiveExample
final case class RecursiveCase(recursion: RecursiveExample) extends RecursiveExample
case object BaseCase extends RecursiveExample

//Tail recursion
def sum(list: IntList, total: Int = 0): Int =
  list match {
    case End => total
    case Pair(hd, tl) => sum(tl, total + hd)
  }