package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int =
    if (n == 1) 1
    else {
      println("Computing factorial of " + n + " - I first need factorial of " + (n - 1))
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)

      result
    }

  //  println(factorial(10))
  //  println(factorial(5000))

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator) // tail recursion

    factHelper(n, 1)
  }

  println(anotherFactorial(10))
  println(anotherFactorial(5000))

  // ** para loops usar o tail recursion

  /**
   * 1. Concatenate a string n times
   */
  @tailrec
  def aRepeatedFunction(aString: String, aStringAccumulator: String, n: Int): String =
    if (n == 1) aStringAccumulator
    else aRepeatedFunction(aString, aString + aStringAccumulator, n - 1)

  println(aRepeatedFunction("hello", "", 3))

  /**
   * 2. isPrime tail recursive
   */
  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int, isPrime: Boolean): Boolean =
      if (!isPrime) false
      else if (t <= 1) true
      else isPrimeUntil(t - 1, n % t != 0 && isPrime)

    isPrimeUntil(n / 2, true)
  }

  println(isPrime(37))
  println(isPrime(10))
  println(isPrime(12058))
  println(isPrime(37 * 5))

  /**
   * 3. Fibonacci tail recursive
   */
  def fibonacci(n: Int): BigInt = {
    @tailrec
    def fibonacciHelper(x: Int, last: BigInt, nextToLast: BigInt): BigInt =
      if (x >= n) last
      else fibonacciHelper(x + 1, last + nextToLast, last)

    if (n <= 2) 1
    else fibonacciHelper(2, 1, 1)
  }

  println(fibonacci(1))
  println(fibonacci(2))
  println(fibonacci(3))
  println(fibonacci(4))
  println(fibonacci(5))
  println(fibonacci(8))
  println(fibonacci(10))
  println(fibonacci(50))
}
