package com.aibuild.cats

import CoinFlipUtils._
import scala.annotation.tailrec
import scala.util.Random

/**
  * Created by ArnoldHigyed on 16/10/2019
  */
case class GameState(numFlips: Int, numCorrect: Int)

object CoinFlip extends App {

  val r = Random
  val s = GameState(0, 0)
  mainLoop(s, r, 1)

  @tailrec
  def mainLoop(gameState: GameState, random: Random, gamesPlayed: Int) {

    showPrompt()
    val userInput = getUserInput()

    // handle the result
    userInput match {
      case "H" | "T" => {
        val coinTossResult = tossCoin(random)
        val newNumFlips = gameState.numFlips + 1
        if (userInput == coinTossResult) {
          val newNumCorrect = gameState.numCorrect + 1
          val newGameState = gameState.copy(numFlips = newNumFlips, numCorrect = newNumCorrect)
          printGameState(printableFlipResult(coinTossResult), newGameState)
          mainLoop(newGameState, random, gamesPlayed)
        } else {
          val newGameState = gameState.copy(numFlips = newNumFlips)
          printGameState(printableFlipResult(coinTossResult), newGameState)
          mainLoop(newGameState, random, gamesPlayed)
        }
      }
      case "N" =>
        val newGameState = gameState.copy(numFlips = 0, numCorrect = 0)
        mainLoop(newGameState, r, gamesPlayed + 1)
      case _   => {
        printGameOver()
        printGameState(gameState)
        printGamesPlayed(gamesPlayed)
        // return out of the recursion here
      }
    }
  }

}