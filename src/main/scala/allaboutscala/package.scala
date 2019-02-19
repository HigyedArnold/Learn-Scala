package object allaboutscala {

  println("Step 2: How to define a case class to represent a Donut object in a package object")

  /**
    * A package object.
    * @param name
    * @param price
    * @param productCode
    */

  println("\nStep 3: How to define an implicit class to augment or extend the Donut object with a uuid field")
  implicit class AugmentedDonut(donut: Donut) {
    def uuid: String = s"${donut.name} - ${donut.productCode.getOrElse(12345)}"
  }

  println("\nStep 4: How to alias JodaTime to a DateTime type")
  type DateTime = org.joda.time.DateTime

}
