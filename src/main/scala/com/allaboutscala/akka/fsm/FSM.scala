package com.allaboutscala.akka.fsm

import akka.actor.FSM.Event
import akka.actor.{Actor, ActorLogging, ActorSystem, LoggingFSM, Props}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

/**
  * Created by ArnoldHigyed on 19/11/2018
  */
object FSM extends App {

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: Create ActorSystem")
  val system = ActorSystem("ActorStateBecome")

  println("\nStep 2: Define DonutBakingActor with become() states")
  class DonutBakingActor extends Actor with ActorLogging {
    import context._

    def receive = {
      case "BakeDonut" =>
        log.info("Becoming BakeDonut state")
        become {
          case "BakeVanilla" =>
            log.info("baking vanilla")

          case "BakePlain" =>
            log.info("baking plain")

          case "StopBaking" =>
            log.info("stopping to bake")
            unbecome()

          case event @ _ =>
            log.info(s"Allowed events [BakeVanilla, BakePlain]], event = $event")
        }

      case event @ _ =>
        log.info(s"Allowed events [BakeDonut], events = $event")
    }
  }

  println("\nStep 3: Create DonutBakingActor")
  val bakingActor = system.actorOf(Props[DonutBakingActor], "donut-baking-actor")

  println("\nStep 4: Send events to actor to switch states and process events")
  bakingActor ! "boom" // not valid
  Thread.sleep(2000)


  bakingActor ! "BakeDonut"
  Thread.sleep(2000)


  bakingActor ! "BakePlain"
  Thread.sleep(2000)


  bakingActor ! "BakeVanilla"
  Thread.sleep(2000)


  bakingActor ! "Bake Chocolate"
  Thread.sleep(2000)

  bakingActor ! "StopBaking"
  Thread.sleep(2000)

  bakingActor ! "BakeVanilla"
  Thread.sleep(2000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("\nStep 2: Define message passing protocol using sealed trait")
  sealed trait DonutProtocol
  case object BakeDonut extends DonutProtocol
  case object BakeVanilla extends DonutProtocol
  case object BakePlain extends DonutProtocol
  case object StopBaking extends DonutProtocol

  println("\nStep 3: Define DonutBakingActor with become() and unbecome() event")
  class DonutBakingActor1 extends Actor {

    import context._

    def startBaking: Receive = {
      case BakeDonut =>
        println("becoming bake state")
        become(bake)

      case event@_ =>
        println(s"Allowed event [$BakeDonut], event = $event")
    }

    def bake: Receive = {
      case BakeVanilla =>
        println("baking vanilla")

      case BakePlain =>
        println("baking plain")

      case StopBaking =>
        println("stopping to bake")
        unbecome()

      case event@_ =>
        println(s"Allowed event [$BakeVanilla, $BakePlain, $StopBaking], event = $event")
    }

    def receive = startBaking

  }

  println("\nStep 4: Create DonutBakingActor1")
  val bakingActor1 = system.actorOf(Props[DonutBakingActor1], "donut-baking-actor1")
  bakingActor1 ! "boom" // not valid
  Thread.sleep(2000)


  bakingActor1 ! BakeDonut
  Thread.sleep(2000)


  bakingActor1 ! BakePlain
  Thread.sleep(2000)


  bakingActor1 ! BakeVanilla
  Thread.sleep(2000)


  bakingActor1 ! "Bake Chocolate"
  Thread.sleep(2000)

  bakingActor1 ! StopBaking
  Thread.sleep(2000)

  bakingActor1 ! BakeVanilla
  Thread.sleep(2000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: Create ActorSystem")
  val system2 = ActorSystem("DonutActorFSM")

  println("\nStep 2: Defining events")
  sealed trait BakingEvents
  final case object BakingDonut extends BakingEvents
  final case object AddTopping extends BakingEvents
  final case object StopBake extends BakingEvents

  println("\nStep 3: Defining states")
  sealed trait BakingStates
  case object Start extends BakingStates
  case object Stop extends BakingStates

  println("\nStep 4: Defining mutatable data")
  case class BakingData(qty: Int) {
    def addOneDonut = copy(qty + 1)
  }

  object BakingData {
    def initialQuantity = BakingData(0)
  }

  println("\nStep 5: Define DonutBakingActor using LoggingFSM trait")
  class DonutBakingActor2 extends LoggingFSM[BakingStates, BakingData] {
    startWith(Stop, BakingData.initialQuantity)

    when(Stop) {
      case Event(BakingDonut, _) =>
        println("Current state is [Stop], switching to [Start]")
        goto(Start).using(stateData.addOneDonut)

      case Event(AddTopping, _) =>
        println("Current state is [Stop], you first need to move to [BakeDonut]")
        stay
    }

    when(Start) {
      case Event(StopBake, _) =>
        println(s"Event StopBaking, current donut quantity = ${stateData.qty}")
        goto(Stop)
    }

    whenUnhandled {
      case Event(event, stateData) =>
        println(s"We've received an unhandled event = [$event] for the state data = [$stateData]")
        goto(Stop)
    }

    initialize()
  }

  println("\nStep 6: Create DonutBakingActor")
  val bakingActor2 = system2.actorOf(Props[DonutBakingActor2], "donut-baking-actor2")

  println("\nStep 7: Send events to actor to switch states and process events")
  bakingActor2 ! AddTopping
  Thread.sleep(2000)

  bakingActor2 ! BakingDonut
  Thread.sleep(2000)

  bakingActor2 ! AddTopping
  Thread.sleep(2000)

  bakingActor2 ! BakingDonut
  Thread.sleep(2000)

  bakingActor2 ! StopBake
  Thread.sleep(2000)

  bakingActor2 ! AddTopping
  Thread.sleep(2000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  val isTerminated = system.terminate()

  isTerminated.onComplete {
    case Success(result) => println("Successfully terminated actor system")
    case Failure(e)     => println("Failed to terminate actor system")
  }

  val isTerminated2 = system2.terminate()

  isTerminated2.onComplete {
    case Success(result) => println("Successfully terminated actor system")
    case Failure(e)     => println("Failed to terminate actor system")
  }

  println("\n//-----------------------------------------------------------------------------------------------------\n")



  println("\n//-----------------------------------------------------------------------------------------------------\n")



  println("\n//-----------------------------------------------------------------------------------------------------\n")



  println("\n//-----------------------------------------------------------------------------------------------------\n")



  println("\n//-----------------------------------------------------------------------------------------------------\n")



  println("\n//-----------------------------------------------------------------------------------------------------\n")

}