// Create a type class Printable

//  Let’s define a Printable type class to work around these problems:
//
//  1 Define a type class Printable[A] containing a single method format. format should accept a value of type A and return a String.
//
//  2 Create an object PrintableInstances containing instances of Printable for String and Int.
//
//  3 Define an object Printable with two generic interface methods:
//
//  format accepts a value of type A and a Printable of the corresponding type. It uses the relevant Printable to convert the A to a String.
//
//  print accepts the same parameters as format and returns Unit. It prints the A value to the console using println.


// Start with the trait, that accepts a value of type A
// and returns String

trait Printable[A]{
  def format(value: A): String
}

// package instances into an object

object PrintableInstances{
  // string instance
  implicit val stringPrintable = new Printable[String]{
    def format(input: String) = input
  }

  // int instance
  implicit val intPrintable = new Printable[Int]{
    def format(input: Int) = input.toString()
  }
}

// finally define an interface Printable
object Printable{
  def format[A](input: A) (implicit p: Printable[A]): String =
    p.format(input)
  def print[A](input: A)(implicit p: Printable[A]): Unit =
    println(format(input))
}
