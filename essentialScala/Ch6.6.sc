// Maps
// Question

val example = Map("a" -> 1, "b" -> 2, "c" -> 3)
example("a")
example.get("a")
// throws an exeption vs .get which returns an option
// example("d")
example.get("d")

// profides context for the option
example.getOrElse("d", -1)

example.contains("a")
example.size

example.+("c" -> 10, "d" -> 11, "e" -> 12)
example.-("b","c")

example + ("d" -> 4) - "c"

// Mutable maps

val example2 = scala.collection.mutable.Map("x" -> 10, "y" -> 11, "z" -> 12)
example2 += ("x" -> 20)

example2 -= ("y", "z")

//sugared syntax for update
example2("w") = 30

// Sorted maps
// if order of operands matters
scala.collection.immutable.ListMap("a" -> 1) + ("b" -> 2) + ("c" -> 3) + ("d" -> 4) + ("e" -> 5)

