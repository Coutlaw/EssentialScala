def calculator(operand1: String, operator: String, operand2: String): Unit = {
  val result = for {
    a   <- readInt(operand1)
    b   <- readInt(operand2)
    ans <- operator match {
      case "+" => Some(a + b)
      case "-" => Some(a - b)
      case "*" => Some(a * b)
      case "/" => divide(a, b)
      case _   => None
    }
  } yield ans

  result match {
    case Some(number) => println(s"The answer is $number!")
    case None         => println(s"Error calculating $operand1 $operator $operand2")
  }
}

