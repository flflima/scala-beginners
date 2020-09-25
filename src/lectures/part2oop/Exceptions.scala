package lectures.part2oop

object Exceptions extends App {

  val x: String = null
//  println(x.length) << crash with NPE

  // 1. Throwing exceptions
//  val aWeirdValue = throw new NullPointerException
  // Exception and Error are the major Throwable subtypes
  // throwable classes extend the Throwable class

  // 2. catching exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No Int!!!")
    else 42

  val potentialFail = try {
    // code that might fail
    getInt(false)
  } catch {
    case e: RuntimeException => println("caught a RuntimeException")
  } finally {
    // code that will be executed no matter what
    println("finally")
  }

  println(potentialFail)

  // 3. define own exceptions
  class MyException extends Exception
  val exception = new Exception

//  throw exception

  /**
   * 1. Crash your program with an OutOfMemoryError
   * 2. Crash with StackOverflowError
   * 3. PocketCalculator
   *      - add(x, y)
   *      - subtract(x, y)
   *      - multiply(x, y)
   *      - divide(x, Y)
   *
   *      throw
   *        - OverflowException if add(x,y) exceeds Int.MAX_VALUE
   *        - UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
   *        - MathCalculationException for division by 0
   */

//  val array = Array.ofDim(Int.MaxValue)

  def explodeException(n: Int): Int = n * explodeException(n + 1)
//  explodeException(1000)

  class OverflowException extends Exception
  class UnderflowException extends Exception
  class MathCalculationException extends Exception

  class PocketCalculator {
    def add(x: BigInt, y: BigInt): BigInt =
      if (x + y > Int.MaxValue) throw new OverflowException
      else x + y

    def subtract(x: BigInt, y: BigInt): BigInt =
      if (x - y < Int.MinValue) throw new UnderflowException
      else x - y

    def multiply(x: Int, y: Int): Int = x * y

    def divide(x: Int, y: Int): Int =
      if (y == 0) throw new MathCalculationException
      else x / y
  }

  val calculator = new PocketCalculator
//  println(calculator.add(Int.MaxValue, 1))
//  println(calculator.subtract(Int.MinValue, 1000))
  println(calculator.divide(1000, 0))
}
