package com.aibuild.cats.functionalmodel

/**
  * Created by ArnoldHigyed on 01/11/2019
  */
trait ToString[A] {
  def toString(a: A): String
}