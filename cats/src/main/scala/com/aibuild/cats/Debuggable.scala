package com.aibuild.cats

/**
  * Created by ArnoldHigyed on 17/10/2019
  */
case class Debuggable[A](value: A, message: String) {

  def map[B](f: A => B): Debuggable[B] = {
    val nextValue = f(value)
    Debuggable(nextValue, message)
  }

  def flatMap[B](f: A => Debuggable[B]): Debuggable[B] = {
    val nextValue = f(value)
    Debuggable(nextValue.value, message + "\n" + nextValue.message)
  }
}

object Debuggable {
  def apply[A](value: A, message: String): Debuggable[A] = new Debuggable[A](value, message)
}
