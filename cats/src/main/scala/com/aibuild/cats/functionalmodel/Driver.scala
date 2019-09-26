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

}
