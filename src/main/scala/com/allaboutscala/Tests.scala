package com.allaboutscala

import org.scalatest.PrivateMethodTester._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by ArnoldHigyed on 19/11/2018
  */

class Tests extends FlatSpec with Matchers with ScalaFutures {

  behavior of "DonutStore class"

  "favourite donut" should "match vanilla donut" in {
    val donutStore = new DonutStore()
    donutStore.favouriteDonut() shouldEqual "vanilla donut"
    donutStore.favouriteDonut() === "vanilla donut"
    donutStore.favouriteDonut() should not equal "plain donut"
    donutStore.favouriteDonut() should not be "plain donut"
    donutStore.favouriteDonut() !== "Plain donut"
  }

  "Length and size of donuts" should "be equal to 3" in {
    val donutStore = new DonutStore()
    val donuts = donutStore.donuts()
    donuts should have size 3
    donuts should have length 3
  }

  "Examples of boolean assertions" should "be valid" in {
    val donutStore = new DonutStore()
    val donuts = donutStore.donuts()
    donuts.nonEmpty shouldEqual true
    donuts.size === 3
    donuts.contains("chocolate donut") shouldEqual false
    donuts should not be empty
    donutStore.favouriteDonut() should not be empty
  }

  "Examples of collection assertions" should "be valid" in {
    val donutStore = new DonutStore()
    val donuts = donutStore.donuts()
    donuts should contain ("plain donut")
    donuts should not contain "chocolate donut"
    donuts shouldEqual Seq("vanilla donut", "plain donut", "glazed donut")
  }

  "Examples of type assertions" should "be valid" in {
    val donutStore = new DonutStore()
    donutStore shouldBe a [DonutStore]
    donutStore.favouriteDonut() shouldBe a [String]
    donutStore.donuts() shouldBe a [Seq[_]]
  }

  "Method DonutStore.printName()" should "throw IllegalStateException" in {
    val donutStore = new DonutStore()
    val exception = the [java.lang.IllegalStateException] thrownBy {
      donutStore.printName()
    }
    // here we verify that the exception class and the internal message
    exception.getClass shouldEqual classOf[java.lang.IllegalStateException]
    exception.getMessage should include ("Some Error")

    the [java.lang.IllegalStateException] thrownBy {
      donutStore.printName()
    } should have message "Some Error"

    an [java.lang.IllegalStateException] should be thrownBy {
      new DonutStore().printName()
    }
  }

  "Example of testing private method" should "be valid" in {
    val donutStore = new DonutStore()
    val priceWithDiscount = donutStore.donutPrice("vanilla donut")
    priceWithDiscount shouldEqual Some(1.6)

    // test the private method discountByDonut()
    val discountByDonutMethod = PrivateMethod[Double]('discountByDonut)
    val vanillaDonutDiscount = donutStore invokePrivate discountByDonutMethod("vanilla donut")
    vanillaDonutDiscount shouldEqual 0.2
  }

  implicit override val patienceConfig: PatienceConfig =
    PatienceConfig(timeout = Span(5, Seconds), interval = Span(500, Millis))

  "Example of testing asychronous futures" should "be valid" in {
    val donutStore = new DonutStore()
    val donutSalesTaxFuture = donutStore.donutSalesTax("vanilla donut")

    whenReady(donutSalesTaxFuture) { salesTaxForVanillaDonut  =>
      salesTaxForVanillaDonut shouldEqual 0.15
    }
  }

}

// The DonutStore class which we are testing using ScalaTest
class DonutStore {

  def favouriteDonut(): String = "vanilla donut"
  def donuts(): Seq[String] = Seq("vanilla donut", "plain donut", "glazed donut")
  def printName(): Unit = {
    throw new IllegalStateException("Some Error")
  }

  def donutPrice(donut: String): Option[Double] = {
    val prices = Map(
      "vanilla donut" -> 2.0,
      "plain donut"   -> 1.0,
      "glazed donut"  -> 3.0
    )
    val priceOfDonut = prices.get(donut)
    priceOfDonut.map{ price => price * (1 - discountByDonut(donut)) }
  }

  private def discountByDonut(donut: String): Double = {
    val discounts = Map(
      "vanilla donut" -> 0.2,
      "plain donut"   -> 0.1,
      "glazed donut"  -> 0.3
    )
    discounts.getOrElse(donut, 0)
  }

  def donutSalesTax(donut: String): Future[Double] = {
    Thread.sleep(3000)
    println("Task executed!")
    Future(0.15)
  }

}