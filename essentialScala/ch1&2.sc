"hello world"
"hello world".toUpperCase()

for(i <-1 to 3){
  println(i)
}

// compute the minimum of values
2.min(3)

// Take functions
"Hello World!".take(2+3)
// The above firsts evaluates the string
// then the expression evaluates 2+3
// lastsly take is evaluated with the result of 2+3

// Operators

43 - 3 +2

43.-(3).+(2)

// infix operator notations

"the quick brown fox" split " "

// precedence rules of math
2 * 3
4*5
2*3 + 4*5

"The \n usual \t escape characters apply"

object Test{
  def name: String = "Probably the best object ever"
}
Test.name

object Test3{
  def hello(name: String) =
    "hello " + name
}
Test3.hello("Cass")

object Test4 {
  val name = "Noel"
  def hello(other: String) =
    name + " says hi to " + other
}
Test4.hello("John")
object Test7 {
  val simpleFiled = {
    println("Evaluating simpleFiled")
    42
  }
  def simpleMethod = {
    println("Evaluating simpleMethod")
    42
  }
}

Test7
object calc {
  def square(x: Double) = x * x
  def cube(x: Double) = x * square(x)
}
calc.cube(2.0)
// Test Cases
assert(calc.square(2.0) == 4.0)

// scala tricks
def square (in: Double): Double = ???


// This creates a person object then communicates with an alien object

object person {
  val firstName = "Dave"
  val lastName = "Gurnell"
}

object alien {
  def greet(p: person.type) =
    "Greetings, " + p.firstName + " " + p.lastName
}

alien.greet(person)
// res15: String = Greetings, Dave Gurnell