package akka.http

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

final case class AkkaHttpRestServer(app: String, version: String)

/**
  * Created by ArnoldHigyed on 14/02/2019
  */
trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  import spray.json._
  implicit val printer = PrettyPrinter
  implicit val serverFormat = jsonFormat2(AkkaHttpRestServer)
}