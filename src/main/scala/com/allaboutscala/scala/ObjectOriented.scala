package com.allaboutscala.scala

/**
  * Created by ArnoldHigyed on 15/11/2018
  */
object ObjectOriented extends App {

  println("\n//-----------------------------------------------------------------------------------------------------\n")

//  println("Step 2: How to create instances of Donut class")
//  val glazedDonut = new Donut("Glazed Donut", 1111)
//  val vanillaDonut = new Donut("Vanilla Donut", 2222)
//  println("\nStep 3: How to call the print function for each of the donut object")
//  glazedDonut.print
//  vanillaDonut.print
//  println("\nStep 4: How to access the properties of class Donut")
//  glazedDonut.name
//  glazedDonut.productCode

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // TODO companion classes
//  println("\nStep 2: How to declare a companion object for the Donut class")
//  println("\nStep 3: How to create instances of the Donut class using the companion object")
//  val glazedDonut1 = Donut("Glazed Donut", 1111)
//  val vanillaDonut1 = Donut("Vanilla Donut", 2222)
//
//  println("\nStep 4: How to call function on each Donut object")
//  glazedDonut1.print
//  vanillaDonut1.print
//
//  object Donut {
//
//    def apply(name: String, productCode: Long): Donut = {
//      new Donut(name, productCode)
//    }
//  }
//
//  // "Step 1: How to define a simple class to represent a Donut object")
//  class Donut(name: String, productCode: Long) {
//
//    def print = println(s"Donut name = $name, productCode = $productCode")
//
//  }

  println("\n//-----------------------------------------------------------------------------------------------------\n")

//  println("\nStep 2: How to declare class hierarchy through inheritance using extends keyword")
//  println("\nStep 3: How to declare apply method of companion object as a factory")
//  println("\nStep 4: How to call apply method of companion object which is a factory")
//  val glazedDonut = Donut("Glazed Donut")
//  println(s"The class type of glazedDonut = ${glazedDonut.getClass}")
//  glazedDonut.print
//
//  val vanillaDonut = Donut("Vanilla Donut")
//  println(s"The class type of vanillaDonut = ${vanillaDonut.getClass}")
//  vanillaDonut.print
//
//  class Donut(name: String, productCode: Option[Long] = None){
//
//    def print = println(s"Donut name = $name, productCode = ${productCode.getOrElse(0)}")
//
//  }
//
//  class GlazedDonut(name: String) extends Donut(name)
//
//  class VanillaDonut(name: String) extends Donut(name)
//
//  object Donut {
//
//    def apply(name: String): Donut = {
//      name match {
//        case "Glazed Donut" => new GlazedDonut(name)
//        case "Vanilla Donut" => new VanillaDonut(name)
//        case _ => new Donut(name)
//      }
//    }
//  }

  println("\n//-----------------------------------------------------------------------------------------------------\n")

//  println("Step 1: How to define a simple class to represent a Donut object")
//  class Donut(name: String, productCode: Option[Long] = None){
//
//    def print = println(s"Donut name = $name, productCode = ${productCode.getOrElse(0)}, uuid = ${Donut.uuid}")
//
//  }
//
//  println("\nStep 2: How to declare fields and values in companion object")
//  object Donut {
//
//    private val uuid = 1
//
//    def apply(name: String, productCode: Option[Long]): Donut = {
//      new Donut(name, productCode)
//    }
//
//    def apply(name: String): Donut = {
//      new Donut(name)
//    }
//  }
//  println("\nStep 3: How to create instances of the Donut class using the companion object")
//  val glazedDonut = Donut("Glazed Donut", Some(1111))
//  val vanillaDonut = Donut("Vanilla Donut")
//  println("\nStep 4: How to call function on each Donut object")
//  glazedDonut.print
//  vanillaDonut.print

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: How to declare a Singleton Object")
  object DonutShoppingCartCalculator {

    println("\nStep 2: How to define a global field")
    val discount: Double = 0.01

    println("\nStep 3: How to define utility function called calculateTotalCost")
    def calculateTotalCost(donuts: List[String]): Double = {
      // calculate the cost of donuts
      1
    }

  }

  println("\nStep 4: How to call global discount field from Step 2")
  println(s"Global discount = ${DonutShoppingCartCalculator.discount}")

