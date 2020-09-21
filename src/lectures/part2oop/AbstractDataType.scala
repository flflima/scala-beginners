package lectures.part2oop

object AbstractDataType extends App {

  // abstract
  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"

    /*override*/ def eat: Unit = println("crunch crunch") // not mandatory to "override"

  }

  // traits
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    val creatureType: String = "crocodile"
    def eat: Unit = println("nomnomnom")
    override def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  // traits vs abstract classes
  // 1 - traits does not have constructor parameters
  // 2 - multiple traits may be inherited by the same class
  // 3 - traits = behaviours / abstract = "thing"

}
