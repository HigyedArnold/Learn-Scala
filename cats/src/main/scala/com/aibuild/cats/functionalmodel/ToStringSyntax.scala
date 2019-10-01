package com.aibuild.cats.functionalmodel

/**
  * Created by ArnoldHigyed on 01/11/2019
  */
object ToStringSyntax {
  implicit class ToStringOps[A](value: A) {

    def asString(implicit toStringInstance: ToString[A]): String = {
      toStringInstance.toString(value)
    }
  }
}
