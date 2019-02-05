package com.allaboutscala.akka.fsm

import akka.actor.ActorSystem
import scala.util.Success
import akka.testkit.{DefaultTimeout, ImplicitSender, TestActorRef, TestFSMRef, TestKit}
import com.allaboutscala.akka.fsm.DonutStoreProtocol.Info
import com.allaboutscala.akka.fsm.LoggingFSM._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.concurrent.duration._

/**
  * Created by ArnoldHigyed on 06/12/2018
  */
class DonutBakingActorFSMTests
  extends TestKit(ActorSystem("DonutActorFSM"))
    with ImplicitSender
    with DefaultTimeout
    with WordSpecLike
    with BeforeAndAfterAll
    with Matchers {

  private var donutBakingActorFSM: TestFSMRef[BakingStates, BakingData, DonutBakingActor] = _

  override protected def beforeAll(): Unit = {
    donutBakingActorFSM = TestFSMRef(new DonutBakingActor())
  }

  "DonutBakingActor" should {
    "have initial state of BakingStates.Stop" in {
      donutBakingActorFSM.stateName shouldEqual Stop
    }
  }

  "DonutBakingActor" should {
    "process BakeDonut event and switch to the BakingStates.Start state" in {
      donutBakingActorFSM ! BakeDonut
      // test the state using awaitCond
      awaitCond(donutBakingActorFSM.stateName == Start, 2 second, 1 second)
    }
  }

  "DonutBakingActor" should {
    "process StopBaking event and switch to BakingStates.Stop state" in {
      donutBakingActorFSM ! StopBaking
      awaitCond(donutBakingActorFSM.stateName == Stop, 2 second, 1 second)
    }
  }

  "DonutBakingActor current donut quantity" should {
    "equal to 1 after the StopBaking event" in {
      donutBakingActorFSM.stateData.qty shouldEqual 1
    }
  }

  "DonutInfoActor" should {
    "respond back within 1000 millis" in {
      within(1000 millis) {
        val testActor = TestActorRef[DonutInfoActor]
        testActor ! Info("vanilla")
        Thread.sleep(500)
        expectMsg(true)
      }
    }
  }

  "Sending Ask Pattern Info(plain) message to DonutInfoActor" should {
    "reply back with false" in {
      import akka.pattern._
      val testActor = TestActorRef[DonutInfoActor]
      val result = testActor ? Info("plain")
      val Success(reply: Boolean) = result.value.get
      reply shouldBe false
    }
  }

  "Sending a Random Donut message to DonutInfoActor" should {
    "throw IllegalStateException" in {
      val testActor = TestActorRef[DonutInfoActor]
      intercept[IllegalStateException] {
        testActor.receive("Random Donut")
      }
    }
  }

  "The exception message when sending a Random Donut to DonutInfoActor" should {
    "include the words: is not allowed" in {
      val testActor = TestActorRef[DonutInfoActor]
      val exception = the [IllegalStateException] thrownBy {
        testActor.receive("Random Donut")
      }
      exception.getClass shouldEqual classOf[java.lang.IllegalStateException]
      exception.getMessage should be ("Event Random Donut is not allowed")
    }
  }

  override protected def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }

}