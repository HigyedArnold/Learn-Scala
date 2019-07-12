package com.aibuild.allaboutscala.akka.fsm

import akka.actor.{Actor,                ActorLogging,   ActorSystem}
import akka.testkit.{DefaultTimeout,     ImplicitSender, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers,       WordSpecLike}

class DonutInfoActorTests extends TestKit(ActorSystem("DonutActorTests")) with ImplicitSender with DefaultTimeout with WordSpecLike with BeforeAndAfterAll with Matchers {

  override protected def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }
}

object DonutStoreProtocol {
  case class Info(name: String)
}

class DonutInfoActor extends Actor with ActorLogging {
  import DonutStoreProtocol._

  def receive: PartialFunction[Any, Unit] = {
    case Info(name) if name == "vanilla" =>
      log.info(s"Found valid $name donut")
      sender ! true

    case Info(name) =>
      log.info(s"$name donut is not supported")
      sender ! false

    case event @ _ =>
      log.info(s"Event $event is not allowed.")
      throw new IllegalStateException(s"Event $event is not allowed")
  }
}
