package com.allaboutscala.scala


/**
  * Created by ArnoldHigyed on 16/11/2018
  */
object CollectionFunctions extends App {

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // AGGREGATE: def aggregate[B](z: =>B)(seqop: (B, A) => B, combop: (B, B) => B): B = foldLeft(z)(seqop)

  println("Step 1: How to initialize a Set of type String to represent Donut elements")
  val donutBasket1: Set[String] = Set("Plain Donut", "Strawberry Donut")
  println(s"Elements of donutBasket1 = $donutBasket1")

  println("\nStep 2: How to define an accumulator function to calculate the total length of the String elements")
  val donutLengthAccumulator: (Int, String) => Int = (accumulator, donutName) => accumulator + donutName.length

  println("\nStep 3: How to call aggregate function with the accumulator function from Step 2")
  val totalLength = donutBasket1.aggregate(0)(donutLengthAccumulator, _ + _)
  println(s"Total length of elements in donutBasket1 = $totalLength")

  println("\nStep 4: How to initialize a Set of Tuple3 elements to represent Donut name, price and quantity")
  val donutBasketF: Set[(String, Double, Int)] = Set(("Plain Donut", 1.50, 10), ("Strawberry Donut", 2.0, 10))
  println(s"Elements of donutBasket2 = $donutBasketF")

  println("\nStep 5: How to define an accumulator function to calculate the total cost of Donuts")
  val totalCostAccumulator: (Double, Double, Int) => Double = (accumulator, price, quantity) => accumulator + (price * quantity)

  println("\nStep 6: How to call aggregate function with accumulator function from Step 5")
  val totalCost = donutBasketF.aggregate(0.0)((ac:Double, tuple: (String, Double, Int)) => totalCostAccumulator(ac, tuple._2, tuple._3), _ + _)
  println(s"Total cost of donuts in donutBasket2 = $totalCost")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // COLLECT: def collect[B](pf: PartialFunction[A, B]): Traversable[B]

  println("Step 1: How to initialize a Sequence which contains donut names and prices")
  val donutNamesandPrices: Seq[Any] = Seq("Plain Donut", 1.5, "Strawberry Donut", 2.0, "Glazed Donut", 2.5)
  println(s"Elements of donutNamesAndPrices = $donutNamesandPrices")

  println("\nStep 2: How to use collect function to cherry pick all the donut names")
  val donutNames: Seq[String] = donutNamesandPrices.collect{ case name: String => name }
  println(s"Elements of donutNames = $donutNames")

  println("\nStep 3: How to use collect function to cherry pick all the donut prices")
  val donutPrices: Seq[Double] = donutNamesandPrices.collect{ case price: Double => price }
  println(s"Elements of donutPrices = $donutPrices")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // DIFF: def diff(that: GenSet[A]): This

  println("Step 1: How to initialize a Set containing 3 donuts")
  val donutBasket2: Set[String] = Set("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donutBasket1 = $donutBasket2")

  println("\nStep 2: How to initialize a Set containing 2 donuts")
  val donutBasket3: Set[String] = Set("Glazed Donut", "Vanilla Donut")
  println(s"Elements of donutBasket2 = $donutBasket3")

  println("\nStep 3: How to find the difference between two Sets using the diff function")
  val diffDonutBasket2From3: Set[String] = donutBasket2 diff donutBasket3
  println(s"Elements of diffDonutBasket1From2 = $diffDonutBasket2From3")

  println("\nStep 4: How to find the difference between two Sets using the diff function")
  val diffDonutBasket3From2: Set[String] = donutBasket3 diff donutBasket2
  println(s"Elements of diff DonutBasket2From1 = $diffDonutBasket3From2")

