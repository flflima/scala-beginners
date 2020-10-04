package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("A Failure"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod: String = throw new RuntimeException("Another Failure")

  // Try object via the apply method
  val potentialFailure = Try(unsafeMethod)
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }

  // utilities
  println(potentialFailure.isSuccess)

  // or else
  def backupMethod: String = "A valid result"
  val fallbackTry = Try(unsafeMethod).orElse(Try(backupMethod))
  println(fallbackTry)

  // when designing an API
  def betterUnsafeMethod: Try[String] = Failure(new RuntimeException)
  def betterBackupMethod: Try[String] = Success("A valid result")
  val betterFallbackTry = betterUnsafeMethod orElse betterBackupMethod
  println(betterFallbackTry)

  // map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))
// for-comprehensions

  /**
   *
   */
  val hostname = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html> ... </html>"
      else throw new RuntimeException("Connection Interrupted")
    }
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
    }
  }

  /**
   * if you get the HTML page from the connection, print it to the console i.e. call renderHTML
   */
  val connection = Try(HttpService.getConnection(hostname, port))
  val html = connection.map(c => c.get(""))
  html.foreach(renderHTML)

  //

  Try(HttpService.getConnection(hostname, port))
    .map(c => c.get(""))
    .foreach(renderHTML)

  //
  for {
    c <- Try(HttpService.getConnection(hostname, port))
    html <- Try(c.get(""))
  } yield renderHTML(html)
}
