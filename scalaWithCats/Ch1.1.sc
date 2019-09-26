//a type class is an interface or API that represenets some functionality
//we want to implement.

// represents the thing we want to interface with
sealed trait Json
final case class JsObject(get: Map[String, Json]) extends Json
final case class JsString(get: String) extends Json
final case class JsNumber(get: Double) extends Json
case object JsNull extends Json

// represents our type class
trait JsonWriter[A] {
  def write(value: A): Json
}


// instances of type classes provide implementations for
// the types we care about, including types from the scala
// standard library and types from our domain model
// In Scala we define instances by creating concrete implementations
// of the type class and tagging them with the implicit keyword
final case class Person(name: String, email: String)

// instances of our type class
object JsonWriterInstances{
  implicit val stringWriter: JsonWriter[String] =
    new JsonWriter[String]{
      def write(value: String): Json =
        JsString(value)
    }
  implicit val personWriter: JsonWriter[Person] =
    new JsonWriter[Person] {
      def write(value: Person):Json =
        JsObject(Map(
          "name" -> JsString(value.name),
          "email" -> JsString(value.email)
        ))
    }
  // more implicits as needed
}

// type class interface
object Json {
  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json =
    w.write(value)
}

import JsonWriterInstances._

Json.toJson(Person("Dave", "dave@example.com"))

// The implicitly method
def implicitly[A](implicit value: A): A = value
implicitly[JsonWriter[String]]







