package com.aibuild.cats.functionalmodel

/**
  * Created by ArnoldHigyed on 26/10/2019
  */
case class Pizza(
  crustSize: CrustSize,
  crustType: CrustType,
  toppings:  Seq[Topping]
) {

  def addTopping(t: Topping): Pizza = {
    this.copy(toppings = this.toppings :+ t)
  }

  def removeTopping(t: Topping): Pizza = {
    val newToppings = dropFirstMatch(this.toppings, t)
    this.copy(toppings = newToppings)
  }

  def removeAllToppings(p: Pizza): Pizza = {
    val newToppings = Seq[Topping]()
    this.copy(toppings = newToppings)
  }

  def updateCrustSize(cs: CrustSize): Pizza = {
    this.copy(crustSize = cs)
  }

  def updateCrustType(ct: CrustType): Pizza = {
    this.copy(crustType = ct)
  }

  def getPrice(
    toppingPrices:   Map[Topping,   Money],
    crustSizePrices: Map[CrustSize, Money],
    crustTypePrices: Map[CrustType, Money]
  ): Money = {
    val base        = BigDecimal(10)
    val numToppings = this.toppings.size
    val price       = base + 1.00 * numToppings
    price
  }

}
