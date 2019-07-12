package com.aibuild.chessproblem.core
import com.aibuild.chessproblem.core.ChessPieceType.ChessPieceType
import com.aibuild.chessproblem.entities.Position

object Util {

  def generateChessTable(x: Int, y: Int): Vector[Position] =
    (for {
      a <- 1 to x
      b <- 1 to y
    } yield Position(a, b)).toVector

  def uniqueCoef(pieces: Vector[ChessPieceType]): Int = {
    pieces
      .groupBy(l => l)
      .map(t => (t._1, t._2.length))
      .values
      .product
  }

}

object ChessPieceType extends Enumeration {
  type ChessPieceType = Value
  val King, Queen, Rook, Bishop, Knight = Value
}
