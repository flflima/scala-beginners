package lectures.part2oop

object CaseClasses extends App {

  /**
   * avoid boilerplate code
   *
   * equals, hashcode, toString
   */
  case class Person(name: String, age: Int)

  // 1. promote all class parameters are fields
  val jim = new Person("Jim", 3)
  println(jim.name)

  // 2. sensible toString
  println(jim.toString)

  // 3. equals and hashcode
  val jim2 = new Person("Jim", 3)
  println(jim == jim2)

  // 4. handy copy methods
  val jim3 = jim.copy(age = 10)
  println(jim3)

  // 5. have companion objects
  val thePerson = Person
  val mary = Person("Mary", 33)

  // 6. Serializable
  // Akka

  // 7. Extractor Patterns => pattern matching



  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }


}
