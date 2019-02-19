package chessproblem.core
import chessproblem.entities.Position

object Util {

  def generateChessTable(x: Int, y: Int): List[Position] =
    (for {
    a <- 1 to x
    b <- 1 to y
    } yield(Position(a, b))).toList

}

object ChessPieceType extends Enumeration {
  type ChessPieceType = Value
  val dKing, aQueen, bRook, cBishop, eKnight = Value
}