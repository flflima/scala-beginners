package lectures.part3fp

object WhatsAFunction extends App {

  // functions as first class elements
  val doubler = new MyFunction[Int, Int] {
    override def apply(value: Int): Int = value * 2
  }

  println(doubler(3))

  // function types = Function1[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("2") + 2)

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  println(adder(1, 9))

  // Function types Function2[A, B, R] ==== (A,B) => R

  /**
   * 1. Define a function which takes 2 strings and concatenates them
   * 2. Transform MyPredicate and MyTransformer into function types
   * 3. Define a function which takes an int and returns another function which takes an int and returns an int
   *    - what's the type of this function
   *    - how to do it
   */

  def concatenator: (String, String) => String = new Function2[String, String, String] {
    override def apply(first: String, second: String): String = first + "" + second
  }

  println(concatenator("Hello ", "world!"))

  // higher-order functions: either receive functions as parameters or return functions

//  def myFunction: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
//    override def apply(a: Int): Function1[Int, Int] = new Function[Int, Int] {
//      override def apply(b: Int): Int = a + b
//    }
//  }
//
//  def myFunction: Int => (Int => Int) = a => (b => a + b)
  def myFunction = (a: Int) => (b: Int) => a + b

  val firstFunction = myFunction(1)
  println(firstFunction(2))
  println(myFunction(1)(2)) // => curried function

}

trait MyFunction[A, B] {
  def apply(value: A): B
}
