package com.aibuild.cats

/**
  * Created by ArnoldHigyed on 06/02/2019
  */
/** TYPE CLASS */
sealed trait Json
final case class JsObject(get: Map[String, Json]) extends Json
final case class JsString(get: String) extends Json
final case class JsNumber(get: Double) extends Json
case object JsNull extends Json

final case class Person(name: String, email: String)

trait JsonWriter[A] {
  def write(value: A): Json
}

/** TYPE CLASS INSTANCE */

object JsonWriterInstances {

  implicit def stringWriter: JsonWriter[String] =
    new JsonWriter[String] {

      def write(value: String): Json =
        JsString(value)
    }

  implicit def doubleWriter: JsonWriter[Double] =
    new JsonWriter[Double] {

      def write(value: Double): Json =
        JsNumber(value)
    }

  implicit def personWriter: JsonWriter[Person] =
    new JsonWriter[Person] {

      def write(value: Person): Json =
        JsObject(
          Map(
            "name"  -> JsString(value.name),
            "email" -> JsString(value.email)
          )
        )
    }

  implicit def optionWriter[A](implicit writer: JsonWriter[A]): JsonWriter[Option[A]] =
    new JsonWriter[Option[A]] {

      def write(option: Option[A]): Json =
        option match {
          case Some(aValue) => writer.write(aValue)
          case None         => JsNull
        }
    }

}

/** TYPE CLASS INTERFACE */
/** INTERFACE OBJECT */
object Json {

  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json =
    w.write(value)
}

/** INTERFACE SYNTAX */
object JsonSyntax {
  implicit class JsonWriterOps[A](value: A) {

    def toJson(implicit w: JsonWriter[A]): Json =
      w.write(value)
  }
}

/** MAIN */
object Introduction_TypeClass extends App {

  import JsonSyntax._
  import JsonWriterInstances._

  println(Json.toJson(Person("Dave", "dave@example.com"))(personWriter))

  println(Person("Dave", "dave@example.com").toJson)
  println("Test".toJson)
  println(2.0.toJson)
  println(Option("A string").toJson)

  println(implicitly[JsonWriter[Person]]) // -> implicitly is a good fallback for debugging purposes
  println(implicitly[JsonWriter[String]]) // -> implicitly is a good fallback for debugging purposes
  println(implicitly[JsonWriter[Double]]) // -> implicitly is a good fallback for debugging purposes

}
