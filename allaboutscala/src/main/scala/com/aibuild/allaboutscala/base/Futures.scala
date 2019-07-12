package com.aibuild.allaboutscala.base

import scala.concurrent.Promise
import scala.language.postfixOps
import scala.util.Try

/**
  * Created by ArnoldHigyed on 16/11/2018
  */
object Futures extends App {

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: Define a method which returns a Future")
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.Future

  def donutStock(donut: String): Future[Int] = Future {
    // assume some long running database operation
    println("checking donut stock")
    10
  }
  println("\nStep 2: Call method which returns a Future")
  import scala.concurrent.Await
  import scala.concurrent.duration._
  val vanillaDonutStock = Await.result(donutStock("vanilla donut"), 5 seconds)
  println(s"Stock of vanilla donut = $vanillaDonutStock")
  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("\nStep 2: Non blocking future result")
  import scala.util.{Failure, Success}
  donutStock("vanilla donut").onComplete {
    case Success(stock) => println(s"Stock for vanilla donut = $stock")
    case Failure(e)     => println(s"Failed to find vanilla donut stock, exception = $e")
  }
  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("\nStep 2: Define another method which returns a Future")

  def buyDonuts(quantity: Int): Future[Boolean] = Future {
    println(s"buying $quantity donuts")
    true
  }

  println("\nStep 3: Chaining Futures using flatMap")
  val buyingDonuts: Future[Boolean] = donutStock("plain donut").flatMap(qty => buyDonuts(qty))
  val isSuccess = Await.result(buyingDonuts, 5 seconds)
  println(s"Buying vanilla donut was successful = $isSuccess")
  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("\nStep 3: Chaining Futures using for comprehension")
  for {
    stock     <- donutStock("vanilla donut")
    isSuccess <- buyDonuts(stock)
  } yield println(s"Buying vanilla donut was successful = $isSuccess")
  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: Define a method which returns a Future Option")

  def donutStockO(donut: String): Future[Option[Int]] = Future {
    // assume some long running database operation
    println("checking donut stock")
    if (donut == "vanilla donut") Some(10) else None
  }

  println("\nStep 3: Chaining Future Option using for comprehension")
  for {
    someStock <- donutStockO("vanilla donut")
    isSuccess <- buyDonuts(someStock.getOrElse(0))
  } yield println(s"Buying vanilla donut was successful = $isSuccess")
  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println(s"\nStep 2: Access value returned by future using map() method")
  donutStockO("vanilla donut")
    .map(someQty => println(s"Buying ${someQty.getOrElse(0)} vanilla donuts"))
  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println(s"\nStep 3: Calling map() method over multiple futures")

  val resultFromMap: Future[Future[Boolean]] = donutStockO("vanilla donut")
    .map(someQty => buyDonuts(someQty.getOrElse(0)))

  println(s"\nStep 4: Calling flatMap() method over multiple futures")

  val resultFromFlatMap: Future[Boolean] = donutStockO("vanilla donut")
    .flatMap(someQty => buyDonuts(someQty.getOrElse(0)))
  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("\nStep 2: Define another method which returns a Future[Boolean]")

  def donutStockW(donut: String): Future[Option[Int]] = Future {
    println("checking donut stock ... sleep for 2 seconds")
    Thread.sleep(2000)
    if (donut == "vanilla donut") Some(10) else None
  }

  println("\nStep 2: Define another method which returns a Future[Boolean]")

  def buyDonutsW(quantity: Int): Future[Boolean] = Future {
    println(s"buying $quantity donuts ... sleep for 3 seconds")
    Thread.sleep(3000)
    if (quantity > 0) true else false
  }

  println("\nStep 3: Define another method for processing payments and returns a Future[Unit]")

  def processPayment(): Future[Unit] = Future {
    println("processPayment ... sleep for 1 second")
    Thread.sleep(1000)
  }

  println("\nStep 4: Combine future operations into a List")
  val futureOperations: List[Future[Any]] = List(donutStockW("vanilla donut"), buyDonutsW(10), processPayment())

  println(s"\nStep 5: Call Future.sequence to run the future operations in parallel")
  val futureSequenceResults = Future.sequence(futureOperations)
  futureSequenceResults.onComplete {
    case Success(results) => println(s"Results $results")
    case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
  }
  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println(s"\nStep 2: Create a List of future operations")

  val futureOperations2 = List(
    donutStockO("vanilla donut"),
    donutStockO("plain donut"),
    donutStockO("chocolate donut")
  )

  println(s"\nStep 3: Call Future.traverse to convert all Option of Int into Int")

  val futureTraverseResult = Future.traverse(futureOperations2) { futureSomeQty =>
    futureSomeQty.map(someQty => someQty.getOrElse(0))
  }
  futureTraverseResult.onComplete {
    case Success(results) => println(s"Results $results")
    case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
  }
  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println(s"\nStep 3: Call Future.foldLeft to fold over futures results from left to right")

  val futureFoldLeft = Future.foldLeft(futureOperations2)(0) {
    case (acc, someQty) =>
      acc + someQty.getOrElse(0)
  }
  futureFoldLeft.onComplete {
    case Success(results) => println(s"Results $results")
    case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
  }
  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println(s"\nStep 3: Call Future.reduceLeft to fold over futures results from left to right")

  val futureFoldLeft2 = Future.reduceLeft(futureOperations2) {
    case (acc, someQty) =>
      acc.map(qty => qty + someQty.getOrElse(0))
  }
  futureFoldLeft2.onComplete {
    case Success(results) => println(s"Results $results")
    case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
  }
  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println(s"\nStep 2: Create a List of future operations")

