package com.aibuild.cats

/**
  * Created by ArnoldHigyed on 17/10/2019
  */
class Wrapper[A] private (value: A) {

  def map[B](f: A => B): Wrapper[B] = {
    val newValue = f(value)
    new Wrapper(newValue)
  }

  def flatMap[B](f: A => Wrapper[B]): Wrapper[B] = {
    val newValue = f(value)
    newValue
  }
  override def toString: String = value.toString
}

object Wrapper {
  def apply[A](value: A): Wrapper[A] = new Wrapper(value)
}
