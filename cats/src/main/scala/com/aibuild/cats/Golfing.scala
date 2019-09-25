package com.aibuild.cats
import com.aibuild.cats.monads.State

/**
  * Created by ArnoldHigyed on 18/10/2019
  */
object Golfing extends App {
  case class GolfState(distance: Int)

  def swing(distance: Int): State[GolfState, Int] = State { s: GolfState =>
    val newAmount = s.distance + distance
    (GolfState(newAmount), newAmount)
  }

//  Desugared code
//
//  val stateWithNewDistance: State[GolfState, Int] =
//    swing(20)
//      .flatMap(_ => swing(15)
//        .flatMap(_ => swing(5)
//          .map(totalDistance => totalDistance)
//        )
//      )

  val stateWithNewDistance: State[GolfState, Int] = for {
    _             <- swing(20)
    _             <- swing(15)
    totalDistance <- swing(5)
  } yield totalDistance

  val beginningState = GolfState(0)
  val result: (GolfState, Int) = stateWithNewDistance.run(beginningState)

  println(s"GolfState: ${result._1}")
  println(s"Total Distance: ${result._2}")
}
