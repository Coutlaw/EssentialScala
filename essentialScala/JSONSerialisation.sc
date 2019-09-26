sealed trait JsValue {
  def stringify: String
}

final case class JsObject(values: Map[String, JsValue]) extends JsValue {
  def stringify = values
    .map { case (name, value) => "\"" + name + "\":" + value.stringify }
    .mkString("{", ",", "}")
}

final case class JsString(value: String) extends JsValue {
  def stringify = "\"" + value.replaceAll("\\|\"", "\\\\$1") + "\""
}

val obj = JsObject(Map("foo" -> JsString("a"), "bar" -> JsString("b"), "baz" -> JsString("c")))
obj.stringify

//*****************************************************

// Let’s create a type class for converting Scala data to JSON.
// Implement a JsWriter trait containing a single abstract method
// write that converts a value to a JsValue.

// creating a Type class
// start with trait of generic type A, with write, that returns JsValue
trait JsWriter[A]{
  def write(value: A): JsValue
}

// object with toJson method, with implicit JsWriter
object JsUtil {
  def toJson[A](value: A)(implicit writer: JsWriter[A]) =
    writer write value
}

// ********************************************************
import java.util.Date

sealed trait Visitor {
  def id: String
  def createdAt: Date
  def age: Long = new Date().getTime() - createdAt.getTime()
}

final case class Anonymous(
                            id: String,
                            createdAt: Date = new Date()
                          ) extends Visitor

final case class User(
                       id: String,
                       email: String,
                       createdAt: Date = new Date()
                     ) extends Visitor

implicit object AnonymousWriter extends JsWriter[Anonymous]{
  def write(value: Anonymous) = JsObject(
    Map(
    "id" -> JsString(value.id),
    "createdAt" -> JsString(value.createdAt.toString)
  ))
}

implicit object UserWriter extends JsWriter[User]{
  def write(value: User) = JsObject(
    Map(
      "id" -> JsString(value.id),
      "email" -> JsString(value.email),
      "createdAt" -> JsString(value.createdAt.toString)
  ))

}

implicit object VisitorWriter extends JsWriter[Visitor]{
  def write(value: Visitor) = value match{
    case anon: Anonymous => JsUtil.toJson(anon)
    case user: User => JsUtil.toJson(user)
  }
}
val visitors: Seq[Visitor] = Seq(Anonymous("001", new Date), User("003", "dave@xample.com", new Date))

val testVal: JsObject = AnonymousWriter.write(Anonymous("002", new Date))





