// Pattern matching
sealed trait Feline{
  def dinner: Food =
    this match{
      case Lion() => Antelope
      case Tiger() => TigerFood
      case Panther() => Licorice
      case Cat(favouriteFood) => CatFood(favouriteFood)
    }
}
final case class Lion() extends Feline{

}
final case class Tiger() extends Feline{

}
final case class Panther() extends Feline{

}
final case class Cat(favouriteFood: String) extends Feline{

}

sealed trait Food
case object Antelope extends Food
case object TigerFood extends Food
case object Licorice extends Food
final case class CatFood(food: String) extends Food


object Diner {
  def dinner(feline: Feline): Food =
    feline match {
      case Lion() => Antelope
      case Tiger() => TigerFood
      case Panther() => Licorice
      case Cat(food) => CatFood(food)
    }
}


//Traffic light with polymorphism

//sealed trait TrafficLight{
//  def next: TrafficLight
//}
//case object Red extends TrafficLight{
//  def next: TrafficLight =
//    Green
//}
//case object Green extends TrafficLight{
//  def next: TrafficLight =
//    Yellow
//}
//case object Yellow extends  TrafficLight{
//  def next: TrafficLight =
//    Red
//}

//With pattern matching
sealed trait TrafficLight{
  def next: TrafficLight =
  this match{
    case Red => Green
    case Green => Yellow
    case Yellow => Red
  }
}
case object Red extends TrafficLight
case object Green extends TrafficLight
case object Yellow extends  TrafficLight

/*
  for choosing polymorphism or pattern matching. Pattern matching is better
  when the result does not depend on external data.
*/

// Calculation data type

// given a calculation that
sealed trait Calculation
final case class Success(result: Int) extends Calculation
final case class Failure(reason: String) extends Calculation

object Calculator{
  def +(calc: Calculation, operand: Int): Calculation =
    calc match {
      case Success(result) => Success(result + operand)
      case Failure(reason) => Failure(reason)
    }
  def -(calc: Calculation, operand: Int): Calculation =
    calc match {
      case Success(result) => Success(result - operand)
      case Failure(reason) => Failure(reason)
    }
  def /(calc: Calculation, operand: Int): Calculation =
    calc match{
      case Success(result) =>
        operand match {
          case 0 => Failure("Divisiion by Zero, not allowed")
          case _ => Success(result / operand)
        }
      case Failure(reason) => Failure(reason)
    }
}