package lectures.part2oop

object Generics extends App {

  class MyList[+A] { // [] generic type
    def add[B >: A](element: B): MyList[B] = ???
  }

  trait MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList { // objects can't be type parameterized
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // List[Cat] extends List[Animal] => COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]

  // animalList.add(new Dog) XX => INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal] // same type both sides

  // CONTRAVARIANCE
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal] // subtype = supertype

  // bounded types
  class Cage[A <: Animal] (animal: A) // only accepts types A that are subtypes of Animal
                                      // also has  >: symbol, which means tha A must be a supertype of Animal
  val cage = new Cage(new Dog)

//  class Car
//  val newCage = new Cage(new Car) // error

  /**
   * expand MyList from previous exercise to be generic
   */
}
