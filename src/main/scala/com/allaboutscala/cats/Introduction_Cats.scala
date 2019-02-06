package com.allaboutscala.cats

import java.util.Date

import cats.Show
import cats.instances.int._
import cats.instances.string._
import cats.syntax.show._ // for Show

/**
  * Created by ArnoldHigyed on 06/02/2019
  */
object Introduction_Cats extends App {

// val showInt: Show[Int] = Show.apply[Int]
// val showString: Show[String] = Show.apply[String]
//
// val intAsString: String = showInt.show(123)
// // intAsString: String = 123
// val stringAsString: String = showString.show("abc")
// // stringAsString: String = abc

 val shownInt = 123.show
 // shownInt: String = 123
 val shownString = "abc".show
 // shownString: String = abc

 implicit val dateShow: Show[Date] = Show.show(date => s"${date.getTime}ms since the epoch.")

 final case class Cat(name: String, age: Int, color: String)

 implicit val catShow = Show.show[Cat] { cat =>
  val name = cat.name.show
  val age = cat.age.show
  val color = cat.color.show
  s"$name is a $age year-old $color cat."
 }

 println(Cat("Garfield", 38, "ginger and black").show)
 // Garfield is a 38 year-old ginger and black cat.

}