// GENERIC FOLD ABSTRACTION
sealed trait LinkedList[A]{
  def fold[B](end: B, pair: (A,B) => B): B =
    this match{
      case End() => end
      case Pair(h, t) => pair(h,t.fold(end, pair))
    }
}

final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
final case class End[A]() extends LinkedList[A]

// Place holder syntax
// extreme shorthand for inline functions
((_: Int) *2)
// equivalent to (a: Int) => a*2

/* More shorthand expressions

_ + _     // expands to `(a, b) => a + b`
foo(_)    // expands to `(a) => foo(a)`
foo(_, b) // expands to `(a) => foo(a, b)`
_(foo)    // expands to `(a) => a(foo)`
// and so on...

 */

object Sum{
  def sum(x: Int, y: Int) = x+y
}
Sum.sum(2,3)

// mutltiple parameter examples
def example(x: Int)(y: Int) = x+y
example(2)(2)

// calling the fold
//fold(0){(total, elt) => total + elt}
// or easier
//fold(0,(total, elt) => total+elt)

sealed trait Tree[A]{
  def fold[B](node: (B,B) => B, leaf: A=>B): B
}
final case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A]{
  def fold[B](node: (B,B) => B, leaf: A=> B): B=
    node(left.fold(node,leaf),right.fold(node,leaf))
}
final case class Leaf[A](value: A) extends Tree[A] {
  def fold[B](node: (B,B) => B, leaf: A=>B): B = leaf(value)
}

val tree: Tree[String] =
  Node(Node(Leaf("To"), Leaf("iterate")),
    Node(Node(Leaf("is"), Leaf("human,")),
      Node(Leaf("to"), Node(Leaf("recurse"), Leaf("divine")))))

tree.fold[String]((a,b) => a+ " " +b, str => str)



