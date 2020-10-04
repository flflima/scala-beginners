package lectures.part3fp

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)
  println(noOption)

  // unsafe APIs
  def unsafeMethod(): String = null

  //  val result = Some(null) //WRONG
  val result = Option(unsafeMethod())
  println(result)

  // chained methods
  def backupMethod(): String = "a valid result"

  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // design unsafe APIs
  def betterUnsafeMethod(): Option[String] = None

  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for-comprehensions
  val config: Map[String, String] = Map(
    "host" -> "192.0.0.1",
    "port" -> null
  )

  class Connection {
    def connect = "Connected"
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  /**
   * Try to establish a connection, if so print the connect method
   */
//  val myConnectionOption = Connection(Option(config("host")).orElse(Option("")).get, Option(config("port")).orElse
//  (Option("")).get)
//
//  if (myConnectionOption.isDefined)  println(myConnectionOption.get.connect)

  val host = config.get("host")
  val port = config.get("port")

  /**
   * if (host != null)
   *    if (port != null)
   *        return Connection(host, port)
   * return null
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))

  /**
   * if (c != null)
   *    return c.connect
   * return null
   */
  val connectionStatus = connection.map(c => c.connect)

  println(connectionStatus)
  connectionStatus.foreach(println)

  // OR

  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // OR

  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)
}