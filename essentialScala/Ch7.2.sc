trait HtmlWriter[A] {
  def write(in: A): String
}

implicit object PersonWriter extends HtmlWriter[Person] {
  def write(person: Person) = s"<span>${person.name} &lt;${person.email}&gt;</span>"
}
case class Person(name: String, email: String)

//object HtmlUtil{
//  def htmlify[A](data: A)(implicit writer: HtmlWriter[A]):
//  String = {
//    writer.write(data)
//  }
//}
//
//HtmlUtil.htmlify(Person("John", "john@example.com"))(PersonWriter)


//HtmlUtil.htmlify(2)

// Prevents us from writing the above code
object HtmlWriter{
  def apply[A](implicit writer: HtmlWriter[A]): HtmlWriter[A] =
    writer
}
// HtmlWriter -> writer [PersonWriter] -> .write(input: Person)
HtmlWriter[Person].write(Person("Noel", "noel@example.org"))

// Poblems

trait Equal[A] {
  def equal(v1: A, v2: A): Boolean
}

object EmailEqual extends Equal[Person] {
  def equal(v1: Person, v2: Person): Boolean =
    v1.email == v2.email
}

object NameEmailEqual extends Equal[Person] {
  def equal(v1: Person, v2: Person): Boolean =
    v1.email == v2.email && v1.name == v2.name
}

object Eq{
  def apply[A](v1: A, v2: A)(implicit equal: Equal[A]): Boolean =
    equal.equal(v1,v2)
}

//Eq(Person("Noel", "noel@example.com"), Person("Noel", "noel@example.com"))

implicit class HtmlOps[T](data: T) {
  def toHtml(implicit writer: HtmlWriter[T]) =
    writer.write(data)
}

Person("john", "john@example.com").toHtml
