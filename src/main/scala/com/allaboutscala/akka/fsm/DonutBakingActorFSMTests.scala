package com.allaboutscala.akka.fsm

import akka.actor.ActorSystem
import akka.testkit.{DefaultTimeout, ImplicitSender, TestFSMRef, TestKit}
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

  override protected def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }

}