package lectures.part3fp

object MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair= (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  /**
   * Print all combinations between two lists
   * List("a1", "a2", "a3" ... "d4")
   */
  val numbers = List(1,2,3,4)
  val chars = List('a','b','c','d')

  chars.foreach(c => {
    numbers.foreach(n => {
      println(c + "" + n)
    })
  })

  // CORRECT SOLUTION FOR FUNCTIONAL PROGRAMMING
  // "iterations"
  val combinations = numbers.flatMap(n => chars.map(c => c + "" + n))
  println(combinations)


  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- numbers
    c <- chars
  } yield "" + c + n
  println(forCombinations)

  // syntax overload
  list.map { x =>
    x * 2
  }
}
