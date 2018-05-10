
// Church encoding
// classic OOP, easy to add new operations (EX. trig functions), difficult to add new actions (EX. return BigDecimal)
class Calculator {
  def literal(v: Double): Double = v
  def add(a: Double, b: Double): Double = a + b
  def subtract(a: Double, b: Double): Double = a - b
  def multiply(a: Double, b: Double): Double = a * b
  def divide(a: Double, b: Double): Double = a / b
}

// Reification
// classic FP, easy to add new actions(Formatting options), Impossible to add new operations(Trig functions)
sealed trait Calculation
final case class Literal(v: Double) extends Calculation
final case class Add(a: Calculation, b: Calculation) extends Calculation
final case class Subtract(a: Calculation, b: Calculation) extends Calculation
final case class Multiply(a: Calculation, b: Calculation) extends Calculation
final case class Divide(a: Calculation, b: Calculation) extends Calculation


def eval(c: Calculation): Double =
  c match {
    case Literal(v)     => v
    case Add(a, b)      => eval(a) + eval(b)
    case Subtract(a, b) => eval(a) - eval(b)
    case Multiply(a, b) => eval(a) * eval(b)
    case Divide(a, b)   => eval(a) / eval(b)
  }


def prettyPrint(c: Calculation): String =
  c match {
    case Literal(v)     => v.toString
    case Add(a, b)      => s"${prettyPrint(a)} + ${prettyPrint(b)}"
    case Subtract(a, b) => s"${prettyPrint(a)} - ${prettyPrint(b)}"
    case Multiply(a, b) => s"${prettyPrint(a)} * ${prettyPrint(b)}"
    case Divide(a, b)   => s"${prettyPrint(a)} / ${prettyPrint(b)}"
  }


//Church encoding: OO way of implementation
//vs
//Reification: FP way of implementation

//We see that OO and FP allow easy extension in different directions:
//
//  OO makes it easy to add new operations, but makes adding new actions hard; whereas
//FP makes it easy to add new actions, but makes adding new operations hard.