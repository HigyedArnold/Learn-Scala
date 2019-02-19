package chessproblem
import chessproblem.core.ChessPieceType._
import chessproblem.core.Util
import chessproblem.entities._
import chessproblem.timer.Timer
import chessproblem.timer.Timer.TimerType

object Solver extends App {

  solve

  def solve: Unit = {
    // Read config from text file -> TODO
    val m = 7
    val n = 7
    val pieces = List(dKing, dKing, aQueen, aQueen, cBishop, cBishop, eKnight)

    // Better performance if we start the search with the pieces sorted
    pieces.sorted

    val chessTable = Util.generateChessTable(m, n)

    val started = Timer.start

    // recursive function
    // BZL:
    // -> put a piece on -> validate position
    //                   -> mark impacted positions
    // -> if any of the pieces are not validating, mark the position
    // -> don't put a piece on
    //
    // Stop condition: no postions left or no pieces left

    rec(chessTable, 0, pieces, List.empty[Movable])

    Timer.print(Timer.stop(started), TimerType.MIL)
  }

  def rec(chessTable: List[Position], chessIndex: Int, pieces: List[ChessPieceType], onPieces: List[Movable]): Unit = {
    if(pieces.isEmpty)
      // finish
    val piece = pieces.head match {
      case aQueen => new Queen(chessTable.head)
      case bRook => new Rook(chessTable.head)
      case cBishop => new Bishop(chessTable.head)
      case dKing => new King(chessTable.head)
      case eKnight => new Knight(chessTable.head)
    }

    if (!piece.validate(onPieces)) {

      piece +: onPieces
      rec(piece.mark(chessTable.tail), 0, )
    }
  }

}
