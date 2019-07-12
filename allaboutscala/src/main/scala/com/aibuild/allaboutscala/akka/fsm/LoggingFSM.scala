package com.aibuild.allaboutscala.akka.fsm

import akka.actor.{Actor, ActorLogging, ActorSystem, LoggingFSM, PoisonPill, Props}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.StdIn
import scala.language.postfixOps
import scala.util.{Failure, Success}

/**
  * Created by ArnoldHigyed on 06/12/2018
  */
object LoggingFSM extends App {

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: Create ActorSystem")
  val system = ActorSystem("DonutActorFSM")

  println("\nStep 2: Defining events")
  sealed trait BakingEvents
  final case object BakeDonut  extends BakingEvents
  final case object AddTopping extends BakingEvents
  final case object StopBaking extends BakingEvents

  println("\nStep 3: Defining states")
  sealed trait BakingStates
  case object Start extends BakingStates
  case object Stop  extends BakingStates

  println("\nStep 4: Defining mutatable data")
  case class BakingData(qty: Int) {
    def addOneDonut: BakingData = copy(qty + 1)
  }

  object BakingData {
    def initialQuantity = BakingData(0)
  }

  println("\nStep 5: Define DonutBakingActor using LoggingFSM trait")

  class DonutBakingActor extends LoggingFSM[BakingStates, BakingData] {
    startWith(Stop, BakingData.initialQuantity)

    when(Stop) {
      case Event(BakeDonut, _) =>
        println("Current state is [Stop], switching to [Start]")
        goto(Start).using(stateData.addOneDonut)

      case Event(AddTopping, _) =>
        println("Current state is [Stop], you first need to move to [BakeDonut]")
        stay
    }

    import scala.concurrent.duration._
    when(Start, stateTimeout = 5 second) {
      case Event(StopBaking, _) =>
        println(s"Event StopBaking, current donut quantity = ${stateData.qty}")
        goto(Stop)

      case Event(StateTimeout, _) =>
        println("Timing out state = [Start], switching to state = [Stop]")
        goto(Stop)
    }

    whenUnhandled {
      case Event(event, stateData) =>
        println(s"We've received an unhandled event = [$event] for the state data = [$stateData]")
        goto(Stop)
    }

    onTransition {
      case Stop  -> Start => println("Switching state from [Stop -> Start]")
      case Start -> Stop  => println("Switching state from [Start -> Stop]")
    }

    import akka.actor.FSM.{Failure, _}
    onTermination {
      case StopEvent(Normal, state, data) =>
        log.info(s"Actor onTermination, event = Normal, state = $state, data = $data")

      case StopEvent(Shutdown, state, data) =>
        log.info(s"Actor onTermination, event = Shutdown, state = $state, data = $data")

      case StopEvent(Failure(cause), state, data) =>
        log.error(s"Actor onTermination, event = Failure, cause = $cause, state = $state, data = $data")
    }

    initialize()
  }

  println("\nStep 6: Create DonutBakingActor")
  val bakingActor = system.actorOf(Props[DonutBakingActor], "donut-baking-actor")

  println("\nStep 7: Send events to actor to switch states and process events")
  bakingActor ! AddTopping
  Thread.sleep(2000)

  bakingActor ! BakeDonut
  Thread.sleep(2000)

  bakingActor ! AddTopping
  Thread.sleep(2000)

  bakingActor ! BakeDonut
  Thread.sleep(2000)

  bakingActor ! StopBaking
  Thread.sleep(2000)

  bakingActor ! AddTopping
  Thread.sleep(2000)

  println("\nStep 7: Send events to actor to switch states and process events")
  bakingActor ! BakeDonut
  Thread.sleep(10000)

  bakingActor ! AddTopping
  Thread.sleep(2000)

  println("\nStep 7: Send events to actor to switch states and process events")
  bakingActor ! PoisonPill
  Thread.sleep(2000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // Scheduler

  println("Step 1. Create ActorSystem")
  val systemS = ActorSystem("ActorState")

  println("\nStep 2: Define DonutBakingActor with become() and unbecome() states")

  class DonutBakingActorS extends Actor with ActorLogging {
    import context._

    def receive: PartialFunction[Any, Unit] = {
      case "BakeDonut" =>
        log.info("becoming bake state")
        become {
          case "BakeVanilla" =>
            log.info("baking vanilla")

          case "BakePlain" =>
            log.info("baking plain")

          case "StopBaking" =>
            log.info("stopping to bake")
            unbecome()

          case event @ _ =>
            log.info(s"Allowed events [BakeVanilla, BakePlain, StopBaking], event = $event")
        }

      case event @ _ =>
        log.info(s"Allowed events [BakeDonut], event = $event")
    }
  }

  println("\nStep 3: Create DonutBakingActor")
  val bakingActorS = systemS.actorOf(Props[DonutBakingActorS], "donut-baking-actorS")

  println("\nStep 4: Send events to actor to switch states and process events")
  bakingActorS ! "BakeDonut"
  Thread.sleep(2000)

  println("\nStep 5: Using system.scheduler to send periodic events to actor")
  import scala.concurrent.duration._

  // initial delay of 1 seconds, and interval of 2
  systemS.scheduler.schedule(1.seconds, 2.seconds) {
    bakingActorS ! "BakeVanilla"
    Thread.sleep(1000)
  }

  StdIn.readLine()

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  val isTerminated = system.terminate()

  isTerminated.onComplete {
    case Success(_) => println("Successfully terminated actor system")
    case Failure(_) => println("Failed to terminate actor system")
  }

  val isTerminatedS = systemS.terminate()

  isTerminatedS.onComplete {
    case Success(_) => println("Successfully terminated actor system")
    case Failure(_) => println("Failed to terminate actor system")
  }

  println("\n//-----------------------------------------------------------------------------------------------------\n")

}
