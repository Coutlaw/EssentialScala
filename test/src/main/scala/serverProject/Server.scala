package serverProject


import java.net.ServerSocket

object Server extends App {
  val serSock = new ServerSocket(4000)
  println("Accepting")
  val sock = serSock.accept()
  println("got socket" + sock)

}
