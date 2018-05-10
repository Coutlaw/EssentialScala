package serverProject


import java.net.Socket

object Client extends App {
  println("Making Socket")
  val sock = new Socket("localhost", 4000)
  println("Socket Made")
}
