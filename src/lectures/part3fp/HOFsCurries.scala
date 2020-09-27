package lectures.part3fp

object HOFsCurries extends App {

//  val superFunction: (Int, (String, (Int => Int) => Int) => Int) => (Int, Int) = ???
  // it's a higher order function (receives or returns a function)

  // map, filter, flatMap => HOFs, because both of them receive a function as a parameter

  // function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x))
  // nTimes(f, n, x) = nTimes(f, n-1, f(x))
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n-1, f(x))
  // n == 1
  // nTimes(f, n-1, f(x))
  // nTimes(x + 1, 1-1, 1+1)
  // nTimes(x + 1, 0, 2) == 2

  val addOne = (x: Int) => x + 1

  println(nTimes(addOne, 10, 1))


  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n-1)(f(x))
  // n == 1
  // x == 1
  // nTimesBetter((x) => x + 1, 1)
  // nTimesBetter((x) => x + 1, 1 - 1)(x + 1)
  // nTimesBetter((x) => x + 1, 0)(x + 1)
  // nTimesBetter((x) => x + 1, 0)
  // f(x) => x applied to (x + 1)
  // f(x + 1) => x + 1
  // x == 1
  // f(1 + 1) => 1 + 1
  // f(2) => 2

  // n == 2
  // nTimesBetter((x) => x + 1, 2 - 1)(x + 1)
  // nTimesBetter((x) => x + 1, 1)(x + 1)
  // nTimesBetter((x) => x + 1, 1 - 1)(x + 1) applied to (x + 1)
  // nTimesBetter((x) => x + 1, 0)(x + 1) applied to  (x + 1)
  // f(x) => x applied to (x + 1) applied to  (x + 1)
  // f(x + 1) => x + 1 applied to  (x + 1)
  // f((x + 1) + 1) => (x + 1) + 1
  // x == 1
  // f((1 + 1) + 1) => (1 + 1) + 1
  // f(3) => 3

  // n == 3
  // nTimesBetter((x) => x + 1, 3)
  // nTimesBetter((x) => x + 1, 3 - 1)(x + 1)
  // nTimesBetter((x) => x + 1, 2)(x + 1)
  // nTimesBetter((x) => x + 1, 2 - 1)(x + 1) applied to (x + 1)
  // nTimesBetter((x) => x + 1, 1)(x + 1) applied to (x + 1)
  // nTimesBetter((x) => x + 1, 1 - 1)(x + 1) applied to (x + 1) applied to (x + 1)
  // nTimesBetter((x) => x + 1, 0)(x + 1) applied to (x + 1) applied to (x + 1)
  // f(x) => (x) applied to (x + 1) applied to (x + 1) applied to (x + 1)
  // f(x + 1) => x + 1 applied to (x + 1) applied to (x + 1)
  // f((x + 1) + 1) => (x + 1) + 1 applied to (x + 1)
  // f(((x + 1) + 1) + 1) => ((x + 1) + 1) + 1
  // x == 1
  // f(((1 + 1) + 1) + 1) => ((1 + 1) + 1) + 1
  // f(4) => 4

  val plus10 = nTimesBetter(addOne, 10)
  println(plus10(1))
  println(nTimesBetter(addOne, 3)(1))


  // curried functions
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) // y => 3 + y
  println(add3(10))
  println(superAdder(3)(10))


  // functions with multiple parameters
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: Double => String = curriedFormatter("%4.2f") // inform function return type
  val preciseFormat: Double => String = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))
}
