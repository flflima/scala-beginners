package lectures.part2oop

abstract class MyList {
  /**
   * linked list of integers
   * method head = first element of the list
   *        tail = remainder of the list
   *        isEmpty = is this list empty
   *        add(int) = new list with this element added
   *        toString = a string representation of the list
   */
  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(item: Int): MyList
//  def toString(): String
}

object EmptyList extends MyList {
  def head: Int = throw new NoSuchElementException
  def tail: MyList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(item: Int): MyList = new Cons(item, EmptyList)
}

class Cons(h: Int, t: MyList) extends MyList {
  def head: Int = h
  def tail: MyList = t
  def isEmpty: Boolean = false
  def add(item: Int): MyList = new Cons(item, this)
}

object ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, EmptyList)))
  println(list.tail.head)
  println(list.add(4).head)
}
