def numberOfVowels(str: String) =
  str.filter(Seq('a', 'e', 'i', 'o', 'u').contains(_)).length
numberOfVowels("the quick brown fox")

implicit class ExtraStringMethods(str: String){
  val vowels = Seq('a','e', 'i', 'o', 'u')
  def numberOfVowels =
    str.toList.filter(vowels contains _).length
}

new ExtraStringMethods("the quick brown fox").numberOfVowels

// when extra string methods is an implicit class
"the quick brown fox".numberOfVowels

// exercises
// make something that prints yeah n.yeah() for n times
implicit class IntOps(n: Int){
  def yeah() = for{ _ <- 0 until n } println("Oh yeah!")
  def times(func: Int => Unit) =
    for(i <- 0 until n) func(i)
}

2.yeah()
3.times(i => println(s"Look - it's the number $i!"))
