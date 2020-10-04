package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {

  // switch on steroids
  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "the One"
    case 2 => "double or nothing"
    case 3 => "third time is charming"
    case _ => "something else" // case _ wildcard
  }

  println(x)
  println(description)

  // 1. Decompose values
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I can't drink in the US!"
    case Person(n, a) => s"Hi, my name is $n and I'm $a years old!"
    case _ => "I don't know who I am!"
  }

  println(greeting)

  /*
  1. cases are matched in order
  2. if a case is not found an exception is thrown - MatchError
  3. the return type of match is a common type of the union of all returns
  4. Works really well with sealed
   */

  // 2. sealed hierarchy
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = new Dog("Terra Nova")
  animal match {
    case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")
  }

  /**
   * simple function use PM
   *
   * takes an Expr => human readable
   *
   * Sum(Number(2), Number(3)) => 2 + 3
   * Sum(Number(2), Number(3), Number(4)) => 2 + 3 +4
   * Prod(Sum(Number(2), Number(1)), Number(3)) = (2 + 1) * 3
   * Sum(Prod(Number(2), Number(1)), Number(3)) = 2 * 1 + 3
   */
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def readableExpression(expr: Expr): String = {
    expr match {
      case Number(n) => s"$n"
      case Sum(e1, e2) => readableExpression(e1) + " + " + readableExpression(e2)
      case Prod(e1, e2) => {
        def showInParenthesis(exp: Expr): String = exp match {
          case Number(_) => readableExpression(exp)
          case Prod(_, _) => readableExpression(exp)
          case _ => "(" + readableExpression(exp) + ")"
        }
        showInParenthesis(e1) + " * " + showInParenthesis(e2)
      }
    }
  }

  println(readableExpression(Sum(Number(2), Number(3))))
  println(readableExpression(Sum(Sum(Number(2), Number(3)), Number(4))))
  println(readableExpression(Prod(Sum(Number(2), Number(1)), Number(3))))
  println(readableExpression(Sum(Prod(Number(2), Number(1)), Number(3))))
}
