import cats.instances.int._
import cats.instances.string._
import cats.Show
import cats.syntax.show._
//
//
//val showInt: Show[Int] = Show.apply[Int]
//val showString: Show[String] = Show.apply[String]
//
//val intAsString: String = showInt.show(123)
//val stringAsString: String =  showString.show("abc")

val showInt = 123.show
val showString = "abc".show


import java.util.Date

// interface
implicit val dateShow: Show[Date] =
  new Show[Date]{
    def show(date: Date): String =
    s"${date.getTime}ms since the epoch."
}

// using the show method from dateShow
dateShow.show(new Date())





