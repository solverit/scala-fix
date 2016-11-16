package scalafix

import java.io.InputStream
import java.util.UUID

import org.scalatest.concurrent.PatienceConfiguration.Timeout
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.exceptions.TestFailedException

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}
import scala.io.Source
import scala.util.Try

/**
  * Base class for all test
  *
  * @author solverit
  */
class TestSpec extends FlatSpec with Matchers with ScalaFutures with TryValues with OptionValues {
  implicit val timeout: Timeout = Timeout(1.minute)
  implicit val ec: ExecutionContext = ExecutionContext.Implicits.global

  implicit val pc: PatienceConfig = PatienceConfig(timeout = 1.minute)

  def randomId: String = UUID.randomUUID.toString
  val id: String = randomId

  def withInputStream(fileName: String)(f: InputStream ⇒ Unit): Unit = {
    val is = fromClasspathAsStream(fileName)
    try { f(is) } finally { Try(is.close()) }
  }

  implicit class FutureToTry[T](f: Future[T]) {
    def toTry: Try[T] = Try(f.futureValue)
  }

  implicit class MustBeWord[T](self: T) {
    def mustBe(pf: PartialFunction[T, Unit]): Unit =
      if (!pf.isDefinedAt(self)) throw new TestFailedException("Unexpected: " + self, 0)
  }

  def streamToString(is: InputStream): String =
    Source.fromInputStream(is).mkString

  def fromClasspathAsString(fileName: String): String =
    streamToString(fromClasspathAsStream(fileName))

  def fromClasspathAsStream(fileName: String): InputStream =
    getClass.getClassLoader.getResourceAsStream(fileName)
}
