package com.aibuild.cats

import cats.effect.IO

/**
  * Created by ArnoldHigyed on 18/10/2019
  */
object IOTest extends App {

  private def getLine: IO[String] = IO(scala.io.StdIn.readLine())
  private def putStrLn(s: String): IO[Unit] = IO(println(s))

  def forExpression: IO[Unit] =
    for {
      _         <- putStrLn("First name?")
      firstName <- getLine
      _         <- putStrLn(s"Last name?")
      lastName  <- getLine
      fNameUC = firstName.toUpperCase
      lNameUC = lastName.toUpperCase
      _ <- putStrLn(s"First: $fNameUC, Last: $lNameUC")
    } yield ()

  forExpression.attempt.unsafeRunSync.fold(
    e => println(e.getMessage),
    v => v
  )

  def recForExpression: IO[Unit] =
    for {
      _     <- putStrLn("Type something: ")
      input <- getLine
      _     <- putStrLn(s"You said '$input'.")
      _     <- if (input == "quit") IO(Unit) else recForExpression
    } yield ()

  recForExpression.attempt.unsafeRunSync.fold(
    e => println(e.getMessage),
    v => v
  )

}
