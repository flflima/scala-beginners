package lectures.part1basics

object CBNvsCBV extends App {

  def callByValue (x: Long): Unit = {
    println("By value " + x)
    println("By value " + x)
  }

  def callByName (x: => Long): Unit = {
    println("By name " + x)
    println("By name " + x)
  }

  callByValue(System.nanoTime())
  callByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def printSomething(x: Int, y: => Int, z: Int) = print(x)

//  printSomething(infinite(), 34, 35) // stackoverflow
  printSomething(34, infinite(), 35) // sem stackoverflow, valor (=>) não é avaliado nunca logo não dá erro (delay evaluation)
//  printSomething(34, 35, infinite()) // stackoverflow
}
