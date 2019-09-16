package com.aibuild.cats

import scala.util.Random
import scala.io.StdIn.readLine

/**
  * Created by ArnoldHigyed on 16/10/2019
  */
object CoinFlipUtils {

  def showPrompt(): Unit = { print("\n(h)eads, (t)ails, or (n)ew game, (q)uit: ") }

  def getUserInput(): String = readLine.trim.toUpperCase

  def printableFlipResult(flip: String): String = flip match {
    case "H" => "Heads"
    case "T" => "Tails"
  }

  def printGameState(printableFlipResult: String, gameState: GameState): Unit = {
    print(s"Flip was $printableFlipResult. ")
    printGameState(gameState)
  }

  def printGameState(gameState: GameState): Unit = {
    println(s"#Flips: ${gameState.numFlips}, #Correct: ${gameState.numCorrect}")
  }

  def printGameOver(): Unit = println("\n=== GAME OVER ===")

  def printGamesPlayed(gamesPlayed: Int): Unit = println(s"#Games Played: $gamesPlayed")

  // returns "H" for heads, "T" for tails
  def tossCoin(r: Random): String = {
    val i = r.nextInt(2)
    i match {
      case 0 => "H"
      case 1 => "T"
    }
  }

}