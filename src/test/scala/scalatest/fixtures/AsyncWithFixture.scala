package scalatest.fixtures

import java.io.File

import org.scalatest._

import scala.concurrent.Future

/**
  * Created by ArnoldHigyed on 5/12/2018
  */
class AsyncWithFixture extends AsyncFunSuite {

 override def withFixture(test: NoArgAsyncTest) = {

  super.withFixture(test) onFailedThen { _ =>
   val currDir = new File(".")
   val fileNames = currDir.list()
   info("Dir snapshot: " + fileNames.mkString(", "))
  }
 }

 def addSoon(addends: Int*): Future[Int] = Future { addends.sum }

 test("This test should succeed") {
  addSoon(1, 1) map { sum => assert(sum === 2) }
 }

 test("This test should fail") {
  addSoon(1, 1) map { sum => assert(sum === 3) }
 }
}