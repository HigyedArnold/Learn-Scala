package com.allaboutscala.scala

import com.typesafe.scalalogging.LazyLogging

/**
  * Created by ArnoldHigyed on 14/11/2018
  */
object Introduction extends App with LazyLogging {

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Hello World from Scala!")
  println("Command line arguments are: ")
  println(args.mkString(", "))

  logger.info("Hello World from Scala Logging!")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

}
