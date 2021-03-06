package com.aibuild.scalatest.fixtures

import org.scalatest._

import scala.collection.mutable.ListBuffer

/**
  * Created by ArnoldHigyed on 5/12/2018
  */
class MixingBeforeAndAfter extends FlatSpec with BeforeAndAfter {

  val builder = new StringBuilder
  val buffer  = new ListBuffer[String]

  before {
    builder.append("ScalaTest is ")
  }

  after {
    builder.clear()
    buffer.clear()
  }

  "Testing" should "be easy" in {
    builder.append("easy!")
    assert(builder.toString === "ScalaTest is easy!")
    assert(buffer.isEmpty)
    buffer += "sweet"
  }

  it should "be fun" in {
    builder.append("fun!")
    assert(builder.toString === "ScalaTest is fun!")
    assert(buffer.isEmpty)
  }
}
