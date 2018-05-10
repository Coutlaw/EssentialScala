val sequence = Seq(1,2,3)
sequence.map(elt => elt*2)
sequence.map(_ *2)

// get all permutations of a string
// returns a list, so if there is no .toList it doesnt work
"dog".permutations
// Proper way to get all permutations as a list
"dog".permutations.toList

// take a sequence of strings, get permuntations and return as a list
Seq("a", "wet", "dog").map(_.permutations.toList)

Seq(1,2,3).flatMap(num => Seq(num, num*10))

import scala.collection.immutable.Vector
Vector(1,2,3).flatMap(num => Seq(num, num*10))

// fold summations
//first starts at zero and adds things to 0
Seq(1,2,3).foldLeft(0)(_+_)
// this solution starts with the first value in the sequence
Seq(1,2,3).foldRight(0)(_+_)

// doesnt do anything usefull, mostly for printing elements
List(1, 2, 3).foreach(num => println("And a " + num + "..."))


// Examples
case class Film(
                 name: String,
                 yearOfRelease: Int,
                 imdbRating: Double)

case class Director(
                     firstName: String,
                     lastName: String,
                     yearOfBirth: Int,
                     films: Seq[Film])

val memento           = new Film("Memento", 2000, 8.5)
val darkKnight        = new Film("Dark Knight", 2008, 9.0)
val inception         = new Film("Inception", 2010, 8.8)

val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7)
val outlawJoseyWales  = new Film("The Outlaw Josey Wales", 1976, 7.9)
val unforgiven        = new Film("Unforgiven", 1992, 8.3)
val granTorino        = new Film("Gran Torino", 2008, 8.2)
val invictus          = new Film("Invictus", 2009, 7.4)

val predator          = new Film("Predator", 1987, 7.9)
val dieHard           = new Film("Die Hard", 1988, 8.3)
val huntForRedOctober = new Film("The Hunt for Red October", 1990, 7.6)
val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8)

val eastwood = new Director("Clint", "Eastwood", 1930,
  Seq(highPlainsDrifter, outlawJoseyWales, unforgiven, granTorino, invictus))

val mcTiernan = new Director("John", "McTiernan", 1951,
  Seq(predator, dieHard, huntForRedOctober, thomasCrownAffair))

val nolan = new Director("Christopher", "Nolan", 1970,
  Seq(memento, darkKnight, inception))

// get all films by nolan as a list
nolan.films.map(_.name)

directors.flatMap(director => director.films.map(film => film.name))