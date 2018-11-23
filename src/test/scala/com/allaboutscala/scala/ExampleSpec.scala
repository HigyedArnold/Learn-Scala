package com.allaboutscala.scala

import org.scalatest.FlatSpec
import org.scalatest.tagobjects.Slow

import org.scalatest.Tag

object DbTest extends Tag("com.mycompany.tags.DbTest")

class ExampleSpec extends FlatSpec {

  "The Scala language" must "add correctly" taggedAs(Slow) in {
    val sum = 1 + 1
    assert(sum === 2)
  }

  it must "subtract correctly" taggedAs(Slow, DbTest) in {
    val diff = 4 - 1
    assert(diff === 3)
  }
}