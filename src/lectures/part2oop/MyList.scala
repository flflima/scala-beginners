package lectures.part2oop

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

  def map[B](transformer: MyTransformer[A, B]): MyList[B]

  def filter(predicate: MyPredicate[A]): MyList[A]

  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]

  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object EmptyList extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](item: B): MyList[B] = Cons(item, EmptyList)

  def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = EmptyList

  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = EmptyList

  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = EmptyList

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](item: B): MyList[B] = Cons(item, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  def map[B](transformer: MyTransformer[A, B]): MyList[B] = Cons(transformer.transform(h), t.map(transformer))

  def filter(predicate: MyPredicate[A]): MyList[A] =
    if (predicate.test(h)) Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)

  def ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)
}

trait MyPredicate[-T] {
  def test(value: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(value: A): B
}

/*

class EvenPredicate extends MyPredicate[Int] {
  override def test(value: Int): Boolean = value % 2 == 0
}

class StringToIntTransformer extends MyTransformer[String, Int] {
  override def transform(value: String): Int = value.toInt
}*/

object ListTest extends App {
  val list: MyList[Int] = Cons(1, Cons(2, Cons(3, EmptyList)))
  val cloneList: MyList[Int] = Cons(1, Cons(2, Cons(3, EmptyList)))
  println(list.tail.head)
  println(list.add(4).head)
  println(list.isEmpty)
  println(list.toString)

  val listOfStrings: MyList[String] = Cons("Hello", EmptyList)
  println(listOfStrings.toString)

  /**
   * [1, 2, 3].map(n * 2)
   *
   */
  println(list.map(new MyTransformer[Int, Int] {
    override def transform(value: Int): Int = value * 2
  }))

  /**
   *
   */
  println(list.filter(new MyPredicate[Int] {
    override def test(value: Int): Boolean = value % 2 == 0
  }))

  /**
   *
   */
  println(list.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(value: Int): MyList[Int] =
      Cons(value, Cons(value + 1, EmptyList))
  }))

  println(cloneList == list)
}
