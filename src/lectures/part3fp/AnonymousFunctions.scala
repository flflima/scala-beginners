package lectures.part3fp

object AnonymousFunctions extends App {

  // anonymous function (LAMBDA)
  val doubler: Int => Int = x => x * 2

  // multiple params in a lambda
//  val adder = (a: Int, b: Int) => a + b
  val adder: (Int, Int) => Int = (a, b) => a + b

  // no params
//  val justDoSomething = () => 3
  val justDoSomething: () => Int = () => 3

  println(justDoSomething) // prints a function
  println(justDoSomething()) // prints the function's return

  // curly braces
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1 // it's important to have the parameter type
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b // underscore per parameter

  /**
   * 1. MyList: replace all Function calls with lambdas
   * 2. Rewrite the adder (firstFunction) as an anonymous function
   */
}
