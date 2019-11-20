package com.aibuild.autoschema
import java.time.{LocalDate, LocalDateTime}
import java.time.format.DateTimeFormatter

//import org.coursera.autoschema.AutoSchema
//import json._

object AutoSchemaMain extends App {
//  val schemaSnap2 = AutoSchema.createPrettySchema[](0)
//  println(schemaSnap2)

  println()

//  val schemaSnap1 = Json.schema[]
//  println(schemaSnap1)
  println(TestEnum.z.id)
  val dateTime = LocalDateTime.now()
  val sqlDateTime = java.sql.Timestamp.valueOf(dateTime)
  println(List(1, 2, 3, 4).mkString("('", "', '", "')"))
  println(sqlDateTime)
  val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
  println(LocalDateTime.parse("2012-11-26 00:00:00.000", formatter))

  val map = scala.collection.mutable.Map(1 -> 1, 2 -> 2, 3 -> 3)
  println(map)
  map += (1 -> 4)
  println(map)

  val dateFrom = LocalDate.parse("2010-12-02")
  val dateTo   = LocalDate.parse("2010-12-09")

  val dates = Iterator
    .iterate(dateFrom)(_.plusDays(1))
    .takeWhile(date => date.isBefore(dateTo) || date.equals(dateTo))
    .toList.map(date => date -> 0).toMap

  println(dates)
}

object TestEnum extends Enumeration {
  val x: TestEnum.Value = Value(1)
  val y: TestEnum.Value = Value(2)
  val z: TestEnum.Value = Value(3)
}