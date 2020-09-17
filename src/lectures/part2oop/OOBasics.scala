package lectures.part2oop

object OOBasics extends App {

  val person = new Person("John", 23)
  println(person.age)
  person.greet("Mary")
  person.greet()
}

// class parameters are not fields
//class Person(name: String, age: Int) // constructor

class Person(name: String, val age: Int) {
  // body
  val x = 2 // field

  println(1 + 1)

  // method
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  // overloading
  def greet(): Unit = println(s"Hi, I am $name")

  // multiple constructors
  def this(name: String) = this(name, 0)
}
