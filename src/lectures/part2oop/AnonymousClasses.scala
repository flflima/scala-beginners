package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  // anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("blah blah blah")
  }
  /*
  equivalent with

  class AnonymousClasses$$anon$1 extends Animal {
    override def eat: Unit = println("blah blah blah")
  }
  val funnyAnimal: Animal = new AnonymousClasses$$anon$1
   */

  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println("Hi my name is Jim")
  }

  // anonymous classes works for abstract and non abstract classes and traits


  /**
   * 1. Generic trait MyPredicate[-T] with a method test(T) => Boolean - test if a value of type T passes a condition
   * 2. Generic trait MyTransformer[-A, B] with a method transform(A) => B
   * 3. MyList:
   *      - map(MyTransformer) => MyList
   *      - filter(MyPredicate) => MyList
   *      - flatMap(MyTransformer from A to MyList[B]) => MyList[B]
   *
   *      class EvenPredicate extends MyPredicate[Int]
   *      class StringToIntTransformer extends MyTranformer[String, Int]
   *
   *      [1,2,3].map(n * 2) = [2,4,6]
   *      [1,2,3,4].filter(n % 2)  = [2,4]
   *      [1,2,3].flatMap(n => [n,n+1]) => [1,2,2,3,3,4]
   */
}
