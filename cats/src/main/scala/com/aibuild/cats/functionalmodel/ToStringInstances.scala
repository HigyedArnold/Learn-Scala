package com.aibuild.cats.functionalmodel

/**
  * Created by ArnoldHigyed on 01/11/2019
  */
object ToStringInstances {

  implicit val pizzaAsString: ToString[Pizza] = new ToString[Pizza] {

    def toString(p: Pizza): String =
      s"Pizza(${p.crustSize}, ${p.crustType}), toppings = ${p.toppings}"
  }

}
