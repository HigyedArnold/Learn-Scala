package com.aibuild.allaboutscala.base

/**
  * Created by ArnoldHigyed on 5/12/2018
  */
object CollectionFunctionsAM extends App {

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // AGGREGATE: def aggregate[B](z: =>B)(seqop: (B, A) => B, combop: (B, B) => B): B = foldLeft(z)(seqop)
  /**
    * The aggregate method aggregates results by first applying a sequence operation which is its first parameter and
    * then uses a combine operator to combine the results produced by the sequence operation.
    */
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
  val totalCost = donutBasketF.aggregate(0.0)((ac: Double, tuple: (String, Double, Int)) => totalCostAccumulator(ac, tuple._2, tuple._3), _ + _)
  println(s"Total cost of donuts in donutBasket2 = $totalCost")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // COLLECT: def collect[B](pf: PartialFunction[A, B]): Traversable[B]
  /**
    * The collect method takes a Partial Function as its parameter and applies it to all the elements in the collection
    * to create a new collection which satisfies the Partial Function.
    */
  println("Step 1: How to initialize a Sequence which contains donut names and prices")
  val donutNamesandPrices: Seq[Any] = Seq("Plain Donut", 1.5, "Strawberry Donut", 2.0, "Glazed Donut", 2.5)
  println(s"Elements of donutNamesAndPrices = $donutNamesandPrices")

  println("\nStep 2: How to use collect function to cherry pick all the donut names")
  val donutNames: Seq[String] = donutNamesandPrices.collect { case name: String => name }
  println(s"Elements of donutNames = $donutNames")

  println("\nStep 3: How to use collect function to cherry pick all the donut prices")
  val donutPrices: Seq[Double] = donutNamesandPrices.collect { case price: Double => price }
  println(s"Elements of donutPrices = $donutPrices")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // DIFF: def diff(that: GenSet[A]): This
  /**
    * The diff method takes another Set as its parameter and uses it to find the elements that are different from the
    * current Set compared to another Set.
    */
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
  /**
    * The drop method takes an integer parameter N and will return a new collection that does not contain the first N
    * elements.
    */
  println("Step 1: How to initialize a Sequence of donuts")
  val donuts: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts")

  println("\nStep 2: How to drop the first element using drop function")
  println(s"Drop the first element in the sequence = ${donuts.drop(1)}")

