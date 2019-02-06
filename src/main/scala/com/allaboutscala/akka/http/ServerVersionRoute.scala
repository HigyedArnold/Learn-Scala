package com.allaboutscala.akka.http

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

/**
  * Created by ArnoldHigyed on 06/02/2019
  */
class ServerVersion {

  def route(): Route = {
    path("server-version") {
      get {
        val serverVersion = "1.0.0.0"
        complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, serverVersion))
      }
    }
  }
}

