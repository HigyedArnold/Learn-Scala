package com.aibuild.allaboutscala.akka.actors

import akka.actor.SupervisorStrategy.{Escalate, Restart}
import akka.actor.{Actor,                       ActorLogging, ActorRef, ActorSystem, OneForOneStrategy, Props, SupervisorStrategy}
import akka.pattern._
import akka.util.Timeout
import com.aibuild.allaboutscala.akka.actors.AkkaActor.DonutStoreProtocol.{CheckStock, Info, WorkerFailedException}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.{Failure, Success}

/**
  * Created by ArnoldHigyed on 19/11/2018
  */
object AkkaActor extends App {

//  println("\n//-----------------------------------------------------------------------------------------------------\n")
//
//  println("Step 1: create an actor system")
//  val system = ActorSystem("DonutStoreActorSystem")
//
//  println("\nStep 2: close the actor system")
//  val isTerminated = system.terminate()
//
//  println("\nStep 3: Check the status of the actor system")
//  isTerminated.onComplete {
//    case Success(result) => println("Successfully terminated actor system")
//    case Failure(e)     => println("Failed to terminate actor system")
//  }
//
//  Thread.sleep(5000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  implicit val timeout: Timeout = Timeout(5 second)

  println("Step 1: create an actor system")
  val system = ActorSystem("DonutStoreActorSystem")

  println("\nStep 2: Define the message passing protocol for our DonutStoreActor")

  object DonutStoreProtocol {
    case class Info(name:                   String)
    case class CheckStock(name:             String)
    case class WorkerFailedException(error: String) extends Exception(error)
  }

  println("\nStep 3: Define DonutInfoActor")

  class DonutInfoActor extends Actor with ActorLogging {

    def receive: PartialFunction[Any, Unit] = {
      case Info(name) if name == "vanilla" =>
        log.info(s"Found valid $name donut")
        sender ! true

      case Info(name) =>
        log.info(s"$name donut is not supported")
        sender ! false
    }
  }

  println("\nStep 4: Create DonutInfoActor")
  val donutInfoActor = system.actorOf(Props[DonutInfoActor], name = "DonutInfoActor")

  println("\nStep 5: Akka Tell Pattern")
//  donutInfoActor ! Info("vanilla")

//  val vanillaDonutFound = donutInfoActor ? Info("vanilla")
  val vanillaDonutFound: Future[Boolean] = (donutInfoActor ? Info("vanilla")).mapTo[Boolean]

  for {
    found <- vanillaDonutFound
  } yield println(s"Vanilla donut found = $found")

  val glazedDonutFound = donutInfoActor ? Info("glazed")
  for {
    found <- glazedDonutFound
  } yield println(s"Glazed donut found = $found")

  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("\nStep 3: Create DonutStockActor")

  class DonutStockActor extends Actor with ActorLogging {

    def receive: PartialFunction[Any, Unit] = {
      case CheckStock(name) =>
        log.info(s"Checking stock for $name donut")
        findStock(name).pipeTo(sender)
    }

    def findStock(name: String): Future[Int] = Future {
      // assume a long running database operation to find stock for the given donut
      100
    }
  }

  println("\nStep 4: Create DonutStockActor")
  val donutStockActor = system.actorOf(Props[DonutStockActor], name = "DonutStockActor")
//  system.actorSelection("/user/DonutInfoActor") ! Info("chocolate")
//  system.actorSelection("/user/*") ! Info("vanilla and chocolate")
  val vanillaDonutStock: Future[Int] = (donutStockActor ? CheckStock("vanilla")).mapTo[Int]

  for {
    found <- vanillaDonutStock
  } yield println(s"Vanilla donut stock = $found")

  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("\nStep 3: Define a BakingActor and a DonutInfoActor")

  class BakingActor extends Actor with ActorLogging {

    override def preStart(): Unit = log.info("prestart")

    override def postStop(): Unit = log.info("postStop")

    override def preRestart(reason: Throwable, message: Option[Any]): Unit = log.info("preRestart")

    override def postRestart(reason: Throwable): Unit = log.info("postRestart")

    def receive: PartialFunction[Any, Unit] = {
      case Info(name) =>
        log.info(s"BakingActor baking $name donut")
    }
  }

  class DonutInfoActor1 extends Actor with ActorLogging {

    override def preStart(): Unit = log.info("prestart")

    override def postStop(): Unit = log.info("postStop")

    override def preRestart(reason: Throwable, message: Option[Any]): Unit = log.info("preRestart")

    override def postRestart(reason: Throwable): Unit = log.info("postRestart")

    val bakingActor: ActorRef = context.actorOf(Props[BakingActor], name = "BakingActor")

    def receive: PartialFunction[Any, Unit] = {
      case msg @ Info(name) =>
        log.info(s"Found $name donut")
        bakingActor forward msg
    }
  }

  println("\nStep 4: Create DonutInfoActor1")
  val donutInfoActor1 = system.actorOf(Props[DonutInfoActor1], name = "DonutInfoActor1")

  println("\nStep 5: Akka Tell Pattern")
//  donutInfoActor1 ! PoisonPill
  donutInfoActor1 ! Info("vanilla")

  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("\nStep 3: Create DonutStockActor")

  class DonutStockActor1 extends Actor with ActorLogging {

    override def supervisorStrategy: SupervisorStrategy =
      OneForOneStrategy(maxNrOfRetries = 3, withinTimeRange = 1 seconds) {
        case _: WorkerFailedException =>
          log.error("Worker failed exception, will restart.")
          Restart

        case _: Exception =>
          log.error("Worker failed, will need to escalate up the hierarchy")
          Escalate
      }

    val workerActor: ActorRef = context.actorOf(Props[DonutStockWorkerActor], name = "DonutStockWorkerActor")

    def receive: PartialFunction[Any, Unit] = {
      case checkStock @ CheckStock(name) =>
        log.info(s"Checking stock for $name donut")
        workerActor forward checkStock
    }
  }

  println("\nStep 4: Worker Actor called DonutStockWorkerActor")

  class DonutStockWorkerActor extends Actor with ActorLogging {

    @throws[Exception](classOf[Exception])
    override def postRestart(reason: Throwable): Unit = {
      log.info(s"restarting ${self.path.name} because of $reason")
    }

    def receive: PartialFunction[Any, Unit] = {
      case CheckStock(name) =>
        findStock(name)
        context.stop(self)
    }

    def findStock(name: String): Int = {
      log.info(s"Finding stock for donut = $name")
      100
//      throw new IllegalStateException("boom") // Will Escalate the exception up the hierarchy
//      throw new WorkerFailedException("boom") // Will Restart DonutStockWorkerActor
    }
  }

  println("\nStep 5: Define DonutStockActor")
  val donutStockActor1 = system.actorOf(Props[DonutStockActor1], name = "DonutStockActor1")

  val vanillaDonutStock1: Future[Int] = (donutStockActor1 ? CheckStock("vanilla")).mapTo[Int]
  for {
    found <- vanillaDonutStock1
  } yield println(s"Vanilla donut stock = $found")

  Thread.sleep(3000)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  val isTerminated = system.terminate()

  isTerminated.onComplete {
    case Success(_) => println("Successfully terminated actor system")
    case Failure(_) => println("Failed to terminate actor system")
  }

  println("\n//-----------------------------------------------------------------------------------------------------\n")

}
