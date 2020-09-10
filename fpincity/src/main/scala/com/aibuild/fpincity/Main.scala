package com.aibuild.fpincity

import com.aibuild.fpincity.DSL._

object Main extends App {
  println(move(east) ~> move(west))
//  println(start ~> start) // Can't anymore, compiler enforces
//  println(stop ~> stop) // Can't anymore, compiler enforces
//  println(start ~> face(north)) // Can't anymore, compiler enforces
  println(face(east) ~> start ~> stop)

  movingLabel(stop)
//  movingLabel(start) // Can't anymore, compiler enforces
  movingLabel(stop ~> start ~> stop)
}
