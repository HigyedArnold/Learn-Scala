package com.aibuild.autoschema

//trait A {
//  def print(): Unit = println("A")
//}
//
//trait B {
//  def print(): Unit = println("B")
//}
//
//trait C {
//  def print(): Unit = println("C")
//}
//
//class MultipleExtends extends A with B with C
//
//object MultipleExtends extends App {
//  val me = new MultipleExtends
//  me.print()
//}
// Not possible!

trait Base { def print(): Unit }
trait A extends Base { override def print(): Unit = println("A") }
trait B extends Base { override def print(): Unit = println("B") }
trait C extends Base { override def print(): Unit = println("C") }

class MultipleExtends extends A with B with C

object MultipleExtends extends App {
  val me = new MultipleExtends
  me.print()
}
