package com.aibuild.allaboutscala.akka.routers

import akka.actor.SupervisorStrategy.{Escalate, Restart}
import akka.actor.{Actor,                       ActorLogging, ActorRef, ActorSystem, OneForOneStrategy, Props, SupervisorStrategy}
import akka.pattern._
import akka.routing._
import akka.util.Timeout
import com.aibuild.allaboutscala.akka.actors.AkkaActor.DonutStoreProtocol.{CheckStock, WorkerFailedException}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.{Failure, Success}

/**
  * Created by ArnoldHigyed on 19/11/2018
  */
object Routers extends App {

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  implicit val timeout: Timeout = Timeout(5 second)

  println("Step 1: Create an actor system")
  val system = ActorSystem("DonutStoreActorSystem")

  println("\nStep 2: Define the message passing protocol for our DonutStoreActor")

  object DonutStoreProtocol {
    case class Info(name: String)

    case class CheckStock(name: String)

    case class WorkerFailedException(error: String) extends Exception(error)
  }

  println("\nStep 3: Create DonutStockActor")

  class DonutStockActor extends Actor with ActorLogging {

    override def supervisorStrategy: SupervisorStrategy =
      OneForOneStrategy(maxNrOfRetries = 3, withinTimeRange = 5 seconds) {
        case _: WorkerFailedException =>
          log.error("Worker failed exception, will restart.")
          Restart

        case _: Exception =>
          log.error("Worker failed, will need to escalate up the hierarchy")
          Escalate
      }

    // We will not create one worker actor.
    // val workerActor = context.actorOf(Props[DonutStockWorkerActor], name = "DonutStockWorkerActor")

    // We are using a resizable RoundRobinPool.
    val resizer = DefaultResizer(lowerBound = 5, upperBound = 10)

//    val props = RoundRobinPool(5, Some(resizer), supervisorStrategy = supervisorStrategy)
//      .props(Props[DonutStockWorkerActor])

//    val props = ScatterGatherFirstCompletedPool(
//      nrOfInstances = 5,
//      resizer = Some(resizer),
//      supervisorStrategy = supervisorStrategy,
//      within = 5 seconds
//    ).props(Props[DonutStockWorkerActor])

//    val props = TailChoppingPool(
//      nrOfInstances = 5,
//      resizer = Some(resizer),
//      within = 5 seconds,
//      interval = 10 millis,
//      supervisorStrategy = supervisorStrategy
//    ).props(Props[DonutStockWorkerActor])

    val props: Props = BroadcastPool(
      nrOfInstances      = 5,
      resizer            = None,
      supervisorStrategy = supervisorStrategy
    ).props(Props[DonutStockWorkerActor])

    val donutStockWorkerRouterPool: ActorRef = context.actorOf(props, "DonutStockWorkerRouter")

    def receive: PartialFunction[Any, Unit] = {
      case checkStock @ CheckStock(name) =>
        log.info(s"Checking stock for $name donut")
        donutStockWorkerRouterPool forward checkStock
    }
  }

  println("\ntep 4: Worker Actor called DonutStockWorkerActor")

  class DonutStockWorkerActor extends Actor with ActorLogging {

    override def postRestart(reason: Throwable): Unit = {
      log.info(s"restarting ${self.path.name} because of $reason")
    }

    def receive: PartialFunction[Any, Unit] = {
      case CheckStock(name) =>
        sender ! findStock(name)
    }

    def findStock(name: String): Int = {
      log.info(s"Finding stock for donut = $name, thread = ${Thread.currentThread().getId}")
      100
    }
  }

  println("\nStep 5: Define DonutStockActor")
  val donutStockActor = system.actorOf(Props[DonutStockActor], name = "DonutStockActor")

  val vanillaStockRequests = (1 to 10).map(_ => (donutStockActor ? CheckStock("vanilla")).mapTo[Int])
  for {
    results <- Future.sequence(vanillaStockRequests)
  } yield println(s"vanilla stock results = $results")

  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  val isTerminated = system.terminate()

  isTerminated.onComplete {
    case Success(_) => println("Successfully terminated actor system")
    case Failure(_) => println("Failed to terminate actor system")
  }

  println("\n//-----------------------------------------------------------------------------------------------------\n")

}
