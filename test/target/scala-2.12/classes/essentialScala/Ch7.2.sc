trait HtmlWriter[A] {
  def write(in: A): String
}

object PersonWriter extends HtmlWriter[Person] {
  def write(person: Person) = s"<span>${person.name} &lt;${person.email}&gt;</span>"
}
case class Person(name: String, email: String)

object HtmlUtil{
  def htmlify[A](data: A)(implicit writer: HtmlWriter[A]):
  String = {
    writer.write(data)
  }
}

HtmlUtil.htmlify(Person("John", "john@example.com"))(PersonWriter)


//HtmlUtil.htmlify(2)

//
//object HtmlWriter {
//  def apply[A](implicit writer: HtmlWriter[A]): HtmlWriter[A] =
//    writer
//}
//
//HtmlWriter[Person].write(Person("Noel", "noel@example.org"))


