//import java.sql.Timestamp

// creating classes
class Person {
  val firstName = "Cass"
  val lastName = "Outlaw"
  def name = firstName + " " + lastName
}

val cass = new Person
cass.firstName

// using objects to interact with classes
object alien {
  def greet(p: Person) =
    "Greetings " + p.firstName + " " + p.lastName
}

alien.greet(cass)

// class constructors
class Person2(first: String, last: String) {
  val firstName = first
  val lastName = last
  def name = firstName + " " + lastName

}

// creates the object
val dave = new Person2("Dave" , "Smith")
// performs the functional part
dave.name



// better more scala way of doing it
class Person3(val firstName: String, val lastName: String){
  def name = firstName + " " + lastName
}
// short hand way of doing what we did with dave
new Person3("Cass", "Outlaw").name

// exercises
//3.1
class Cat(val colour: String, val food: String){
  val oswald = new Cat("Black", "milk")
  val henderson = new Cat("Ginger", "Chips")
  val quentin = new Cat("Tabby and white", "Curry")
}

//3.2
object ChipShop {
  def willServe(cat: Cat) = if(cat.food == "Chips")
    true
  else
    false
}


//3.3
class Director(val firstName: String, val lastName: String, val yearOfBirth: Int){

  // String Interpolation
  def name = s"$firstName $lastName"
  //def copy( firstName: String = this.firstName, lastName: String = this.lastName, yearOfBirth: Int = this.yearOfBirth):
  //Director = new Director(firstName, lastName, yearOfBirth)
}

class Film(val name: String, val yearOfRelease: Int, val imdbRating: Double, val director: Director){
  def direcorsAge = yearOfRelease - director.yearOfBirth
  def isDirectedBy(d: Director) = if(director.name == d.name) true else false
  // This copys things without overwriting them
  def copy(
            name: String = this.name,
            yearOfRelease: Int = this.yearOfRelease,
            imdbRating: Double = this.imdbRating,
            director: Director = this.director): Film =
    new Film(name, yearOfRelease, imdbRating, director)
}
val eastwood          = new Director("Clint", "Eastwood", 1930)
val mcTiernan         = new Director("John", "McTiernan", 1951)
val nolan             = new Director("Christopher", "Nolan", 1970)
val someBody          = new Director("Just", "Some Body", 1990)

val memento           = new Film("Memento", 2000, 8.5, nolan)
val darkKnight        = new Film("Dark Knight", 2008, 9.0, nolan)
val inception         = new Film("Inception", 2010, 8.8, nolan)

val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7, eastwood)
val outlawJoseyWales  = new Film("The Outlaw Josey Wales", 1976, 7.9, eastwood)
val unforgiven        = new Film("Unforgiven", 1992, 8.3, eastwood)
val granTorino        = new Film("Gran Torino", 2008, 8.2, eastwood)
val invictus          = new Film("Invictus", 2009, 7.4, eastwood)

val predator          = new Film("Predator", 1987, 7.9, mcTiernan)
val dieHard           = new Film("Die Hard", 1988, 8.3, mcTiernan)
val huntForRedOctober = new Film("The Hunt for Red October", 1990, 7.6, mcTiernan)
val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8, mcTiernan)

eastwood.yearOfBirth
dieHard.director.name
invictus.isDirectedBy(nolan)

//highPlainsDrifter.copy(name = "L'homme des hautes plaines")


// 3.4 / 3.5 / 3.6

// Create a counter
// Since value fields are immutable we have to come up with a new way of tracking the count
// returning new counter objects lets us return new states without side effects of assignment
// this also allows chaining of methods as seen below

class Adder(amount: Int) {
  def add(in: Int) = in + amount
}

class Counter(var count: Int){
  def inc(input: Int = 1) = new Counter(count + input)
  def dec(input: Int = 1) = new Counter(count - input)
  def adjust(adder: Adder) = new Counter(adder.add(count))
}

// NOTE: If you give initilized values for methods you have to add () to the method call
//  Even if there is nothing inside of them
new Counter(10).inc().dec().inc().inc().count

//new Counter(10).adjust(2)

/*=============================================================================
   Section 3.2
 */

// The apply method in classes
class Adder2(amount: Int) {
  def apply(in: Int): Int = in + amount
}

// with the apply method we do not need to explicity call the method
val add3 = new Adder2(3)
add3.apply(2)
// any method with apply method does not need the method call
add3(4)


// 3.3 Companion Objects
// singleton objects are how scala handles static methods that are independent of any particular object
// Since scala does not allow multiple constructors for a class, in scala you can create objects with
// The same name as the class, called a companion object

class Timestamp(val seconds: Long = 0l)
object Timestamp {
  def apply(hours: Int, minutes: Int, seconds: Int): Timestamp =
    new Timestamp(hours*60*60 + minutes*60 + seconds)
}

// is timestamp.type
// this is the companion object
Timestamp

// is TimeStamp type
Timestamp(1,1,1)


/*=============================================================================
   Section 3.3
 */

// companion objects for Person class
// the person object can be used independantly of the class
class Person5(val firstName: String, val lastName: String){
  def name: String = s"$firstName, $lastName"
}

// singleton object
object Person5{
  def apply(name: String): Person5 = {
    val parts = name.split(" ")
    new Person5(parts(0), parts(1))
  }
}

// Case classes
// basically short hand for deining a class, and companion object
case class Person6(firstName: String, lastName: String){
  def name = firstName + " " + lastName
}
// creates from class
val dave2 = Person6("Dave", "Gurnell")
//Creates a companion object
Person6

/*=============================================================================
   3.5 Patterns in scala
 */

// using case class and patterns, if a Cat is defined use patterns to determine if the cats favorite food is chips
//case class Cat2( name: String, colour: String, favFood: String)
//object Chipshop{
//  def willServe(cat: Cat): Boolean =
//    cat match{
//      case Cat2(_,_,"chips") => true
//      case Cat2(_,_,_) => false
//    }
//}
//
//object Dad{
//  def rate(film: Film): Double =
//    film match{
//      case Film(_,_,_,Director("Clint", "Eastwood", _)) => 10.0
//      case Film(_,_,_,Director("John", "McTiernan", _)) => 7.0
//        // all other cases get a 3.0
//      case _ => 3.0
//    }
//}


