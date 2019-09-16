package com.aibuild.cats

import cats.instances.string._
import cats.syntax.semigroup._

/**
  * Created by ArnoldHigyed on 05/02/2019
  */
object Main extends App {

  def timer[A](f: => (A, A)): (A, Double) = {
    println("Start")
    val startTime = System.nanoTime
    val result = f
    val stopTime = System.nanoTime
    val delta = stopTime - startTime
    (result._2, delta/1000000d)
  }

  println(timer((println("Hello " |+| "Cats!"), Thread.sleep(1000))))

  def plus(a: Int)(b: Int): Int = a + b
  def plus2: Int => Int = plus(2)(_)

  println(plus(1)(2))
  println(plus2(1))

  def add(x: Int, y: Int): Int = x + y
  val addCurried = (add _).curried
  val addCurriedTwo = addCurried(2)

  println(addCurried(2)(2))
  println(addCurriedTwo(2))

  def wrap(prefix: String, html: String, suffix: String): String = {
    prefix + html + suffix
  }
  val wrapWithDiv = wrap("<div>", _: String, "</div>")

  println( wrapWithDiv("Hello, world"))

  case class Person(firstName: String, lastName: String)
  val people = List(
    Person("barney", "rubble"),
    Person("fred", "flintstone")
  )
  val namesStartingWithB = for {
    p <- people
    // generator
    fname = p.firstName
    // definition
    if (fname startsWith "b")
    // filter
  } yield fname.toUpperCase

  println(namesStartingWithB)


}
