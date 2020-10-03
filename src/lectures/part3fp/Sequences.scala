package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  // Seq (immutable)
  val aSequence = Seq(2, 1, 3, 4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5,6,7))
  println(aSequence.sorted)

  // Ranges (immutable)
  val aRange: Seq[Int] = 1 to 10 // 1 until 10 (non inclusive)
  aRange.foreach(println)

  (1 to 10).foreach(x => println("Hello"))

  // lists (immutable)
  val aList = List(1,2,3)
  val prepended = 42 :: aList // +: (prepend) or :+ (append)
  println(prepended)

  val apple5 = List.fill(5)("apple")
  println(apple5)
  println(aList.mkString("-|-"))

  // arrays (mutable)
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)
  println(threeElements)
  threeElements.foreach(println) // 0 is default for primitives, otherwise is null

  // mutation
  numbers(2) = 0 // syntax sugar numbers.update(2, 0)
  println(numbers.mkString(" "))

  // arrays and sequences
  val numberSeq: Seq[Int] = numbers // implicit conversion
  println(numberSeq)

  // vectors
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vectors vs list
  val maxRuns = 1000
  val maxCapacity = 100000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      // operation
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numberVector = (1 to maxCapacity).toVector

  println(getWriteTime(numbersList))
  println(getWriteTime(numberVector))

}
