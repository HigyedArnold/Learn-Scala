package com.aibuild.autoschema

object TypeParameterNested extends App {

  def ret[T](any: Any): T = any.asInstanceOf[T]

  println(ret[List[Int]](List("1")).head)

}
