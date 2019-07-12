package com.aibuild.allaboutscala.akka.http

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

final case class AkkaHttpRestServer(app: String, version: String)

/**
  * Created by ArnoldHigyed on 14/02/2019
  */
trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  import spray.json._
  implicit val printer:      PrettyPrinter.type                 = PrettyPrinter
  implicit val serverFormat: RootJsonFormat[AkkaHttpRestServer] = jsonFormat2(AkkaHttpRestServer)
}
