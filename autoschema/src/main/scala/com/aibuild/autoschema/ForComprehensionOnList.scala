package com.aibuild.autoschema

object ForComprehensionOnList extends App {

  val xList = List(1,  2,  3)
  val yList = List()

  val result = for {
    x <- xList
    y <- yList
  } yield (x, y)

  println(result)

}