  println("\nStep 5: How to call the utility function calculateTotalCost from Step 3")
  println(s"Call to calculateTotalCost function = ${DonutShoppingCartCalculator.calculateTotalCost(List())}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: How to define a case class to represent a Donut object")
//  case class Donut(name: String, price: Double, productCode: Option[Long] = None)
  println("\nStep 2: How to create instances or objects for the Donut case class")
  val vanillaDonut: Donut = Donut("Vanilla Donut", 1.50)
  val glazedDonut: Donut = Donut("Glazed Donut", 2.0)
  println(s"Vanilla Donut = $vanillaDonut")
  println(s"Glazed Donut = $glazedDonut")

  println("\nStep 3: How to access fields of the Donut object")
  println(s"Vanilla Donut name field = ${vanillaDonut.name}")
  println(s"Vanilla Donut price field = ${vanillaDonut.price}")
  println(s"Vanilla Donut productCode field = ${vanillaDonut.productCode}")

  println("\nStep 4: How to modify or update fields of the Donut object")
  // vanillaDonut.name = "vanilla donut" // compiler error. fields are immutable by default.

  println("\nStep 5: How to define the hashCode and equals method for Donut object")
  val shoppingCart: Map[Donut, Int] = Map(vanillaDonut -> 4, glazedDonut -> 3)
  println(s"All items in shopping cart = ${shoppingCart}")
  println(s"Quantity of vanilla donuts in shopping cart = ${shoppingCart(vanillaDonut)}")
  println(s"Quantity of glazed donuts in shopping cart = ${shoppingCart(glazedDonut)}")

  println("\nTIP: How to create a new object of Donut by using the copy() method of the case class")
  val chocolateVanillaDonut: Donut = vanillaDonut.copy(name = "Chocolate And Vanilla Donut", price = 5.0)
  println(s"Chocolate And Vanilla Donut = $chocolateVanillaDonut")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("\nStep 3: How to use type alias to name a Tuple2 pair into a domain type called CartItem")
  type CartItem[Donut, Int] = Tuple2[Donut, Int]
  println("\nStep 4: How to create instances of the aliased typed CartItem")
  val cartItem = new CartItem(vanillaDonut, 4)
  println(s"cartItem = $cartItem")
  println(s"cartItem first value = ${cartItem._1}")
  println(s"cartItem second value = ${cartItem._2}")

  println("\nStep 5: How to use an aliased typed into a function parameter")
  def calculateTotal(shoppingCartItems: Seq[CartItem[Donut, Int]]): Double = {
    // calculate the total cost
    shoppingCartItems.foreach { cartItem =>
      println(s"CartItem donut = ${cartItem._1}, quantity = ${cartItem._2}")
    }
    10 // some random total cost
  }

  println("\nStep 6: How to use a case class instead of an aliased typed")
  case class ShoppingCartItem(donut: Donut, quantity: Int)

  val shoppingItem: ShoppingCartItem = ShoppingCartItem(Donut("Glazed Donut", 2.50), 10)
  println(s"shoppingItem donut = ${shoppingItem.donut}")
  println(s"shoppingItem quantity = ${shoppingItem.quantity}")

  println("\nStep 7: How to use case class from Step 6 to represent a Sequence of Donut items in a shopping cart")
  def calculateTotal2(shoppingCartItems: Seq[ShoppingCartItem]): Double = {
    // calculate the total cost
    shoppingCartItems.foreach { shoppingCartItem =>
      println(s"ShoppingCartItem donut = ${shoppingCartItem.donut}, quantity = ${shoppingCartItem.quantity}")
    }
    10 // some random total cost
  }
  calculateTotal2(Seq(new ShoppingCartItem(vanillaDonut, 10), new ShoppingCartItem(chocolateVanillaDonut, 15)))

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("\nStep 3: How to define an implicit class to augment or extend the Donut object with a uuid field")
//  object DonutImplicits {
//    implicit class AugmentedDonut(donut: Donut) {
//      def uuid: String = s"${donut.name} - ${donut.productCode.getOrElse(12345)}"
//    }
//  }

