package com.aibuild.autoschema
import com.aibuild.autoschema.ServiceEnum.ServiceEnum

trait Service {
  def init(id: ServiceEnum): String
}

object ServiceEnum extends Enumeration {
  type ServiceEnum = Value
  val S, M, L = Value
}

class DBService extends Service {
  override def init(id: ServiceEnum): String = id.toString
}

object EnumParameter extends App {
  val dbService = new DBService
  println(dbService.init(ServiceEnum.S))
}
