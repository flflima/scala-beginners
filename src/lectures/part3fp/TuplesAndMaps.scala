package lectures.part3fp

object TuplesAndMaps extends App {

  // tuples = finite ordered lists
  val aTuple = /*/*new */Tuple2*/(2, "hello Scala") // Tuple2[Int, String] = {Int, String}

  println(aTuple._1)
  println(aTuple._2)
  println(aTuple.copy(_2 = "goodbye"))
  println(aTuple.swap)
  println(aTuple.toString)

  // maps - key => value
  val aMap: Map[String, Int] = Map()

  val phoneBook = Map(("Jim", 333), "Daniel" -> 444).withDefaultValue(-1)
  // a -> b is sugar for (a, b)
  println(phoneBook)

  // maps ops
  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))
  println(phoneBook("Mary")) // throws NoSuchElementException

  // add a pairing
  val newPairing = "Mary" -> 1234
  val newPhoneBook = phoneBook + newPairing
  println(newPhoneBook)

  // functional on maps
  // map, flatMap, filter

  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phoneBook.filterKeys(x => x.startsWith("J")))

  // mapValues
  println(phoneBook.mapValues(number => number * 10))

  // conversions to other collections
  println(phoneBook.toList)
  println(List(("Daniel" -> 555)).toMap)

  val names = List("Bob", "James", "Mary", "Angela", "Pam", "Jim")
  println(names.groupBy(name => name.charAt(0)))

}