  // TODO so you can use either implicit classes, to contain more functionalities over a given object, or implicit functions, as wrappers
  println("\nStep 4: How to import and use the implicit class AugmentedDonut from Step 3")
//  import DonutImplicits._
  println(s"Vanilla donut uuid = ${vanillaDonut.uuid}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // TODO package object
  println("\nStep 5: How to create instances or objects for the Donut case class from package object")
  val vanillaDonutP: Donut = Donut("Vanilla", 1.50)
  println(s"Vanilla donut name = ${vanillaDonutP.name}")
  println(s"Vanilla donut price = ${vanillaDonutP.price}")
  println(s"Vanilla donut produceCode = ${vanillaDonutP.productCode}")
  println(s"Vanilla donut uuid = ${vanillaDonutP.uuid}")

  println("\nStep 6: How to create new JodaTime instance using DateTime alias from package object")
  val today = new DateTime()
  println(s"today = $today, datetime class = ${today.getClass}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: How to define an abstract class called Donut")
  abstract class DonutAbs(name: String) {

    def printName: Unit

  }

//  println("\nStep 2: How to extend abstract class Donut and define a sub-class of Donut called VanillaDonut")
//  class VanillaDonut(name: String) extends DonutAbs(name) {
//
//    override def printName: Unit = println(name)
//
//  }
//
//  object VanillaDonut {
//
//    def apply(name: String): DonutAbs = {
//      new VanillaDonut(name)
//    }
//
//  }
//
//  println("\nStep 3: How to extend abstract class Donut and define another sub-class of Donut called GlazedDonut")
//  class GlazedDonut(name: String) extends DonutAbs(name) {
//
//    override def printName: Unit = println(name)
//
//  }
//
//  object GlazedDonut {
//
//    def apply(name: String): DonutAbs = {
//      new GlazedDonut(name)
//    }
//
//  }
//
//  println("\nStep 4: How to instantiate Donut objects")
//  val vanillaDonutAbs: DonutAbs = VanillaDonut("Vanilla Donut")
//  vanillaDonutAbs.printName
//
//  val glazedDonutAbs: DonutAbs = GlazedDonut("Glazed Donut")
//  glazedDonutAbs.printName

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("\nStep 2: How to extend abstract class Donut and define a case class called VanillaDonut")
  case class VanillaDonut(name: String) extends DonutAbs(name) {

    override def printName: Unit = println(name)

  }

  println("\nStep 3: How to extend abstract class Donut and define another case class called GlazedDonut")
  case class GlazedDonut(name: String) extends DonutAbs(name) {

    override def printName: Unit = println(name)

  }

  println("\nStep 4: How to instantiate Donut objects")
  val vanillaDonutAbs: VanillaDonut = VanillaDonut("Vanilla Donut")
  vanillaDonutAbs.printName

  val glazedDonutAbs: GlazedDonut = GlazedDonut("Glazed Donut")
  glazedDonutAbs.printName

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("\nStep 5: How to define a ShoppingCart type class which expects Donut types")
  class ShoppingCart[D <: DonutAbs](donuts: Seq[D]) {

    def printCartItems: Unit = donuts.foreach(_.printName)

  }

  println("\nStep 6: How to create instances or objects of ShoppingCart class")
  val shoppingCartAbs: ShoppingCart[DonutAbs] = new ShoppingCart(Seq(vanillaDonutAbs, glazedDonutAbs))
  shoppingCartAbs.printCartItems

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println(s"\nStep 7: How to enable covariance on ShoppingCart")
  class ShoppingCart2[+D <: DonutAbs](donuts: Seq[D]) {

    def printCartItems: Unit = donuts.foreach(_.printName)

  }

  val shoppingCart2: ShoppingCart2[DonutAbs] = new ShoppingCart2[VanillaDonut](Seq(vanillaDonutAbs))
  shoppingCart2.printCartItems

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println(s"\nStep 7: How to enable contra-variance on ShoppingCart")
  class ShoppingCart3[-D <: DonutAbs](donuts: Seq[D]) {

    def printCartItems: Unit = donuts.foreach(_.printName)

  }

  val shoppingCart3: ShoppingCart3[VanillaDonut] = new ShoppingCart3[DonutAbs](Seq(glazedDonutAbs))
  shoppingCart3.printCartItems

  println("\n//-----------------------------------------------------------------------------------------------------\n")

}
