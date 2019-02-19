package allaboutscala


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

  println("\nStep 1: How to create a case class to represent Donut objects")
  case class Donut(name: String, price: Double)

  println("\nStep 2: How to create a Sequence of type Donut")
  val donutsSeq: Seq[Donut] = Seq(Donut("Plain Donut", 1.5), Donut("Strawberry Donut", 2.0), Donut("Glazed Donut", 2.5))
  println(s"Elements of donuts = $donutsSeq")

  println("\nStep 3: How to sort a sequence of case class objects using the sortBy function")
  println(s"Sort a sequence of case class objects of type Donut, sorted by price = ${donutsSeq.sortBy(donut => donut.price)}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // SORTED: def sorted[B >: A](implicit ord: math.Ordering[B]): Repr
  /**
    * The sorted method will return a new collection with elements sorted by their natural order.
    */

  println("Step 1: How to initialize donut prices")
  val prices: Seq[Double] = Seq(1.50, 2.0, 2.50)
  println(s"Elements of prices = $prices")

  println("\nStep 2: How to sort a sequence of type Double using the sorted function")
  println(s"Sort a sequence of type Double by their natural ordering = ${prices.sorted}")

  println("\nStep 4: How to sort a sequence of type String using the sorted function")
  println(s"Sort a sequence of type String by their natural ordering = ${donuts.sorted}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // SORTWITH: def sortWith(lt: (A, A) ⇒ Boolean): Repr
  /**
    * The sortWith method takes a predicate function and will use it to create a new collection where the elements are
    * sorted by the predicate function.
    */

  println("\nStep 3: How to sort a sequence of case class objects using the sortWith function")
  println(s"Sort a sequence of case classes of type Donut, sorted with price = ${donutsSeq.sortWith(_.price < _.price)}")

  println("\nStep 4: How to sort a sequence of case class objects in ascending order using the sortWith function")
  println(s"Sort a sequence of case classes of type Donut, sorted with price in ascending order = ${donutsSeq.sortWith(_.price < _.price)}")

  println("\nStep 5: How to sort a sequence of case class objects in descending order using the sortWith function")
  println(s"Sort a sequence of case classes of type Donut, sorted with price in descending order = ${donutsSeq.sortWith(_.price > _.price)}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // TAIL: def tail: Repr
  /**
    * The tail method returns a collection consisting of all elements except the first one.
    */

  println("\nStep 2: How to return all elements in the sequence except the head using the tail function")
  println(s"Elements of donuts excluding the head = ${donuts.tail}")

  println("\nStep 3: How to access the last element of the donut sequence by using the last function")
  println(s"Last element of donut sequence = ${donuts.last}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // TAKE: def take(n: Int): Repr
  /**
    * The take method takes an integer N as parameter and will use it to return a new collection consisting of the
    * first N elements.
    */

  println("\nStep 2: How to take elements from the sequence using the take function")
  println(s"Take the first donut element in the sequence = ${donuts.take(1)}")
  println(s"Take the first and second donut elements in the sequence = ${donuts.take(2)}")
  println(s"Take the first, second and third donut elements in the sequence = ${donuts.take(3)}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // TAKERIGHT: def takeRight(n: Int): Repr
  /**
    * The takeRight method takes an integer N as parameter and will use it to return a new collection consisting of
    * the last N elements.
    */

  println("\nStep 2: How to take the last N elements using the takeRight function")
  println(s"Take the last donut element in the sequence = ${donuts.takeRight(1)}")
  println(s"Take the last two donut elements in the sequence = ${donuts.takeRight(2)}")
  println(s"Take the last three donut elements in the sequence = ${donuts.takeRight(3)}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // TAKEWHILE: def takeWhile(p: (A) ⇒ Boolean): Repr
  /**
    * The takeWhile method takes a predicate function and will use it to return a new collection consisting of elements
    * which match the predicate function.
    */

  println("\nStep 2: How to take elements from the List using the takeWhile function")
  println(s"Take donut elements which start with letter P = ${donuts.takeWhile(_.charAt(0) == 'P')}")

  println("\nStep 3: How to declare a predicate function to be passed-through to the takeWhile function")
  val takeDonutPredicate: (String) => Boolean = (donutName) => donutName.charAt(0) == 'P'
  println(s"Value function takeDonutPredicate = $takeDonutPredicate")

  println("\nStep 4: How to take elements using the predicate function from Step 3")
  println(s"Take elements using function from Step 3 = ${donuts.takeWhile(takeDonutPredicate)}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // TRANSPOSE: def transpose[B](implicit asTraversable: (A) ⇒ GenTraversableOnce[B]): CC[CC[B]]
  /**
    * The transpose method will pair and overlay elements from another collections into a single collection.
    */

  println("\nStep 3: How to create a List of donuts and prices")
  val donutList = List(donuts, prices)
  println(s"Sequence of donuts and prices = $donutList")
  println("\nStep 4: How to pair each element from both donuts and prices Sequences using the transpose function")
  println(s"Transposed list of donuts paired with their individual prices = ${donutList.transpose}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // UNION: def union(that: GenSet[A]): This
  /**
    * The union method takes a Set as parameter and will merge its elements with the elements from the current Set.
    */

  println("Step 1: How to initialize a Set of donuts")
  val donuts1: Set[String] = Set("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts1 = $donuts1")

  println("\nStep 2: How to initialize another Set of donuts")
  val donuts2: Set[String] = Set("Plain Donut", "Chocolate Donut", "Vanilla Donut")
  println(s"Elements of donuts2 = $donuts2")

  println("\nStep 3: How to merge two Sets using union function")
  println(s"Union of Sets donuts1 and donuts2 = ${donuts1 union donuts2}")
  println(s"Union of Sets donuts2 and donuts1 = ${donuts2 union donuts1}")

  println("\nStep 4: How to merge two Sets using ++ function")
  println(s"Union of Sets donuts1 and donuts2 = ${donuts1 ++ donuts2}")
  println(s"Union of Sets donuts2 and donuts1 = ${donuts2 ++ donuts1}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // UNZIP: def unzip[A1, A2](implicit asPair: (A) ⇒ (A1, A2)): (CC[A1], CC[A2])
  /**
    * The unzip method will unzip and un-merge a collection consisting of element pairs or Tuple2 into two separate
    * collections.
    */

  println("\nStep 3: How to zip the donuts Sequence with their corresponding prices")
  val zippedDonutsAndPrices: Seq[(String, Double)] = donuts zip donutPrices
  println(s"Zipped donuts and prices = $zippedDonutsAndPrices")

  println("\nStep 4: How to unzip the zipped donut sequence into separate donuts names and prices Sequences")
  val unzipped: (Seq[String], Seq[Double]) = zippedDonutsAndPrices.unzip
  println(s"Donut names unzipped = ${unzipped._1}")
  println(s"Donut prices unzipped = ${unzipped._2}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // UNZIP3: def unzip3[A1, A2, A3](implicit asTriple: (A) ⇒ (A1, A2, A3)): (CC[A1], CC[A2], CC[A3])
  /**
    * The unzip3 method will un-merge a collection consisting of elements as Tuple3 into three separate collections.
    */

  println("Step 1: How to initialize a Sequence of Tuple3 elements")
  val donuts3: Seq[(String, Double, String)] = Seq(("Plain Donut",1.5,"Tasty"), ("Glazed Donut",2.0,"Very Tasty"), ("Strawberry Donut",2.5,"Very Tasty"))
  println(s"Donuts tuple3 elements = $donuts3")

  println("\nStep 2: How to call unzip3 function to unzip Tuple3 elements")
  val unzipped3: (Seq[String], Seq[Double], Seq[String]) = donuts3.unzip3
  println(s"Unzipped donut names = ${unzipped3._1}")
  println(s"Unzipped donut prices = ${unzipped3._2}")
  println(s"Unzipped donut taste = ${unzipped3._3}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // VIEW: def view: TraversableView[A, Repr]
  /**
    * The view method will create a non-strict version of the collection which means that the elements of the
    * collection will only be made available at access time.
    */

  println("Step 1: How to create a large numeric range and take the first 10 odd numbers")
  val largeOddNumberList: List[Int] = (1 to 1000000).filter(_ % 2 != 0).take(10).toList
  println(s"Take the first 100 odd numbers from largeOddNumberList = ${largeOddNumberList}")

  println(s"\nStep 2: How to lazily create a large numeric range and take the first 10 odd numbers")
  val lazyLargeOddNumberList = (1 to 1000000).view.filter(_ % 2 != 0).take(100).toList
  println(s"Lazily take the first 100 odd numbers from lazyLargeOddNumberList = ${lazyLargeOddNumberList}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // WITHFILTER: def withFilter(p: (A) ⇒ Boolean): FilterMonadic[A, Repr]
  /**
    * The withFilter method takes a predicate function and will restrict the elements to match the predicate function.
    * withFilter does not create a new collection while filter() method will create a new collection.
    */

  println("\nStep 2: How to filter elements using the withFilter function")
  donuts
    .withFilter(_.charAt(0) == 'P')
    .foreach(donut => println(s"Donut starting with letter P = $donut"))

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // ZIP: def zip[B](that: GenIterable[B]): Iterable[(A, B)]
  /**
    * The zip method takes another collection as parameter and will merge its elements with the elements of the current
    * collection to create a new collection consisting of pairs or Tuple2 elements from both collections.
    */

  println("\nStep 3: How to use zip method to zip two collections")
  val zippedDonutsAndPrices1: Seq[(String, Double)] = donuts zip donutPrices
  println(s"Zipped donuts and prices = $zippedDonutsAndPrices1")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // ZIPWITHINDEX: def zipWithIndex: Iterable[(A, Int)]
  /**
    * The zipWithIndex method will create a new collection of pairs or Tuple2 elements consisting of the element and
    * its corresponding index.
    */

  println("\nStep 2: How to zip the donuts Sequence with their corresponding index using zipWithIndex method")
  val zippedDonutsWithIndex: Seq[(String, Int)] = donuts.zipWithIndex
  zippedDonutsWithIndex.foreach{ donutWithIndex =>
    println(s"Donut element = ${donutWithIndex._1} is at index = ${donutWithIndex._2}")
  }

  println("\n//-----------------------------------------------------------------------------------------------------\n")

}
