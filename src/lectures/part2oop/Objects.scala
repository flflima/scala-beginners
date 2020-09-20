package lectures.part2oop

object Objects extends App{

  // scala does not have class level functionality ("static")
  object Person {
    // class-level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }
  // it's a practice to have an object and a class in the same scope
  class Person(val name: String) {
    // instance-level functionality
  }
  // COMPANIONS == classes and objects with the same name and the same scope

  println(Person.N_EYES)
  println(Person.canFly)

  // scala object == singleton
  val mary = Person
  val john = Person
  println(mary == john)

  val person1 = new Person("Mary")
  val person2 = new Person("John")
  println(person1 != person2)

  val bobbie = Person(person1, person2)

  // Scala Applications = Scala object with:
  // def main(args: Array[String]): Unit
  // OR
  // extends App
}
