package lectures.part1basics

import scala.annotation.tailrec

object DefaultArgs extends App {

  @tailrec
  def factHelper(n: Int, accumulator: Int = 1): Int =
    if (n <= 1) accumulator
    else factHelper(n - 1, n * accumulator)

//  val fact10 = factHelper(10, 1)
  val fact10 = factHelper(10)

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("Saving picture")
//  savePicture(800) // compiler error
  savePicture("bmp")
  savePicture(width = 800)
  savePicture(height = 600, width = 800, format = "bmp")

}
