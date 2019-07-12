package com.aibuild.scalatest

import scala.collection.mutable.ListBuffer

/**
  * Created by ArnoldHigyed on 5/12/2018
  */
class Stack[T] {

  val MAX         = 10
  private val buf = new ListBuffer[T]

  def push(o: T): Unit = {
    if (!full)
      buf.prepend(o)
    else
      throw new IllegalStateException("can't push onto a full stack")
  }

  def pop(): T = {
    if (!empty)
      buf.remove(0)
    else
      throw new IllegalStateException("can't pop an empty stack")
  }

  def peek: T = {
    if (!empty)
      buf.head
    else
      throw new IllegalStateException("can't pop an empty stack")
  }

  def full:  Boolean = buf.size == MAX
  def empty: Boolean = buf.isEmpty
  def size:  Int     = buf.size

}
