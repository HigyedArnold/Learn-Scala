package com.aibuild.autoschema

object TypeParameterMatching extends App {

//  trait bTrait //common base trait
  trait TraitA //extends bTrait
  trait TraitB //extends bTrait

  class ClassA extends TraitA
  class ClassB extends TraitB

  def myFunc[T /*<: bTrait*/](t:T) : String = //passing explicitly an instance of type T
  {
    t match {
      case _ : TraitA => "TraitA"
      case _ : TraitB => "TraitB"
    }
  }

  println(myFunc(new ClassA)) //prints TraitA

}
