package lectures.part1basics

object Expressions extends App {

  val x = 1 + 2
  println(x)

  println(2 + 3 * 4)

  println(1 == x)

  println(!(1 == x))

  var aVariable = 2
  aVariable += 3
  println(aVariable)

  // Instructions vs Expressions

  val aCondition = true // instruction
  val aConditionValue = if (aCondition) 5 else 3 // IF expression
  println(aConditionValue)
  println(if (aCondition) 5 else 3)

  var i = 0
  while (i < 10) { // desencorajado
    println(i)
    i += 1
  }

  val aWeirdValue = (aVariable = 3) // side effects => expressions returning the type Unit
  println(aWeirdValue)

  // Code blocks

  val aCodeBlock = { // expression
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }

  //
  val someValue = {
    2 < 3
  }
  println(someValue)

  val someOtherValue = {
    if (someValue) 239 else 986
    42
  }
  println(someOtherValue)
}