  println("\nStep 3: How to drop the first two elements using the drop function")
  println(s"Drop the first and second elements in the sequence = ${donuts.drop(2)}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // DROPWHILE: def dropWhile(p: (A) ⇒ Boolean): Repr
  /**
    * The dropWhile method takes a predicate function parameter that will be used to drop all the elements in a
    * collection which satisfies the predicate function. It will then return a new collection with elements that matched
    * the predicate function.
    */
  println("\nStep 2: How to drop elements from the sequence using the dropWhile function")
  println(s"Drop donut elements whose name starts with letter P = ${donuts.dropWhile(_.charAt(0) == 'P')}")

  println("\nStep 3: How to declare a predicate function to be passed-through to the dropWhile function")
  val dropElementsPredicate: String => Boolean = donutName => donutName.charAt(0) == 'P'
  println(s"Value function dropElementsPredicate = $dropElementsPredicate")

  println("\nStep 4: How to drop elements using the predicate function from Step 3")
  println(s"Drop elements using function from Step 3 = ${donuts.dropWhile(dropElementsPredicate)}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // EXISTS: def exists(p: (A) ⇒ Boolean): Boolean
  /**
    * The exists method takes a predicate function and will use it to find the first element in the collection which
    * matches the predicate.
    */
  println("\nStep 2: How to check if a particular element exists in the sequence using the exists function")
  //noinspection ExistsEquals
  val doesPlainDonutExists: Boolean = donuts.exists(donutName => donutName == "Plain Donut")
  println(s"Does Plain Donut exists = $doesPlainDonutExists")

  println("\nStep 3: How to declare a predicate value function for the exists function")
  val plainDonutPredicate: String => Boolean = donutName => donutName == "Plain Donut"
  println(s"Value function plainDonutPredicate = $plainDonutPredicate")

  println("\nStep 4: How to find element Plain Donut using the exists function and passing through the predicate function from Step 3")
  println(s"Does Plain Donut exists = ${donuts.exists(plainDonutPredicate)}")

  println("\nStep 5: How to declare a predicate def function for the exists function")
  def plainDonutPredicateFunction(donutName: String): Boolean = donutName == "Plain Donut"

  println("\nStep 6: How to find element Plain Donut using the exists function and passing through the predicate function from Step 5")
  println(s"Does plain Donut exists = ${donuts.exists(plainDonutPredicateFunction)}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // FILTER: def filter(p: (A) ⇒ Boolean): Repr
  /**
    * The filter method takes a predicate function as its parameter and uses it to select all the elements in the
    * collection which matches the predicate. It will return a new collection with elements that matched the predicate.
    */
  // FILETNOT: def filterNot(p: (A) ⇒ Boolean): Repr
  /**
    * The filterNot method is similar to the filter method except that it will create a new collection with elements
    * that do not match the predicate function.
    */
  println("\nStep 2: How to keep only Plain and Glazed Donuts using the filter method")

  val sequenceWithPlainAndGlazedDonut = donuts.filter { donutName =>
    donutName.contains("Plain") || donutName.contains("Glazed")
  }
  println(s"Sequence with Plain and Glazed donuts only = $sequenceWithPlainAndGlazedDonut")

  println("\nStep 3: How to filter out element Vanilla Donut using the filterNot function")
  val sequenceWithoutVanillaDonut = donuts.filterNot(donutName => donutName == "Vanilla Donut")
  println(s"Sequence without vanilla donut = $sequenceWithoutVanillaDonut")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // FIND: def find(p: (A) ⇒ Boolean): Option[A]
  /**
    * The find method takes a predicate function as parameter and uses it to find the first element in the collection
    * which matches the predicate. It returns an Option and as such it may return a None for the case where it does not
    * match any elements in the collection with the predicate function.
    */
  println("\nStep 2: How to find a particular element in the sequence using the find function")
  val plainDonut: Option[String] = donuts.find(donutName => donutName == "Plain Donut")
  println(s"Find Plain Donut = ${plainDonut.get}")

  println("\nStep 4: How to find element Vanilla Donut using the find function and getOrElse")
  val vanillaDonut2: String = donuts.find(_ == "Vanilla Donut").getOrElse("Vanilla Donut was not found!")
  println(s"Find Vanilla Donuts = $vanillaDonut2")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // FLATMAP: def flatMap[B](f: (A) ⇒ GenTraversableOnce[B]): TraversableOnce[B]
  /**
    * The flatMap method takes a predicate function, applies it to every element in the collection. It then returns a
    * new collection by using the elements returned by the predicate function. The flatMap method is essentially a
    * combination of the map method being run first followed by the flatten method.
    */
  println("Step 1: How to initialize a Sequence of donuts")
  val donuts1: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts1 = $donuts1")

  println("\nStep 2: How to initialize another Sequence of donuts")
  val donuts2: Seq[String] = Seq("Vanilla Donut", "Glazed Donut")
  println(s"Elements of donuts2 = $donuts2")

  println("\nStep 3: How to create a List of donuts initialized using the two Sequences from Step 1 and Step 2")
  val listDonuts: List[Seq[String]] = List(donuts1, donuts2)
  println(s"Elements of listDonuts = $listDonuts")

  println("\nStep 4: How to return a single list of donut using the flatMap function")
  //noinspection ReplaceWithFlatten
  val listDonutsFromFlatMap: List[String] = listDonuts.flatMap(seq => seq)
  println(s"Elements of listDonutsFromFlatMap as a flatMap as a single list = $listDonutsFromFlatMap")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // FLATTEN: def flatten[B]: Traversable[B]
  /**
    * The flatten method will collapse the elements of a collection to create a single collection with elements of the
    * same type.
    */
  println("\nStep 4: How to return a single list of donut using the flatten function")
  val listDonutsFromFlatten: List[String] = listDonuts.flatten
  println(s"Elements of listDonutsFromFlatten = $listDonutsFromFlatten")

  println("\nStep 5: How to append the word Donut to each element of listDonuts using flatten and map functions")
  val listDonutsFromFlatten2: List[String] = listDonuts.flatten.map(_ + " Donut")
  println(s"Elements of listDonutsFromFlatten2 = $listDonutsFromFlatten2")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // FOLD: def fold[A1 >: A](z: A1)(op: (A1, A1) ⇒ A1): A1
  /**
    * The fold method takes an associative binary operator function as parameter and will use it to collapse elements
    * from the collection. The fold method allows you to also specify an initial value.
    */
  println("Step 1: How to initialize a sequence of donut prices")
  val prices: Seq[Double] = Seq(1.5, 2.0, 2.5)
  println(s"Donut prices = $prices")

  println("\nStep 2: How to sum all the donut prices using fold function")
  val sum = prices.fold(0.0)(_ + _)
  println(s"Sum = $sum")

  println("\nStep 4: How to create a String of all donuts using fold function")
  println(s"All donuts = ${donuts.fold("")((acc, s) => acc + s + " Donut ")}")

  println("\nStep 5: How to declare a value function to create the donut string")
  val concatDonuts: (String, String) => String = (s1, s2) => s1 + s2 + " Donut "
  println(s"Value function concatDonuts = $concatDonuts")

  println("\nStep 6: How to create a String of all donuts using value function from Step 5 and fold function")
  println(s"All donuts = ${donuts.fold("")(concatDonuts)}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // FOLDLEFT: def foldLeft[B](z: B)(op: (B, A) ⇒ B): B
  /**
    * The foldLeft method takes an associative binary operator function as parameter and will use it to collapse
    * elements from the collection. The order for traversing the elements in the collection is from left to right and
    * hence the name foldLeft. The foldLeft method allows you to also specify an initial value.
    */
  println("\nStep 2: How to sum all the donut prices using foldLeft function")
  val sum1 = prices.foldLeft(0.0)(_ + _)
  println(s"Sum = $sum1")

  println("\nStep 4: How to create a String of all donuts using foldLeft function")
  println(s"All donuts = ${donuts.foldLeft("")((a, b) => a + b + " Donut ")}")

  println("\nStep 6: How to create a String of all donuts using value function from Step 5 and foldLeft function")
  println(s"All donuts = ${donuts.foldLeft("")(concatDonuts)}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // FOLDRIGHT: def foldRight[B](z: B)(op: (A, B) ⇒ B): B
  /**
    * The foldRight method takes an associative binary operator function as parameter and will use it to collapse
    * elements from the collection. The order for traversing the elements in the collection is from right to left and
    * hence the name foldRight. The foldRight method allows you to also specify an initial value.
    */
  /**
    * Prefer using foldLeft as opposed to foldRight since foldLeft is fundamental in recursive function and will help
    * you prevent stack-overflow exceptions.
    */
  println("\nStep 2: How to sum all the donut prices using foldRight function")
  val sum2 = prices.foldRight(0.0)(_ + _)
  println(s"Sum = $sum2")

  println("\nStep 4: How to create a String of all donuts using foldRight function")
  println(s"All donuts = ${donuts.foldRight("")((a, b) => a + " Donut " + b)}")

  println("\nStep 6: How to create a String of all donuts using value function from Step 5 and foldRight function")
  println(s"All donuts = ${donuts.foldRight("")(concatDonuts)}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // FOREACH: def foreach(f: (A) ⇒ Unit): Unit
  /**
    * The foreach method takes a function as parameter and applies it to every element in the collection. As an example,
    * you can use foreach method to loop through all elements in a collection.
    */
  println("\nStep 2: How to loop through all the elements in the sequence using the foreach function")
  donuts.foreach(println(_))

  println("\nStep 3: How to loop through and access all the elements in the sequence using the foreach function")
  donuts.foreach(donutName => println(s"donutName = $donutName"))

  println("\nStep 4: How to declare a value function to format a donut names into upper case format")

  val uppercase: String => String = s => {
    val upper = s.toUpperCase
    println(upper)
    upper
  }
  println(s"Value function formatting donut names to uppercase = $uppercase")

  println("\nStep 5: How to format all donuts to uppercase using value function from Step 4")
  donuts.foreach(uppercase)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // GROUPBY: groupBy[K](f: (A) ⇒ K): immutable.Map[K, Repr]
  /**
    * The groupBy method takes a predicate function as its parameter and uses it to group elements by key and values
    * into a Map collection.
    */
  println("\nStep 2: How to group elements in a sequence using the groupBy function")
  val donutsGroup: Map[Char, Seq[String]] = donuts.groupBy(_.charAt(0))
  println(s"Group elements in the donut sequence by the first letter of the donut name = $donutsGroup")

  println("\nStep 3: How to create a case class to represent Donut objects")
  case class Donut(name: String, price: Double)

  println("\nStep 4: How to create a Sequence of type Donut")
  val donutsD: Seq[Donut] = Seq(Donut("Plain Donut", 1.5), Donut("Strawberry Donut", 2.0), Donut("Glazed Donut", 2.5))
  println(s"Elements of donuts2 = $donutsD")

  println(s"\nStep 5: How to group case classes donut objects by the name property")
  val donutsGroup2: Map[String, Seq[Donut]] = donutsD.groupBy(_.name)
  println(s"Group element in the sequence of type Donut grouped by the donut name = $donutsGroup2")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // HEAD: def head: A
  /**
    * The head method will return the first element in the collection.
    */
  println("\nStep 2: How to access the first element of the donut sequence")
  //noinspection ZeroIndexToHead
  val head = donuts(0)
  println(s"First element of donut sequence = $head")

  println("\nStep 3: How to access the first element of the donut sequence using the head method")
  println(s"First element of donut sequence using head method = ${donuts.head}")

  println("\nStep 4: How to create an empty sequence")
  val donutsE: Seq[String] = Seq.empty[String]
  println(s"Elements of donuts2 = $donutsE")

  println("\nStep 5: How to access the first element of the donut sequence using the headOption function")
  println(s"First element of empty sequence = ${donutsE.headOption.getOrElse("No donut was found!")}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // ISEMPTY: abstract def isEmpty: Boolean
  /**
    * The isEmpty method will check whether a given collection is empty and will return either true or false.
    */
  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // INTERSECT: abstract def isEmpty: Boolean
  /**
    * The intersect method will find the common elements between two Sets.
    */
  println("Step 1: How to initialize a Set of donuts")
  val donutsI1: Set[String] = Set("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts1 = $donutsI1")

  println("\nStep 2: How to initialize another Set of donuts")
  val donutsI2: Set[String] = Set("Plain Donut", "Chocolate Donut", "Vanilla Donut")
  println(s"Elements of donuts2 = $donutsI2")

  println("\nStep 3: How to find the common elements between two Sets using intersect function")
  println(s"Common elements between donuts1 and donuts2 = ${donutsI1 intersect donutsI2}") // intersect = &
  println(s"Common elements between donuts2 and donuts1 = ${donutsI2 intersect donutsI1}") // intersect = &

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // LAST: def last: A
  /**
    * The last method will return the last element in a collection.
    */
  println("\nStep 2: How to access the last element of the donut sequence by index")
  //noinspection LastIndexToLast
  val last = donuts(donuts.size - 1)
  println(s"Last element of donut sequence = $last")

  println("\nStep 3: How to access the last element of the donut sequence by using the last function")
  println(s"Last element of donut sequence = ${donuts.last}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // MAP: def map[B](f: (A) ⇒ B): Traversable[B]
  /**
    * The map method takes a predicate function and applies it to every element in the collection. It creates a new
    * collection with the result of the predicate function applied to each and every element of the collection.
    */
  println("\nStep 2: How to append the word Donut to each element using the map function")
  val donutsM: Seq[String] = donuts1.map(_ + " Donut")
  println(s"Elements of donuts2 = $donutsM")

  println("\nStep 3: How to create a donut sequence with one None element")
  val donuts3: Seq[AnyRef] = Seq("Plain", "Strawberry", None)
  donuts3.foreach(println(_))

  println("\nStep 4: How to filter out the None element using map function")

  val donuts4: Seq[String] = donuts3.map {
    case donut: String => donut + " Donut"
    case None => "Unknown Donut"
  }
  println(s"Elements of donuts4 = $donuts4")

  println("\nStep 5: How to define couple of functions which returns an Option of type String")
  def favoriteDonut: Option[String] = Some("Glazed Donut")

  def leastFavoriteDonut: Option[String] = None

  println("\nStep 6: How to use map function to filter out None values")
  //noinspection UnitInMap
  favoriteDonut.map(donut => println(s"Favorite donut = $donut"))
  //noinspection UnitInMap
  leastFavoriteDonut.map(donut => println(s"Least favorite donut = $donut"))

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // MAX: def max: A
  /**
    * The max method will iterate through all the elements in a collection and return the largest element.
    */
  println("\nStep 2: How to find the maximum element in the sequence using the max function")
  println(s"Max element in the donuts sequence = ${donuts.max}")

  println("\nStep 4: How to find the maximum element in the sequence using the max function")
  println(s"Max element in the donut prices sequence = ${prices.max}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // MAXBY: def maxBy[B](f: (A) ⇒ B): A
  /**
    * The maxBy method takes a predicate function as its parameter and applies it to every element in the collection
    * to return the largest element.
    */
  println("\nStep 3: How to find the maximum element in a sequence of case classes objects using the maxBy function")
  println(s"Maximum element in sequence of case class of type Donut, ordered by price = ${donutsD.maxBy(donut => donut.price)}")

  println("\nStep 4: How to declare a value predicate function for maxBy function")
  val donutsMaxBy: Donut => Double = donut => donut.price
  println(s"Value function donutMaxBy = $donutsMaxBy")

  println("\nStep 5: How to find the maximum element using maxBy function and pass through the predicate function from Step 4")
  println(s"Maximum element in sequence using function from Step 3 = ${donutsD.maxBy(donutsMaxBy)}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // MIN: def min: A
  /**
    * The min method will iterate through all the elements in the collection and return the smallest element.
    */
  println("\nStep 2: How to find the minimum element in the sequence using the min function")
  println(s"Min element in the donuts sequence = ${donuts.min}")

  println("\nStep 4: How to find the minimum element in the sequence using the min function")
  println(s"Min element in the donut prices sequence = ${prices.min}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // MINBY: def minBy[B](f: (A) ⇒ B): A
  /**
    * The minBy method takes a predicate function as its parameter and applies it to every element in the collection
    * to return the smallest element.
    */
  println("\nStep 3: How to find the minimum element in a sequence of case classes using the minBy function")
  println(s"Minimum element in sequence of case class of type Donut, ordered by price = ${donutsD.minBy(donut => donut.price)}")

  println("\nStep 4: How to declare a value predicate function for minBy function")
  val donutsMinBy: Donut => Double = donut => donut.price
  println(s"Value function donutMinBy = $donutsMinBy")

  println("\nStep 5: How to find the minimum element using minBy function and passing through the predicate function from Step 4")
  println(s"Minimum element in sequence using function from Step 3 = ${donutsD.minBy(donutsMinBy)}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // MKSTRING: def mkString: String
  //
  //           def mkString(sep: String): String
  //
  //           def mkString(start: String, sep: String, end: String): String
  /**
    * The mkString method will help you create a String representation of collection elements by iterating through the
    * collection. The mkString method has an overloaded method which allows you to provide a delimiter to separate each
    * element in the collection. Furthermore, there is another overloaded method to also specify any prefix and postfix
    * literal to be preprended or appended to the String representation..
    */
  println("\nStep 2: How to concatenate the elements of a sequence into a String using mkString function")
  val donutsAsString: String = donuts.mkString(" and ")
  println(s"Donuts elements using mkString function = $donutsAsString")

  println("\nStep 3: How to concatenate the elements of a sequence into a String using mkString and specifying prefix and suffix")
  val donutsWithPrefixAndSuffix: String = donuts.mkString("My favorite donuts namely ", " and ", " are very tasty!")
  println(s"$donutsWithPrefixAndSuffix")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

}
