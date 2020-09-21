package lectures.part2oop

object InheritanceAndTraits extends App {

  // single class inheritance
  // public - access from anywhere
  // private - access only inside the class
  // protected - access only inside the class and subclasses
  sealed class Animal {
    val creatureType = "wild"

    def eat = println("nomnomnom")
  }

  class Cat extends Animal

  val cat = new Cat
  cat.eat

  //  constructor
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name) // or extends Person(name, age)


  // overriding
  class Dog(override val creatureType: String) extends Animal {

//    override val creatureType: String = "domestic"

    override def eat = {
      super.eat
      println("crunch crunch")
    }
  }

  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // type substitution (polymorphism)
  val unknowmAnimal: Animal = new Dog("K9")
  unknowmAnimal.eat

  // super

  // preventing override
  // 1 - final on member
  // 2 - final on class
  // 3 - seal the class = extend class in this file, prevent extension in other files
}
