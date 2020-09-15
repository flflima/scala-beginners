package lectures.part1basics

object Functions extends App {

  //  def aFunction(a: String, b: Int): String =
  //    a + " " + b

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 3))

  def aParemeterlessFunction(): Int = 42

  println(aParemeterlessFunction())
  println(aParemeterlessFunction)

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction("hello", 3))

  // recursion instead of loops

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }

  /**
   * 1 - A greeting function (name, age)
   */
  def greeting(name: String, age: Int) =
    println("Hi, my name is " + name + " and I am " + age + " years old.")

  greeting("John", 10)

  /**
   * 2 - Factorial Function 1 * 2 * 3 * ... * n
   */
  def factorial(n: Int): Int =
    if (n == 1) 1
    else n * factorial(n - 1)
  println(factorial(3))
  println(factorial(5))
  println(factorial(10))

  /**
   * 3 - A Fibonacci function
   *  f(1) = 1
   *  f(2) = 1
   *  f(n) = f(n-1) + f(n-2)
   */
  def fibonacci(n: Int): Int =
    if (n == 1 || n == 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)

  // f(4)
  // f(3) + f(2)
  // f(2) + f(1) + 1
  // 1 + 1 + 1

  println(fibonacci(1))
  println(fibonacci(2))
  println(fibonacci(3))
  println(fibonacci(4))
  println(fibonacci(5))
  println(fibonacci(6))
  println(fibonacci(25))


  /**
   * 4 - Tests if a number is prime
   */
  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)

    isPrimeUntil(n / 2)
  }
  println(isPrime(37))
  println(isPrime(10))
  println(isPrime(12058))
  println(isPrime(37 * 5))

}
