package com.aibuild.allaboutscala.base

import scala.concurrent.Future
import scala.io.Source

/**
  * Created by ArnoldHigyed on 19/11/2018
  */
object Tricks extends App {

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // Step 1: Import converters
  import scala.collection.JavaConverters._

  // Step 2: Assume you have a Java Map
  val donutJavaMap: java.util.Map[String, Double] = new java.util.HashMap[String, Double]()
  donutJavaMap.put("Plain Donut",   2.50)
  donutJavaMap.put("Vanilla Donut", 3.50)

  // Step 3: Convert the Java Map by calling .asScala
  val donutScalaMap = donutJavaMap.asScala

  // Step 4: You now have a Scala Map
  val pricePlainDonut = donutScalaMap("Plain Donut")
  val setDonuts       = donutScalaMap.keySet

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /** Convert multi-line string to single line **/
  // Step 1: Define an implicit class to strip out line endings
  implicit class StringConversion(str: String) {

    def inline(): String = str.replaceAll(scala.compat.Platform.EOL, " ")

  }

  // Step 2: Create a multi-line string
  val multilineStr =
    """
      |Plain Donut
      |Vanilla Donut
    """.stripMargin

  println(s"Multi-line as single line = ${multilineStr.inline()}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /** Check the value of an Option **/
  println(Some(5).contains(5))

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /** Function to read a file and return a String of its contents **/
  def readFile(file: String): String = {
    Source
      .fromInputStream(getClass.getResourceAsStream(file))
      .getLines
      .mkString("\n")
  }

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /** Create enum using sealed trait **/
  sealed trait Donut
  case object Vanilla   extends Donut
  case object Chocolate extends Donut
  case object Plain     extends Donut

  //noinspection AccessorLikeMethodIsUnit
  def isValidDonut(donut: Donut): Unit = {
    donut match {
      case Vanilla | Chocolate | Plain => println("Valid donut")
      case _                           => println("Unknown donut!")
    }
  }

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /** Int division in Scala and return a float which keeps the decimal values: **/
  val donutQuantity:  Int = 10
  val donutTotalCost: Int = 25
  val donutPrice = donutTotalCost.toFloat / donutQuantity
  println(s"Cost of one donut = $donutPrice")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /** Cannot find an implicit ExecutionContext **/
  // Step 1: Need to import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.ExecutionContext.Implicits.global

  val future: Future[Int] = Future {
    // some long running operation
    1
  }

  println("\n//-----------------------------------------------------------------------------------------------------\n")

}
