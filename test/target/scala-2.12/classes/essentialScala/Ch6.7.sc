val example = Map("a" -> 1, "b" -> 2, "c" -> 3)
// maping a map
example.map(pair => pair._1 -> pair._2 *2)

// SETS
val people = Set(
  "Alice",
  "Bob",
  "Charlie",
  "Derek",
  "Edith",
  "Fred")

val ages = Map(
  "Alice"   -> 20,
  "Bob"     -> 30,
  "Charlie" -> 50,
  "Derek"   -> 40,
  "Edith"   -> 10,
  "Fred"    -> 60)

val favoriteColors = Map(
  "Bob"     -> "green",
  "Derek"   -> "magenta",
  "Fred"    -> "yellow")

val favoriteLolcats = Map(
  "Alice"   -> "Long Cat",
  "Charlie" -> "Ceiling Cat",
  "Edith"   -> "Cloud Cat")

def favoriteColor(person: String): String =
  favoriteColors.getOrElse(person, "beige")

def printColors() = people foreach {
  person => println(s"$person's favorite color is ${favoriteColor(person)}!")
}


// Union of sets
def union[A](set1: Set[A], set2: Set[A]): Set[A] = {
  set1.foldLeft(set2){ (set, elt) => (set + elt) }
}

// Ranges
1 until 10
1 until 10 by -1

//for(i <- 99 until 0 by -1) println(i + " bottles of beer on the wall!")

