package com.allaboutscala.scala


/**
  * Created by ArnoldHigyed on 5/12/2018
  */
object CollectionFunctionsNZ extends App {

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // NONEMPTY: def nonEmpty: Boolean
  /**
    * The nonEmpty method will test whether a given collection is not empty and will return either true or false
    */

  println("Step 1: How to initialize a Sequence of donuts")
  val donuts: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts")

  println("\nStep 2: How to check if a sequence is not empty using nonEmpty function")
  println(s"Is donuts sequence NOT empty = ${donuts.nonEmpty}")

  println("\nStep 3: How to create an empty sequence")
  val emptyDonuts: Seq[String] = Seq.empty[String]
  println(s"Elements of emptyDonuts = $emptyDonuts")

  println("\nStep 4: How to find out if sequence is empty using nonEmpty function")
  println(s"Is emptyDonuts sequence empty = ${emptyDonuts.nonEmpty}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // PAR: def par: ParRepr
  /**
    * The par method on collection provides a very easy high level API to allow computation to run in parallel to take
    * advantage of multi-core processing. When you call the par method on a collection, it will copy all the elements
    * into an equivalent Scala Parallel Collection. For additional information on Scala's Parallel Collection, see the
    * official documentation.
    */

  println("Step 1: How to initialize an Immutable Sequence of various donut flavours")
  val donutFlavours: Seq[String] = Seq("Plain", "Strawberry", "Glazed")
  println(s"Elements of donutFlavours immutable sequence = $donutFlavours")

  println("\nStep 2: Convert the Immutable donut flavours Sequence into Parallel Collection")
  import scala.collection.parallel.ParSeq
  val donutFlavoursParallel: ParSeq[String] = donutFlavours.par

  println("\nStep 3: How to use Scala Parallel Collection")
  val donutsPar: ParSeq[String] = donutFlavoursParallel.map(d => s"$d donut")
  println(s"Elements of donuts parallel collection = $donutsPar")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // PARTITION: def partition(p: (A) ⇒ Boolean): (Repr, Repr)
  /**
    * The partition method takes a predicate function as its parameter and will use it to return two collections: one
    * collection with elements that satisfied the predicate function and another collection with elements that did not
    * match the predicate function.
    */

  println("Step 1: How to initialize a sequence which contains donut names and prices")
  val donutNamesAndPrices: Seq[Any] = Seq("Plain Donut", 1.5, "Strawberry Donut", 2.0, "Glazed Donut", 2.5)
  println(s"Elements of donutNamesAndPrices = $donutNamesAndPrices")

  println("\nStep 2: How to split the sequence by the element types using partition function")
  val namesAndPrices: (Seq[Any], Seq[Any]) = donutNamesAndPrices.partition {
    case name: String => true
    case price: Double => false
  }
  println(s"Elements of namesAndPrices = $namesAndPrices")

  println("\nStep 3: How to access the donut String sequence from Step 2")
  println(s"Donut names = ${namesAndPrices._1}")

  println("\nStep 4: How to access the donut prices sequence from Step 2")
  println(s"Donut prices = ${namesAndPrices._2}")

  println("\nStep 5: How to extract the pair returned by partition function")
  val (donutNamesT, donutPricesT) = donutNamesAndPrices.partition {
    case name: String => true
    case _ => false
  }
  println(s"donutNames = $donutNamesT")
  println(s"donutPrices = $donutPricesT")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // REDUCE: def reduce[A1 >: A](op: (A1, A1) ⇒ A1): A1
  /**
    * The reduce method takes an associative binary operator function as parameter and will use it to collapse elements
    * from the collection. Unlike the fold method, reduce does not allow you to also specify an initial value.
    */

  println("Step 1: How to initialize a sequence of donut prices")
  val donutPrices: Seq[Double] = Seq(1.5, 2.0, 2.5)
  println(s"Elements of donutPrices = $donutPrices")

  println("\nStep 2: How to find the sum of the elements using reduce function")
  val sum: Double = donutPrices.reduce(_ + _)
  println(s"Sum of elements from donutPrices = $sum")

  println("\nStep 3: How to find the sum of elements using reduce function explicitly")
  val sum1: Double = donutPrices.reduce((a, b) => a + b)
  println(s"Sum of elements from donutPrices by calling reduce function explicitly= $sum1")

  println("\nStep 4: How to find the cheapest donut using reduce function")
  println(s"Cheapest donut price = ${donutPrices.reduce((x: Double, y: Double) => x min y)}")

  println("\nStep 5: How to find the most expensive donut using reduce function")
  println(s"Most expensive donut price = ${donutPrices.reduce((x: Double, y: Double) => x max y)}")

  println("\nStep 7: How to concatenate the elements from the sequence using reduce function")
  println(s"Elements of donuts sequence concatenated = ${donuts.reduce((left, right) => left + ", " + right)}")

  println("\nStep 8: How to declare a value function to concatenate donut names")
  val concatDonutNames: (String, String) => String = (left, right) => {
    left + ", " + right
  }
  println(s"Value function concatDonutNames = $concatDonutNames")

  println("\nStep 9: How to pass a function to reduce function")
  println(s"Elements of donuts sequence concatenated by passing function to the reduce function = ${donuts reduce concatDonutNames}")

  println("\nStep 10: How to use option reduce to avoid exception if the collection is empty")
  println(s"Using reduce option will NOT throw any exception = ${Seq.empty[String].reduceOption(_ + ", " + _)}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // REDUCELEFT: def reduceLeft[B >: A](op: (B, A) ⇒ B): B
  /**
    * The reduceLeft method takes an associative binary operator function as parameter and will use it to collapse
    * elements from the collection. The order for traversing the elements in the collection is from left to right and
    * hence the name reduceLeft. Unlike the foldLeft method, reduceLeft does not allow you to also specify an initial
    * value.
    */

  println("\nStep 2: How to find the sum of the elements using reduceLeft function")
  val sumL: Double = donutPrices.reduceLeft(_ + _)
  println(s"Sum of elements from donutPrices = $sumL")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // REDUCERIGHT: def reduceRight[B >: A](op: (B, A) ⇒ B): B
  /**
    * The reduceRight method takes an associative binary operator function as parameter and will use it to collapse
    * elements from the collection. The order for traversing the elements in the collection is from right to left and
    * hence the name reduceRight. Unlike the foldRight method, reduceRight does not allow you to also specify an initial
    * value.
    *
    * def reduceRight[B >: A](op: (A, B) => B): B = {
    * if (isEmpty)
    * throw new UnsupportedOperationException("empty.reduceRight")
    *
    *     reversed.reduceLeft[B]((x, y) => op(y, x))
    * }
    */

  println("\nStep 2: How to find the sum of the elements using reduceRight function")
  val sumR: Double = donutPrices.reduceRight(_ + _)
  println(s"Sum of elements from donutPrices = $sumR")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // REVERSE: def reverse: Repr
  /**
    * The reverse method will create a new sequence with the elements in reversed order.
    */

  println("\nStep 2: How to get the elements of the sequence in reverse using the reverse method")
  println(s"Elements of donuts in reversed order = ${donuts.reverse}")

  println("\nStep 3: How to access each reversed element using reverse and foreach methods")
  donuts.reverse.foreach(donut => println(s"donut = $donut"))

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // REVERSEITERATOR: def reverseIterator: Iterator[A]
  /**
    * The reverseIterator method returns an iterator which you can use to traverse the elements of a collection in
    * reversed order.
    */

  println("\nStep 2: How to print all elements in reversed order using reverseIterator function")
  println(s"Elements of donuts in reversed order = ${donuts.reverseIterator.toList}")

  println("\nStep 3: How to iterate through elements using foreach method")
  val reverseIterator: Iterator[String] = donuts.reverseIterator
  reverseIterator.foreach(donut => println(s"donut = $donut"))

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // SCAN: def scan[B >: A, That](z: B)(op: (B, B) ⇒ B)(implicit cbf: CanBuildFrom[Repr, B, That]): That
  /**
    * The scan method takes an associative binary operator function as parameter and will use it to collapse elements
    * from the collection to create a running total from each element. Similar to the fold method, scan allows you to
    * also specify an initial value.
    */

  println("Step 1: How to initialize a sequence of numbers")
  val numbers: Seq[Int] = Seq(1, 2, 3, 4, 5)
  println(s"Elements of numbers = $numbers")

  println("\nStep 2: How to create a running total using the scan function")
  val runningTotal: Seq[Int] = numbers.scan(0)(_ + _)
  println(s"Running total of all elements in the collection = $runningTotal")

  println("\nStep 3: How to create a running total using the scan function explicitly")
  val runningTotal2: Seq[Int] = numbers.scan(0)((a, b) => a + b)
  println(s"Running total of all elements in the collection = $runningTotal2")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // SCANLEFT: def scanLeft[B, That](z: B)(op: (B, A) ⇒ B)(implicit bf: CanBuildFrom[Repr, B, That]): That
  /**
    * The scanLeft method takes an associative binary operator function as parameter and will use it to collapse elements
    * from the collection to create a running total. The order for traversing the elements in the collection is from left
    * to right and hence the name scanLeft.  Similar to the foldLeft method, scanLeft allows you to also specify an
    * initial value.
    */

  println("\nStep 2: How to create a running total using the scanLeft function")
  val runningTotalL: Seq[Int] = numbers.scanLeft(0)(_ + _)
  println(s"Running total of all elements in the collection = $runningTotalL")

  println("\nStep 3: How to create a running total using the scanLeft function explicitly")
  val runningTotal2L: Seq[Int] = numbers.scanLeft(0)((a, b) => a + b)
  println(s"Running total of all elements in the collection = $runningTotal2L")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // SCANRIGHT: def scanRight[B, That](z: B)(op: (A, B) ⇒ B)(implicit bf: CanBuildFrom[Repr, B, That]): That
  /**
    * The scanRight method takes an associative binary operator function as parameter and will use it to collapse
    * elements from the collection to create a running total. The order for traversing the elements in the collection
    * is from right to left and hence the name scanRight.  Similar to the foldRight method, scanRight allows you to
    * also specify an initial value.
    */

  println("\nStep 2: How to create a running total using the scanRight function")
  val runningTotalR: Seq[Int] = numbers.scanRight(0)(_ + _)
  println(s"Running total of all elements in the collection = $runningTotalR")

  println("\nStep 3: How to create a running total using the scanRight function explicitly")
  val runningTotal2R: Seq[Int] = numbers.scanRight(0)((a, b) => a + b)
  println(s"Running total of all elements in the collection = $runningTotal2R")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // SIZE: def size: Int
  /**
    * The size method calculates the number of elements in a collection and return its size.
    */

  println("\nStep 2: How to count the number of elements in the sequence using size function")
  println(s"Size of donuts sequence = ${donuts.size}")

  println("\nStep 3: How to use the count function")
  println(s"Number of times element Plain Donut appear in donuts sequence = ${donuts.count(_ == "Plain Donut")}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // SLICE: def slice(from: Int, until: Int): Repr
  /**
    * The slice method takes a start and end index and will use them to return a new collection with elements that
    * are within the start and end index range.
    */

  println("\nStep 2: How to take a section from the sequence using the slice function")
  println(s"Take elements from the sequence from index 0 to 1 = ${donuts.slice(0,1)}")
  println(s"Take elements from the sequence from index 0 to 2 = ${donuts.slice(0,2)}")
  println(s"Take elements from the sequence from index 0 to 3 = ${donuts.slice(0,3)}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // SORTBY: def sortBy[B](f: (A) ⇒ B)(implicit ord: math.Ordering[B]): Repr
  /**
    * The sortBy method takes a predicate function and will use it to sort the elements in the collection.
    */

  println("\n//-----------------------------------------------------------------------------------------------------\n")



  println("\n//-----------------------------------------------------------------------------------------------------\n")



  println("\n//-----------------------------------------------------------------------------------------------------\n")



  println("\n//-----------------------------------------------------------------------------------------------------\n")



  println("\n//-----------------------------------------------------------------------------------------------------\n")



  println("\n//-----------------------------------------------------------------------------------------------------\n")



  println("\n//-----------------------------------------------------------------------------------------------------\n")



  println("\n//-----------------------------------------------------------------------------------------------------\n")



  println("\n//-----------------------------------------------------------------------------------------------------\n")



  println("\n//-----------------------------------------------------------------------------------------------------\n")

}
