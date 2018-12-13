package scalatest.fixtures

import org.scalatest._

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by ArnoldHigyed on 5/12/2018
  */
// Defining actor messages
sealed abstract class StringOp
case object Clear extends StringOp
case class Append(value: String) extends StringOp
case object GetValue

class StringActor { // Simulating an actor
  private final val sb = new StringBuilder
  def !(op: StringOp): Unit =
    synchronized {
      op match {
        case Append(value) => sb.append(value)
        case Clear => sb.clear()
      }
    }
  def ?(get: GetValue.type)(implicit c: ExecutionContext): Future[String] =
    Future {
      synchronized { sb.toString }
    }
}

class StackingAsyncWithFixture extends fixture.AsyncFlatSpec {

  type FixtureParam = StringActor

  def withFixture(test: OneArgAsyncTest): FutureOutcome = {

    val actor = new StringActor
    complete {
      actor ! Append("ScalaTest is ") // set up the fixture
      withFixture(test.toNoArgAsyncTest(actor))
    } lastly {
      actor ! Clear // ensure the fixture will be cleaned up
    }
  }

  "Testing" should "be easy" in { actor =>
    actor ! Append("easy!")
    val futureString = actor ? GetValue
    futureString map { s =>
      assert(s == "ScalaTest is easy!")
    }
  }

  it should "be fun" in { actor =>
    actor ! Append("fun!")
    val futureString = actor ? GetValue
    futureString map { s =>
      assert(s == "ScalaTest is fun!")
    }
  }
}