  println("\nStep 5: How to find the difference between two Sets using the --")
  println(s"Difference between donutBasket2 and donutBasket3 = ${donutBasket2 -- donutBasket3}")
  println(s"Difference between donutBasket3 and donutBasket2 = ${donutBasket3 -- donutBasket2}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // DROP: def drop(n: Int): Repr

  println("Step 1: How to initialize a Sequence of donuts")
  val donuts: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts")

  println("\nStep 2: How to drop the first element using drop function")
  println(s"Drop the first element in the sequence = ${donuts.drop(1)}")

  println("\nStep 3: How to drop the first two elements using the drop function")
  println(s"Drop the first and second elements in the sequence = ${donuts.drop(2)}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // DROPWHILE: def dropWhile(p: (A) ⇒ Boolean): Repr

  println("\nStep 2: How to drop elements from the sequence using the dropWhile function")
  println(s"Drop donut elements whose name starts with letter P = ${donuts.dropWhile(_.charAt(0) == 'P')}")

  println("\nStep 3: How to declare a predicate function to be passed-through to the dropWhile function")
  val dropElementsPredicate: (String) => Boolean = (donutName) => donutName.charAt(0) == 'P'
  println(s"Value function dropElementsPredicate = $dropElementsPredicate")

  println("\nStep 4: How to drop elements using the predicate function from Step 3")
  println(s"Drop elements using function from Step 3 = ${donuts.dropWhile(dropElementsPredicate)}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // EXISTS: def exists(p: (A) ⇒ Boolean): Boolean

  println("\nStep 2: How to check if a particular element exists in the sequence using the exists function")
  val doesPlainDonutExists: Boolean = donuts.exists(donutName => donutName == "Plain Donut")
  println(s"Does Plain Donut exists = $doesPlainDonutExists")

  println("\nStep 3: How to declare a predicate value function for the exists function")
  val plainDonutPredicate: (String) => Boolean = (donutName) => donutName == "Plain Donut"
  println(s"Value function plainDonutPredicate = $plainDonutPredicate")

  println("\nStep 4: How to find element Plain Donut using the exists function and passing through the predicate function from Step 3")
  println(s"Does Plain Donut exists = ${donuts.exists(plainDonutPredicate)}")

  println("\nStep 5: How to declare a predicate def function for the exists function")
  def plainDonutPredicateFunction(donutName: String): Boolean = donutName == "Plain Donut"

  println("\nStep 6: How to find element Plain Donut using the exists function and passing through the predicate function from Step 5")
  println(s"Does plain Donut exists = ${donuts.exists(plainDonutPredicateFunction(_))}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // FILTER: def filter(p: (A) ⇒ Boolean): Repr
  // FILETNOT: def filterNot(p: (A) ⇒ Boolean): Repr

  println("\nStep 2: How to keep only Plain and Glazed Donuts using the filter method")
  val sequenceWithPlainAndGlazedDonut = donuts.filter { donutName =>
    donutName.contains("Plain") || donutName.contains("Glazed")
  }
  println(s"Sequence with Plain and Glazed donuts only = $sequenceWithPlainAndGlazedDonut")

  println("\nStep 3: How to filter out element Vanilla Donut using the filterNot function")
  val sequenceWithoutVanillaDonut = donuts.filterNot(donutName => donutName == "Vanilla Donut" )
  println(s"Sequence without vanilla donut = $sequenceWithoutVanillaDonut")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // FIND: def find(p: (A) ⇒ Boolean): Option[A]

  println("\nStep 2: How to find a particular element in the sequence using the find function")
  val plainDonut: Option[String] = donuts.find(donutName => donutName == "Plain Donut")
  println(s"Find Plain Donut = ${plainDonut.get}")

  println("\nStep 4: How to find element Vanilla Donut using the find function and getOrElse")
  val vanillaDonut2: String = donuts.find(_ == "Vanilla Donut").getOrElse("Vanilla Donut was not found!")
  println(s"Find Vanilla Donuts = $vanillaDonut2")

  println("\n//-----------------------------------------------------------------------------------------------------\n")



  println("\n//-----------------------------------------------------------------------------------------------------\n")



  println("\n//-----------------------------------------------------------------------------------------------------\n")



  println("\n//-----------------------------------------------------------------------------------------------------\n")



  println("\n//-----------------------------------------------------------------------------------------------------\n")



  println("\n//-----------------------------------------------------------------------------------------------------\n")



  println("\n//-----------------------------------------------------------------------------------------------------\n")



  println("\n//-----------------------------------------------------------------------------------------------------\n")

}
