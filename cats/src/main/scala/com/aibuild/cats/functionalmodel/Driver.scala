package com.aibuild.cats.functionalmodel

/**
  * Created by ArnoldHigyed on 26/10/2019
  */
object Driver extends App {

  val toppingPrices   = MockPizzaDao.getToppingPrices
  val crustSizePrices = MockPizzaDao.getCrustSizePrices
  val crustTypePrices = MockPizzaDao.getCrustTypePrices

  val pizza1 = Pizza(
    MediumCrustSize,
    ThinCrustType,
    Seq(Cheese, Pepperoni)
  )

  val pizza2 = pizza1.addTopping(Olives)
  val pizza3 = pizza2.updateCrustSize(LargeCrustSize)
  println(s"pizza3: $pizza3")

  val pizzaPrice = pizza3.getPrice(
    toppingPrices,
    crustSizePrices,
    crustTypePrices
  )
  println(s"price of pizza3: $pizzaPrice")

  import ToStringInstances.pizzaAsString
  import ToStringSyntax._

  println(pizza1.asString)
  println(pizza2.asString)
  println(pizza3.asString)

  // Cats
  import cats.Show
  import cats.syntax.show._

  implicit val pizzaShow: Show[Pizza] = Show.show[Pizza] { p =>
     s"Pizza(${p.crustSize}, ${p.crustType}), toppings = ${p.toppings}"
  }

  println(pizza1.show)
  println(pizza2.show)
  println(pizza3.show)


}
