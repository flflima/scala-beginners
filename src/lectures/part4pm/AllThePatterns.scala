package lectures.part4pm

import lectures.part2oop.{Cons, EmptyList, MyList}

object AllThePatterns extends App {

  // 1 - Constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "THE Scala"
    case true => "The truth"
    case AllThePatterns => "A singleton object"
  }

  // 2 - Matching anything
  // 2.1 wildcard
  val matchAnything = x match {
    case _ =>
  }

  // 2.2 variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  // 3 - Tuples
  val aTuple = (1,2)
  val matchATuple = aTuple match {
    case (1,1) =>
    case(something, 2) => s"I've found $something"
  }

  val nestedTuple = (1, (1, 3))
  val matchANestedTuple = nestedTuple match {
    case(_, (2, v)) =>
  }

  // 4 - case classes - constructor pattern
  val aList: MyList[Int] = Cons(1, Cons(2, EmptyList))
  val matchAList = aList match {
    case EmptyList =>
    case Cons(head, Cons(subhead, subtail)) =>
  }

  // 5 - list patterns
  val aStandardList = List(1,2,3)
  val standardListMatchinf = aStandardList match {
    case List(1, _, _) => // extractor for list
    case List(1, _*) => // list of arbitrary length
    case 1 :: List(_) => // infix pattern
    case List(1,2) :+ 3 => // infix pattern
  }

  // 6 - Type specifier
  val unknown: Any = 22
  val unknownMatch = unknown match {
    case list: List[Int] => // explicit type specifier
    case _ =>
  }

  // 7 - name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_, _) => // name binding => use the name later in here
    case Cons(1, rest @ Cons(2, _)) => // name binding inside nested patterns
  }

  // 8 - multi-patterns
  val multipattern = aList match {
    case EmptyList | Cons(0, _) => // compound pattern
  }

  // 9 - if guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>
  }



}
