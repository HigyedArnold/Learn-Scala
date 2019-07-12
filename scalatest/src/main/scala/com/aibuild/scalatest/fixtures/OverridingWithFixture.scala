package com.aibuild.scalatest.fixtures

import java.io._

import org.scalatest.{fixture, Outcome}

/**
  * Created by ArnoldHigyed on 5/12/2018
  */
class OverridingWithFixture extends fixture.FlatSpec {

  case class FixtureParam(file: File, writer: FileWriter)

  def withFixture(test: OneArgTest): Outcome = {
    val file       = File.createTempFile("hello", "world") // create the fixture
    val writer     = new FileWriter(file)
    val theFixture = FixtureParam(file, writer)

    try {
      writer.write("ScalaTest is ") // set up the fixture
      withFixture(test.toNoArgTest(theFixture)) // "loan" the fixture to the test
    } finally writer.close() // clean up the fixture
  }

  "Testing" should "be easy" in { f =>
    f.writer.write("easy!")
    f.writer.flush()
    assert(f.file.length === 18)
  }

  it should "be fun" in { f =>
    f.writer.write("fun!")
    f.writer.flush()
    assert(f.file.length === 17)
  }
}
