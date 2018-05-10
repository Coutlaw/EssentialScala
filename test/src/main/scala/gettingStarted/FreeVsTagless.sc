//https://en.wikipedia.org/wiki/Expression_problem



//The `~>` is the _natural transformation_ from a type `F[_]` to `G[_]`.
//See [FunctionK](https://typelevel.org/cats/api/cats/arrow/FunctionK.html).

package free
import common._

sealed trait Algebra[A]
case class GenerateS3Key(id: PhotoId) extends Algebra[S3Key]
case class InsertDynamoRecord(id: PhotoId, s3key: S3Key, createdBy: String) extends Algebra[DynamoRecord]
case class GetDynamoRecord(id: PhotoId) extends Algebra[DynamoRecord]
case class WriteContentToS3(key: S3Key, content: Array[Byte]) extends Algebra[Unit]
case class ReadContentFromS3(key: S3Key) extends Algebra[Array[Byte]]


package free
import common._
import cats.free.Free

object DSL {

  type Program[A] = Free[Algebra, A]

  def generateS3Key(id: PhotoId): Program[S3Key] =
    Free.liftF(GenerateS3Key(id))

  def insertDynamoRecord(id: PhotoId, s3key: S3Key, createdBy: String): Program[DynamoRecord] =
    Free.liftF(InsertDynamoRecord(id, s3key, createdBy))

  def getDynamoRecord(id: PhotoId): Program[DynamoRecord] =
    Free.liftF(GetDynamoRecord(id))

  def writeContentToS3(key: S3Key, content: Array[Byte]): Program[Unit] =
    Free.liftF(WriteContentToS3(key, content))

  def readContentFromS3(key: S3Key): Program[Array[Byte]] =
    Free.liftF(ReadContentFromS3(key))

}





package free
import common._

object Programs {
  import DSL._

  def savePhoto(id: PhotoId, createdBy: String, content: Array[Byte]): Program[Unit] =
    for {
      s3key <- generateS3Key(id)
      _ <- insertDynamoRecord(id, s3key, createdBy)
      _ <- writeContentToS3(s3key, content)
    } yield ()

  def getPhoto(id: PhotoId): Program[Photo] =
    for {
      dynamoRecord <- getDynamoRecord(id)
      content <- readContentFromS3(dynamoRecord.s3key)
    } yield Photo(dynamoRecord.id, dynamoRecord.createdBy, content)

  def saveAndThenGetPhoto(id: PhotoId, createdBy: String, content: Array[Byte]): Program[Photo] =
    for {
      _ <- savePhoto(id, createdBy, content)
      photo <- getPhoto(id)
    } yield photo

}



package free

import common._
import cats.~>
import cats.data.OptionT
import cats.instances.future._
import scala.concurrent.ExecutionContext.Implicits.global

object Interpreters {
  val futureOfOptionInterpreter = new (Algebra ~> FutureOfOption) {
    override def apply[A](op: Algebra[A]): FutureOfOption[A] = op match {
      case GenerateS3Key(id) =>
        println("Generating S3 key")
        OptionT.pure(S3Key(s"photos/$id"))

      case InsertDynamoRecord(id, s3key, createdBy) =>
        println("Inserting Dynamo record")
        // TODO write it to Dynamo
        OptionT.pure(DynamoRecord(id, s3key, createdBy))

      case GetDynamoRecord(id) =>
        println("Getting Dynamo record")
        // TODO look it up in Dynamo
        OptionT.pure(DynamoRecord(id, S3Key("the S3 key"), "Chris"))

      case WriteContentToS3(key, content) =>
        println("Writing to S3")
        // TODO write it to S3
        OptionT.pure(())

      case ReadContentFromS3(key) =>
        println("Reading from S3")
        // TODO read it from S3
        OptionT.pure("yolo".getBytes)
    }
  }
}



package free

import common._
import scala.concurrent._
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import cats.instances.future._

object Example extends App {
  import Programs._

  val futureOfOption: FutureOfOption[Photo] =
    saveAndThenGetPhoto(PhotoId("abc"), "Chris", "yolo".getBytes)
      .foldMap(Interpreters.futureOfOptionInterpreter)

  println(Await.result(futureOfOption.value, atMost = Duration.Inf))

}

///////////////////////////////////////////////////////



package tagless
import common._
import scala.language.higherKinds

trait Algebra[F[_]] {
  def gene
  def insertDynamoRecord(id: PhotoId, s3key: S3Key, createdBy: String): F[DynamoRecord]
  def getDynamoRecord(id: PhotoId): F[DynamoRecord]
  def writeContentToS3(key: S3Key, content: Array[Byte]): F[Unit]
  def readContentFromS3(key: S3Key): F[Array[Byte]]
}



