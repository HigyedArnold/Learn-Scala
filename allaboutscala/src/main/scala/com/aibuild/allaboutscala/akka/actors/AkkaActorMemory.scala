package com.aibuild.allaboutscala.akka.actors

import akka.actor.{Actor, ActorSystem, PoisonPill, Props}

import scala.util.Random
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.postfixOps

case object Produce

object AkkaActorMemory extends App {
  val actorSystem = ActorSystem()
  val actor = actorSystem.actorOf(Props[Parent])

  Console.println("Press enter")
  Console.readLine() //I need a few seconds to attach VisualVM to the process ;)

  actorSystem.scheduler.schedule(2 seconds, 200 millis, actor, Produce)
}

class Parent extends Actor {
  override def receive: Receive = {
    case Produce =>
      val child = context.system.actorOf(Props[Child])
      child ! Produce
  }
}

class Child extends Actor {
  val data = new Array[Byte](20000000)

  override def receive: Receive = {
    case Produce =>
      Random.nextBytes(data)
      context.system.scheduler.scheduleOnce(1 second, self, PoisonPill)
  }
}