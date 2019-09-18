package com.aibuild.cats

/**
  * Created by ArnoldHigyed on 17/10/2019
  */
case class Debuggable[A](value: A, log: List[String]) {

  def map[B](f: A => B): Debuggable[B] = {
    val nextValue = f(value)
    Debuggable(nextValue, log)
  }

  def flatMap[B](f: A => Debuggable[B]): Debuggable[B] = {
    val nextValue = f(value)
    Debuggable(nextValue.value, log ::: nextValue.log)
  }
}

object Debuggable {
  def apply[A](value: A, log: List[String]): Debuggable[A] = new Debuggable[A](value, log)
}