package tagless

import common._
import cats.Monad
import scala.language.higherKinds

//    notice the F[_]: Monad
class Programs[F[_]: Monad](alg: Algebra[F]) {
  import alg._
  import cats.syntax.functor._
  import cats.syntax.flatMap._

  def savePhoto(id: PhotoId, createdBy: String, content: Array[Byte]): F[Unit] =
    for {
      s3key <- generateS3Key(id)
      _ <- insertDynamoRecord(id, s3key, createdBy)
      _ <- writeContentToS3(s3key, content)
    } yield ()

  def getPhoto(id: PhotoId): F[Photo] =
    for {
      dynamoRecord <- getDynamoRecord(id)
      content <- readContentFromS3(dynamoRecord.s3key)
    } yield Photo(dynamoRecord.id, dynamoRecord.createdBy, content)

  def saveAndThenGetPhoto(id: PhotoId, createdBy: String, content: Array[Byte]): F[Photo] =
    for {
      _ <- savePhoto(id, createdBy, content)
      photo <- getPhoto(id)
    } yield photo
}


package tagless

import common._
import cats.data.OptionT
import cats.instances.future._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object FutureOfOptionInterpreter extends Algebra[FutureOfOption] {
  def generateS3Key(id: PhotoId): FutureOfOption[S3Key] = {
    println("Generating S3 key")
    OptionT.pure(S3Key(s"photos/$id"))
  }

  def insertDynamoRecord(id: PhotoId, s3key: S3Key, createdBy: String): FutureOfOption[DynamoRecord] = {
    println("Inserting Dynamo record")
    // TODO write it to Dynamo
    OptionT.pure(DynamoRecord(id, s3key, createdBy))
  }

  def getDynamoRecord(id: PhotoId): FutureOfOption[DynamoRecord] = {
    println("Getting Dynamo record")
    // TODO look it up in Dynamo
    OptionT.pure(DynamoRecord(id, S3Key("the S3 key"), "Chris"))
  }

  def writeContentToS3(key: S3Key, content: Array[Byte]): FutureOfOption[Unit] = {
    println("Writing to S3")
    // TODO write it to S3
    OptionT.pure(())
  }

  def readContentFromS3(key: S3Key): FutureOfOption[Array[Byte]] = {
    println("Reading from S3")
    // TODO read it from S3
    OptionT.pure("yolo".getBytes)
  }
}

package tagless

import common._
import scala.concurrent._
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import cats.instances.future._

object Example extends App {
  val prog = new Programs(FutureOfOptionInterpreter)

  val future: FutureOfOption[Photo] =
    prog.saveAndThenGetPhoto(PhotoId("abc"), "Chris", "yolo".getBytes)

  println(Await.result(future.value, atMost = Duration.Inf))
}



///////////


package tagless.modular

import common._
import cats.data.OptionT
import cats.instances.future._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object S3Interpreter extends S3Alg[FutureOfOption] {
  def generateS3Key(id: PhotoId): FutureOfOption[S3Key] = {
    println("Generating S3 key")
    OptionT.pure(S3Key(s"photos/$id"))
  }

  def writeContentToS3(key: S3Key, content: Array[Byte]): FutureOfOption[Unit] = {
    println("Writing to S3")
    // TODO write it to S3
    OptionT.pure(())
  }

  def readContentFromS3(key: S3Key): FutureOfOption[Array[Byte]] = {
    println("Reading from S3")
    // TODO read it from S3
    OptionT.pure("yolo".getBytes)
  }
}

object DynamoInterpreter extends DynamoAlg[FutureOfOption] {
  def insertDynamoRecord(id: PhotoId, s3key: S3Key, createdBy: String): FutureOfOption[DynamoRecord] = {
    println("Inserting Dynamo record")
    // TODO write it to Dynamo
    OptionT.pure(DynamoRecord(id, s3key, createdBy))
  }

  def getDynamoRecord(id: PhotoId): FutureOfOption[DynamoRecord] = {
    println("Getting Dynamo record")
    // TODO look it up in Dynamo
    OptionT.pure(DynamoRecord(id, S3Key("the S3 key"), "Chris"))
  }
}


/// Execute the program using an interpreter.


package tagless.modular

import common._
import scala.concurrent._
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import cats.instances.future._

object Example extends App {
  val prog = new Programs(S3Interpreter, DynamoInterpreter)
  val future: FutureOfOption[Photo] =
    prog.saveAndThenGetPhoto(PhotoId("abc"), "Chris", "yolo".getBytes)

  println(Await.result(future.value, atMost = Duration.Inf))
}


//https://skillsmatter.com/skillscasts/10007-free-vs-tagless-final-with-chris-birchall


