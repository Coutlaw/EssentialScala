// Eager, Lazy, Memorized functions
// Eager - happen immediatly
// Lazy - happen on access
// Memorized - first access

// a value is eager and memorized
val x = {
  println("computing x value")
  math.random()
}

// memorized call, it remembers what we assigned x to
x   // first access

x   // second access

// a def is lazy and not memorized
def y = {
  println("Computing Y")
  math.random()
}

// y is defined every time it is called, therefore it is lazy
y   // first access

y   // second access

// lazy and memorized
lazy val z = {
  println("computing z")
  math.random
}

// each time, the z is called and is remembered from the intial value
z

z