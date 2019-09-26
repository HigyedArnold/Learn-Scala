package com.aibuild.cats

/**
  * Created by ArnoldHigyed on 26/10/2019
  */
package object functionalmodel {

  type Money = BigDecimal

  def dropFirstMatch[A](ls: Seq[A], value: A): Seq[A] = {
    val index = ls.indexOf(value)
    if (index < 0) ls
    else if (index == 0) ls.tail
    else {
      val (a, b) = ls.splitAt(index)
      a ++ b.tail
    }
  }
}
