package chessproblem
import chessproblem.core.ChessPieceType._
import chessproblem.core.Util
import chessproblem.timer.Timer
import chessproblem.timer.Timer.TimerType

object Solver extends App {

  // Read config from text file -> TODO
  val m = 7
  val n = 7
  val pieces = List(dKing, dKing, aQueen, aQueen, cBishop, cBishop, eKnight)

  // Better performance if we start the search with the pieces sorted
  pieces.sorted
  val chessTable = Util.generateChessTable(m, n)
  solve

  def solve: Unit = {
    val started = Timer.start

    // recursive function TODO
    // BZL:

    Timer.print(Timer.stop(started), TimerType.MIL)
  }

}
