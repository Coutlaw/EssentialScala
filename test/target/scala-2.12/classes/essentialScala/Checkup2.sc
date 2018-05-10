//http://blog.tmorris.net/posts/scalaoption-cheat-sheet/

for {
  a <- getFirstNumber   // getFirstNumber  returns Future[Int]
  b <- getSecondNumber  // getSecondNumber returns Future[Int]
} yield a + b


sbt -sbt-version 1.1.1 new http4s/http4s.g8
//https://http4s.org/v0.18/
//http://www.foundweekends.org/giter8/
//https://github.com/foundweekends/giter8/wiki/giter8-templates
