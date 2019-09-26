// Type classes

// problem case, cannot support different formats, and we have to re write solutions of different context
trait HtmlWriteable {
  def toHtml: String
}

final case class Person(name: String, email: String) extends HtmlWriteable {
  def toHtml = s"<span>$name &lt;$email&gt;</span>"
}

Person("John", "john@example.com").toHtml


// Poly Morphism, now A can be multiple different things allowing better reuse
trait HtmlWriter[A] {
  def write(in: A): String
}

object PersonWriter extends HtmlWriter[Person] {
  def write(person: Person) = s"<span>${person.name} &lt;${person.email}&gt;</span>"
}

// extending the HtmlWriter to something else
import java.util.Date

object DateWriter extends HtmlWriter[Date]{
  def write(in: Date) = s"<span>${in.toString}</span>"
}

DateWriter.write(new Date)


// extending it again
object ObfuscatedPersonWriter extends HtmlWriter[Person] {
  def write(person: Person) =
    s"<span>${person.name} (${person.email.replaceAll("@", " at ")})</span>"
}

ObfuscatedPersonWriter.write(Person("John", "john@example.com"))


// pattern for Type classes
//  trait ExampleTypeClass[A] {
//    def doSomething(in: A): Foo
//  }


// Problems

trait Equal[A]{
  def isEqual(v1: A, v2: A): Boolean
}
case class Persons(name: String, email: String)

object EmailEqual extends Equal[Person]{
  def isEqual(v1: Persons, v2: Persons): Boolean =
    v1.email == v2.email
}

object bothEqual extends Equal[Person]{
  def isEqual(v1: Persons, v2:Persons): Boolean =
    v1.email == v2.email && v1.name == v2.email
}












