package akka.dispatchers

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.dispatchers.Dispatchers.DonutStoreProtocol.{DonutStockRequest, StockRequest}
import akka.util.Timeout

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

/**
  * Created by ArnoldHigyed on 19/11/2018
  */
object Dispatchers extends App {

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: Create an actor system")
  val system = ActorSystem("DonutStoreActorSystem")

  println("\nStep 2: Akka default dispatcher config")
  val defaultDispatcherConfig = system.settings.config.getConfig("akka.actor.default-dispatcher")
  println(s"akka.actor.default-dispatcher = $defaultDispatcherConfig")

  println("\nStep 3: Akka default dispatcher type")
  val dispatcherType = defaultDispatcherConfig.getString("type")
  println(s"$dispatcherType")

  println("\nStep 4: Akka default dispatcher throughput")
  val dispatcherThroughput = defaultDispatcherConfig.getString("throughput")
  println(s"$dispatcherThroughput")

  println("\nStep 5: Akka default dispatcher minimum parallelism")
  val dispatcherParallelismMin = defaultDispatcherConfig.getInt("fork-join-executor.parallelism-min")
  println(s"$dispatcherParallelismMin")

  println("\nStep 6: Akka default dispatcher maximum parallelism")
  val dispatcherParallelismMax = defaultDispatcherConfig.getInt("fork-join-executor.parallelism-max")
  println(s"$dispatcherParallelismMax")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  val system1 = ActorSystem("DonutStoreActorSystem")

  println("Step 3: Lookup our fixed-thread-pool dispatcher from application.conf")
  import akka.pattern._
  implicit val timeout = Timeout(1, TimeUnit.MINUTES)
  implicit val executionContext = system1.dispatchers.lookup("resizable-thread-pool")

  println("\nStep 4: Create protocol")
  object DonutStoreProtocol {
    case class StockRequest(name: String, clientId: Int)

    trait Result
    case class DonutStockRequest(quantity: Int) extends Result
    case class DonutFailure(msg: String) extends Result
  }

  println("\nStep 5: Create DonutStockRequestActor")
  class DonutStockRequestActor extends Actor with ActorLogging {
    val randomStock = scala.util.Random
    def receive = {
      case StockRequest(name, clientId) =>
        log.info(s"CHECKING: donut stock for name = $name, clientId = $clientId")
        Thread.sleep(5000)
        log.info(s"FINISHED: donut stock for name = $name, clientId = $clientId")
        sender() ! DonutStockRequest(randomStock.nextInt(100))
    }
  }

  println("\nStep 6: Create 10 requests using pool-size = 10")
  val clientRequests = (1 to 10).map(i => StockRequest(s"vanilla donut", i))
  val futures = clientRequests.map{ stock =>
    val actorRef = system1
      .actorOf(Props[DonutStockRequestActor]
        .withDispatcher("resizable-thread-pool"))
    (actorRef ? stock).mapTo[DonutStockRequest]
  }
  val results = Await.result(Future.sequence(futures), 1 minute)
  results.foreach(println(_))

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  val isTerminated = system.terminate()

  isTerminated.onComplete {
    case Success(result) => println("Successfully terminated actor system")
    case Failure(e)     => println("Failed to terminate actor system")
  }

  val isTerminated1 = system1.terminate()

  isTerminated1.onComplete {
    case Success(result) => println("Successfully terminated actor system")
    case Failure(e)     => println("Failed to terminate actor system")
  }

  println("\n//-----------------------------------------------------------------------------------------------------\n")

}
