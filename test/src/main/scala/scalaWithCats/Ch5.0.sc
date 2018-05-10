// lets say we are looking up user data in a database,
// could be error, or Option[User] so we use an either

def lookupUserName(Id: Long): Either[Error, Option[String]] =
for{
  optUser <- luckupUser(id)

} yield {
  for{ user <- optUser} yield user.name
}

