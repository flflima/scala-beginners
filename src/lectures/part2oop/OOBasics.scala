package lectures.part2oop

object OOBasics extends App {

  val person = new Person("John", 23)
  println(person.age)
  person.greet("Mary")
  person.greet()

  val author = new Writer("Charles", "Dickens", 1812)
  val impostor = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)

  println(novel.authorAge())
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(impostor))

  val counter = new Counter(10)
  println(counter.increment.value)

  val otherCounter = new OtherCounter(10)
  println(otherCounter.dec(5).count)
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

/**
 * Novel and a writer
 *
 * Writer: first name, surname, year
 * - method fullname = first name + surname
 *
 * Novel: name, year of release, author
 * - authorAge = age of the author at the year release
 * - isWrittenBy(author)
 * - copy(new year of release) = new instance of novel
 */
class Writer(val firstName: String, val surname: String, val year: Int) {

  def fullName(): String = s"$firstName $surname"
}

class Novel(val name: String, val year: Int, val author: Writer) {

  def authorAge(): Int = year - author.year

  def isWrittenBy(author: Writer): Boolean = author == this.author

  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

/**
 * Counter class:
 *  - receives an int value
 *  - method current count
 *  - method to increment/decrement => new Counter
 *  - overload inc/dec to receive an amount => new Counter
 *
 */
class Counter(val value: Int) {

  def increment = new Counter(value + 1) // immutability

  def decrement = new Counter(value - 1)

  def increment(amount: Int) = new Counter(value + amount)

  def decrement(amount: Int) = new Counter(value - amount)
}

class OtherCounter(val count: Int) {

  def inc = {
    println("Incrementing")
    new OtherCounter(count + 1)
  }

  def dec = {
    println("Decrementing")
    new OtherCounter(count - 1)
  }

  def inc(n: Int): OtherCounter = {
    if (n <= 0) this
    else inc.inc(n - 1)
  }

  def dec(n: Int): OtherCounter = {
    if (n <= 0) this
    else dec.dec(n - 1)
  }
}