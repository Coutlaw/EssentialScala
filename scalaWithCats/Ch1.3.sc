// using printable with cats.

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

  implicit val catPrintable = new Printable[Cat] {
    def format(cat: Cat) = {
      val name  = Printable.format(cat.name)
      val age   = Printable.format(cat.age)
      val color = Printable.format(cat.color)
      s"$name is a $age year-old $color cat."
    }
  }
}

// finally define an interface Printable
object Printable{
  def format[A](input: A) (implicit p: Printable[A]): String =
    p.format(input)
  def print[A](input: A)(implicit p: Printable[A]): Unit =
    println(format(input))
}

final case class  Cat(name: String, age: Int, color: String)

import PrintableInstances._

val cat = Cat("Garfield", 38, "ginger and black")
Printable.print(cat)
