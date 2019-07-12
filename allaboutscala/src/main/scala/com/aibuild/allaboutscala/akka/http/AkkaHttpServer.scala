package com.aibuild.allaboutscala.akka.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn
import scala.util.{Failure, Success}

/**
  * Created by ArnoldHigyed on 05/02/2019
  */
object AkkaHttpServer extends App with LazyLogging {

  implicit val system:       ActorSystem              = ActorSystem("akka-http-rest-server")
  implicit val materializer: ActorMaterializer        = ActorMaterializer()
  implicit val ec:           ExecutionContextExecutor = system.dispatcher

  // these should ideally be in some configuration file
  val host = "127.0.0.1"
  val port = 8080

  // routes
  val serverUpRoute: Route = get {
    complete("Akka HTTP Server is UP.")
  }

  val serverVersion             = new ServerVersion()
  val serverVersionRoute        = serverVersion.route()
  val serverVersionRouteAsJson  = serverVersion.routeAsJsonEncoding()
  val serverVersionJsonEncoding = serverVersion.routeAsJsonEncoding()

  val routes: Route = serverVersionRoute ~ serverVersionRouteAsJson ~ serverVersionJsonEncoding ~ serverUpRoute

  val httpServerFuture = Http().bindAndHandle(routes, host, port)
  httpServerFuture.onComplete {
    case Success(binding) =>
      logger.info(s"Akka Http Server is UP and is bound to ${binding.localAddress}")

    case Failure(e) =>
      logger.error(s"Akka Http server failed to start", e)
      system.terminate()
  }

  StdIn.readLine() // let it run until user presses return
  httpServerFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done
}
