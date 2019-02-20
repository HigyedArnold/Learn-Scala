package chessproblem.core
import chessproblem.entities.Position

object Util {

  def generateChessTable(x: Int, y: Int): Vector[Position] =
    (for {
    a <- 1 to x
    b <- 1 to y
    } yield(Position(a, b))).toVector

}

object ChessPieceType extends Enumeration {
  type ChessPieceType = Value
  val King, Queen, Rook, Bishop, Knight = Value
}