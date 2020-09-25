package lectures.part2oop

import playground.{Cinderella => Princess, PrinceCharming}
//import playground._

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  // package members aer accessible by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 2018)

  // import the package
//  val princess = new Cinderella
  val princess = new Princess

  // package objects
  sayHello
  println(AGE)

  // imports
  val prince = new PrinceCharming

  // 1. use fully qualified names
  val date = new Date
  val d = new java.sql.Date(2002, 1, 1)

  // 2. alias
  val d2 = new SqlDate(2020, 1, 1)


}
