package cats

import java.util.Date

import cats.instances.int._
import cats.instances.long._
import cats.instances.option._
import cats.instances.string._
import cats.syntax.eq._
import cats.syntax.option._

/**
  * Created by ArnoldHigyed on 06/02/2019
  */
object Introduction_Eq extends App {

  val eqInt = Eq[Int]
  eqInt.eqv(123, 123)
  eqInt.eqv(123, 234)
  123 === 123
  123 =!= 234
  (Some(1) : Option[Int]) === (None : Option[Int])
  Option(1) === Option.empty[Int]
  1.some === none[Int]
  1.some =!= none[Int]

  implicit val dateEq: Eq[Date] =
    Eq.instance[Date] { (date1, date2) =>
      date1.getTime === date2.getTime
    }

  val x = new Date() // now
  val y = new Date() // a bit later than now
  x === x
  // res13: Boolean = true
  x === y
  // res14: Boolean = false

  final case class Cat(name: String, age: Int, color: String)

  implicit val catEq: Eq[Cat] =
    Eq.instance[Cat] { (cat1, cat2) =>
      cat1.name === cat2.name &&
      cat1.age === cat2.age &&
      cat1.color === cat2.color
    }

  val cat1 = Cat("Garfield", 38, "orange and black")
  val cat2 = Cat("Heathcliff", 33, "orange and black")
  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]

  println(cat1 === cat2)
  // res17: Boolean = false
  println(cat1 =!= cat2)
  // res18: Boolean = true
  println(optionCat1 === optionCat2)
  // res19: Boolean = false
  println(optionCat1 =!= optionCat2)
  // res20: Boolean = true

}