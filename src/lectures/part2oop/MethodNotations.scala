package lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {

    def likes(movie: String): Boolean = movie == favoriteMovie

    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def unary_! : String = s"This is not $name!!!!"

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

    def +(nickname: String): Person = new Person(s"${this.name} ($nickname)", favoriteMovie)

    def unary_+ : Person = new Person(this.name, this.favoriteMovie, age + 1)

    def learns(subject: String): String = s"$name learns $subject"

    def learnScala: String = this learns "Scala"

    def apply(amount: Int): String = s"$name watched $favoriteMovie $amount times"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // equivalent
  // infix notation == operator notation (only works with methods which have only one parameter)

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom) // hangOutWith acts like an operator
  println(mary + tom) // this is valid

  println(1 + 2)
  println(1.+(2))

  // all operators are methods

  // prefix notation
  val x = -1
  val y = 1.unary_-
  // unary_ pprefix only for - + ~ !

  println(!mary)
  println(mary.unary_!)

  // postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary()) // equivalent

  /**
   * 1. Overload the + operator which receives a String and returns a Person with a nickname
   *    mary + "the rockstar" => new Person("Mary (the rockstar)", "Inception")
   */
  println((mary + "the rockstar")())

  /**
   * 2. Add an age to the Person class
   *    Add a unary + operator => new Person with age + 1
   *    +mary => mary with the age incrementer
   */
  println((+mary).age)

  /**
   * 3. Add a "learns" method in the Person class => "Mary learns Scala"
   *    Add a learnScala method that calls learns method with "Scala"
   *    Use it in postfix notation
   */
  println(mary.learnScala)

  /**
   * 4. Overload the apply method
   *    mary.apply(2) => "Mary watched Inception 2 times"
   */
  println(mary(2))


}
