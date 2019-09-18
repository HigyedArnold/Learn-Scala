package com.aibuild.cats

/**
  * Created by ArnoldHigyed on 17/10/2019
  */
object DebuggableApp extends App {

  val finalResult = for {
    fResult <- f(100)
    gResult <- g(fResult)
    hResult <- h(gResult)
  } yield hResult

  finalResult.log.foreach(log => println(s"Log: $log"))
  println(s"Output is ${finalResult.value}")

  def f(a: Int): Debuggable[Int] = {
    val result  = a * 2
    val message = s"f: a ($a) * 2 = $result."
    Debuggable(result, List(message))
  }

  def g(a: Int): Debuggable[Int] = {
    val result  = a * 3
    val message = s"g: a ($a) * 3 = $result."
    Debuggable(result, List(message))
  }

  def h(a: Int): Debuggable[Int] = {
    val result  = a * 4
    val message = s"h: a ($a) * 4 = $result."
    Debuggable(result, List(message))
  }

}