  val futureOperations3 = List(
    donutStock("vanilla donut"),
    donutStock("plain donut"),
    donutStock("chocolate donut"),
    donutStock("vanilla donut")
  )

  println(s"\nStep 3: Call Future.firstCompletedOf to get the results of the first future that completes")
  val futureFirstCompletedResult = Future.firstCompletedOf(futureOperations3)
  futureFirstCompletedResult.onComplete {
    case Success(results) => println(s"First results $results")
    case Failure(e)       => println(s"First error processing future operations, error = ${e.getMessage}")
  }
  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println(s"\nStep 2: Define a method which returns a Future Double for donut price")
  def donutPrice(): Future[Double] = Future.successful(3.25)

  println(s"\nStep 3: Zip the values of the first future with the second future")
  val donutStockAndPriceOperation = donutStockO("vanilla donut") zip donutPrice()
  donutStockAndPriceOperation.onComplete {
    case Success(results) => println(s"Results $results")
    case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
  }
  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println(s"\nStep 3: Define a value function to convert Tuple (Option[Int], Double) to Tuple (Int, Double)")
  val qtyAndPriceF: (Option[Int], Double) => (Int, Double) = (someQty, price) => (someQty.getOrElse(0), price)

  println(s"\nStep 4: Call Future.zipWith and pass-through function qtyAndPriceF")
  val donutAndPriceOperation = donutStockO("vanilla donut").zipWith(donutPrice())(qtyAndPriceF)
  donutAndPriceOperation.onComplete {
    case Success(result) => println(s"Result $result")
    case Failure(e)      => println(s"Error processing future operations, error = ${e.getMessage}")
  }
  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println(s"\nStep 2: Call Future.andThen with a PartialFunction")
  val donutStockOperation = donutStock("vanilla donut")
  donutStockOperation.andThen { case stockQty => println(s"Donut stock qty = $stockQty") }
  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

//  println("Step 1: Define an ExecutionContext")
//  val executor = Executors.newSingleThreadExecutor()
//  implicit val ec = scala.concurrent.ExecutionContext.fromExecutor(executor)
//  println("\nStep 3: Call method which returns a Future")
//  val donutStockOperation2 = donutStock("vanilla donut")
//  donutStockOperation2.onComplete {
//    case Success(donutStock)  => println(s"Results $donutStock")
//    case Failure(e)           => println(s"Error processing future operations, error = ${e.getMessage}")
//  }
//
//  Thread.sleep(3000)
//  executor.shutdownNow()
//  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  def donutStockE(donut: String): Future[Int] = Future {
    if (donut == "vanilla donut") 10
    else throw new IllegalStateException("Out of stock")
  }

  println("\nStep 2: Execute donutStock() future operation")
  donutStockE("vanilla donut").onComplete {
    case Success(donutStock) => println(s"Results $donutStock")
    case Failure(e)          => println(s"Error processing future operations, error = ${e.getMessage}")
  }

  println("\nStep 3: Call Future.recover to recover from a known exception")
  donutStockE("unknown donut").recover { case e: IllegalStateException if e.getMessage == "Out of stock" => 0 }.onComplete {
    case Success(donutStock) => println(s"Results $donutStock")
    case Failure(e)          => println(s"Error processing future operations, error = ${e.getMessage}")
  }

  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("\nStep 3: Call Future.recoverWith to recover from a known exception")
  donutStockE("unknown donut").recoverWith { case e: IllegalStateException if e.getMessage == "Out of stock" => Future.successful(0) }.onComplete {
    case Success(donutStock) => println(s"Results $donutStock")
    case Failure(e)          => println(s"Error processing future operations, error = ${e.getMessage}")
  }

  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("\nStep 2: Define another method which returns a Future to match a similar donut stock")

  def similarDonutStock(donut: String): Future[Int] = Future {
    println(s"replacing donut stock from a similar donut = $donut")
    if (donut == "vanilla donut") 20 else 5
  }

  println("\nStep 3: Call Future.fallbackTo")
  donutStockE("plain donut")
    .fallbackTo(similarDonutStock("vanilla donut"))
    .onComplete {
      case Success(donutStock) => println(s"Results $donutStock")
      case Failure(e)          => println(s"Error processing future operations, error = ${e.getMessage}")
    }

  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  def donutStockP(donut: String): Int = {
    if (donut == "vanilla donut") 10
    else throw new IllegalStateException("Out of stock")
  }

  println(s"\nStep 2: Define a Promise of type Int")
  val donutStockPromise = Promise[Int]()

  println("\nStep 3: Define a future from Promise")
  val donutStockFuture = donutStockPromise.future
  donutStockFuture.onComplete {
    case Success(stock) => println(s"Stock for vanilla donut = $stock")
    case Failure(e)     => println(s"Failed to find vanilla donut stock, exception = $e")
  }

  println("\nStep 4: Use Promise.success or Promise.failure to control execution of your future")
  val donut = "vanilla donut"
  if (donut == "vanilla donut") {
    donutStockPromise.success(donutStockP(donut))
  }
  else {
    donutStockPromise.failure(Try(donutStockP(donut)).failed.get)
  }

  println("\nStep 5: Completing Promise using Promise.complete() method")
  val donutStockPromise2 = Promise[Int]()
  val donutStockFuture2  = donutStockPromise2.future
  donutStockFuture2.onComplete {
    case Success(stock) => println(s"Stock for vanilla donut = $stock")
    case Failure(e)     => println(s"Failed to find vanilla donut stock, exception = $e")
  }
  donutStockPromise2.complete(Try(donutStockP("unknown donut")))

  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

}
