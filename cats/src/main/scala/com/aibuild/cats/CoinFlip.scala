package com.aibuild.cats

import cats.effect.IO
import com.aibuild.cats.CoinFlipUtils._

import scala.annotation.tailrec
import scala.util.Random

/**
  * Created by ArnoldHigyed on 16/10/2019
  */
case class GameState(numFlips: Int, numCorrect: Int)

object CoinFlip extends App {

  val r = Random
  val s = GameState(0, 0)
//  mainLoop(s, r, 1)
  mainLoopIO(s, r).unsafeRunSync()

  def mainLoopIO(gameState: GameState, random: Random): IO[Unit] =
    for {
      _         <- IO { showPrompt() }
      userInput <- IO { getUserInput() }
      _ <- if (userInput == "H" || userInput == "T") for {
            // this first line is a hack;
            // a for-expression must begin with a generator
            _ <- IO { println("you said H or T") }
            coinTossResult = tossCoin(random)
            newNumFlips    = gameState.numFlips + 1
            newGameState = createNewGameState(
              userInput,
              coinTossResult,
              gameState,
              random,
              newNumFlips
            )
            _ <- IO {
                  printGameState(
                    printableFlipResult(coinTossResult),
                    newGameState
                  )
                }
            _ <- mainLoopIO(newGameState, random)
          } yield Unit
          else
            for {
              _ <- IO { println("did not enter H or T") }
              _ <- IO { printGameOver() }
              _ <- IO { printGameState(gameState) }
            } yield Unit
    } yield Unit

  private def createNewGameState(
    userInput:      String,
    coinTossResult: String,
    gameState:      GameState,
    random:         Random,
    newNumFlips:    Int
  ): GameState = {
    if (userInput == coinTossResult) {
      val newNumCorrect = gameState.numCorrect + 1
      val newGameState  = gameState.copy(numFlips = newNumFlips, numCorrect = newNumCorrect)
      newGameState
    }
    else {
      val newGameState = gameState.copy(numFlips = newNumFlips)
      newGameState
    }
  }

  @tailrec
  def mainLoop(gameState: GameState, random: Random, gamesPlayed: Int) {

    showPrompt()
    val userInput = getUserInput()

    // handle the result
    userInput match {
      case "H" | "T" =>
        val coinTossResult = tossCoin(random)
        val newNumFlips    = gameState.numFlips + 1
        if (userInput == coinTossResult) {
          val newNumCorrect = gameState.numCorrect + 1
          val newGameState  = gameState.copy(numFlips = newNumFlips, numCorrect = newNumCorrect)
          printGameState(printableFlipResult(coinTossResult), newGameState)
          mainLoop(newGameState,                              random, gamesPlayed)
        }
        else {
          val newGameState = gameState.copy(numFlips = newNumFlips)
          printGameState(printableFlipResult(coinTossResult), newGameState)
          mainLoop(newGameState,                              random, gamesPlayed)
        }
      case "N" =>
        val newGameState = gameState.copy(numFlips = 0, numCorrect = 0)
        mainLoop(newGameState, r, gamesPlayed + 1)
      case _ =>
        printGameOver()
        printGameState(gameState)
        printGamesPlayed(gamesPlayed)
    }
  }

}
