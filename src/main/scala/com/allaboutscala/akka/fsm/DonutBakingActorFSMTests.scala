package com.allaboutscala.akka.fsm

import akka.actor.ActorSystem
import akka.testkit.{DefaultTimeout, ImplicitSender, TestFSMRef, TestKit}
import com.allaboutscala.akka.fsm.LoggingFSM.{BakingData, BakingStates, DonutBakingActor, Stop}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

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

  override protected def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }

}