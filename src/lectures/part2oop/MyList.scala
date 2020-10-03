package lectures.part2oop

import lectures.part2oop.Generics.MyList

abstract class MyList[+A] {
  /**
   * linked list of integers
   * method head = first element of the list
   * tail = remainder of the list
   * isEmpty = is this list empty
   * add(int) = new list with this element added
   * toString = a string representation of the list
   */
  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](item: B): MyList[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: A => B): MyList[B]

  def filter(predicate: A => Boolean): MyList[A]

  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def ++[B >: A](list: MyList[B]): MyList[B]

  def foreach(f: A => Unit)
  def sort(f: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]
  def fold[B](start: B)(operator: (B, A) => B): B
}

case object EmptyList extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](item: B): MyList[B] = Cons(item, EmptyList)

  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = EmptyList

  def filter(predicate: Nothing => Boolean): MyList[Nothing] = EmptyList

  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = EmptyList

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  def foreach(f: Nothing => Unit): Unit = ()
  def sort(f: (Nothing, Nothing) => Int): MyList[Nothing] = EmptyList
  def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] = {
    if (!list.isEmpty) throw new RuntimeException("Lists does not have the same length")
    EmptyList
  }
  def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](item: B): MyList[B] = Cons(item, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  def map[B](transformer: A => B): MyList[B] = Cons(transformer(h), t.map(transformer))

  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  def ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)

  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(f: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty) Cons(x, EmptyList)
      else if (f(x, sortedList.head) <= 0) Cons(x, sortedList)
      else Cons(sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(f)
    insert(h, sortedTail)
  }

  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] = {
    if (list.isEmpty) throw new RuntimeException("Lists does not have the same length")
    else Cons(zip(h, list.head), t.zipWith(list.tail, zip))
  }

  def fold[B](start: B)(operator: (B, A) => B): B = t.fold(operator(start, h))(operator)
}
//
//trait MyPredicate[-T] {
//  def test(value: T): Boolean
//}
//
//trait MyTransformer[-A, B] {
//  def transform(value: A): B
//}

/*

class EvenPredicate extends MyPredicate[Int] {
  override def test(value: Int): Boolean = value % 2 == 0
}

class StringToIntTransformer extends MyTransformer[String, Int] {
  override def transform(value: String): Int = value.toInt
}*/

object ListTest extends App {
  val list: MyList[Int] = Cons(1, Cons(2, EmptyList))
  val cloneList: MyList[Int] = Cons(1, Cons(2, Cons(3, EmptyList)))
  println(list.tail.head)
  println(list.add(4).head)
  println(list.isEmpty)
  println(list.toString)

  val listOfStrings: MyList[String] = Cons("Hello", Cons("scala", EmptyList))
  println(listOfStrings.toString)

  /**
   * [1, 2, 3].map(n * 2)
   *
   */
  println(list.map(value => value * 2))

  /**
   *
   */
  println(list.filter(value => value % 2 == 0))

  /**
   *
   */
  println(list.flatMap(value => Cons(value, Cons(value + 1, EmptyList))))

  println(cloneList == list)

  println("-------------------------------------------")
  list.foreach(x => println(x))
  println(list.sort((x, y) => y - x))
  println(list.zipWith[String, String](listOfStrings, _ + "-" + _))
  println(list.fold(0)(_ + _))
  println("-------------------------------------------")

  val data = for {
    i <- list
  } yield "=> " + i
  println(data)
}