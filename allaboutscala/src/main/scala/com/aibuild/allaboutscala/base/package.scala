package com.aibuild.allaboutscala

package object base {
  println("Step 2: How to define a case class to represent a Donut object in a package object")

  /**
    * A package object.
    */
  println("\nStep 3: How to define an implicit class to augment or extend the Donut object with a uuid field")
  implicit class AugmentedDonut(donut: Donut) {
    def uuid: String = s"${donut.name} - ${donut.productCode.getOrElse(12345)}"
  }

}
