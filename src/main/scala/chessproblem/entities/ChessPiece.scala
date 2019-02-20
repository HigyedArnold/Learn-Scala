package chessproblem.entities

abstract class ChessPiece(pos: Position) {

  val position: Position = pos

  def name: String

  /**
    * BACKCHECK -> NEGATIVE to ASSURE, on false if fine
    * Validate a chess piece position.
    * @param pieces a vector of chess pieces already on the chess table
    * @return boolean signalling if the position is conflicting or not
    */
  def validate(pieces: Vector[ChessPiece]): Boolean = {
    if (pieces.isEmpty) {
      false
    }
    else {
      (for {
        res <- pieces.map((piece: ChessPiece) => check(piece.position))
      } yield res)
        .fold(false)(_ || _)
    }
  }

  /**
    * FRONTCHECK -> POSITIVE to ELIMINATE, on true is fine
    * Mark the unsafe positions by dropping them.
    * @param chessTable a vector of available positions
    * @return a vector of available safe positions
    */
  def mark(chessTable: Vector[Position]): Vector[Position] =
    chessTable.filterNot(check(_))
  // Don't use filter sequence, it is inefficient

  /**
    * Check if position is unsafe. Drop it if it is.
    * @param p as position
    * @return
    */
  def check(p: Position): Boolean

  override def toString: String =
    name + ": (" + position.x + ", " + position.y + ")"

}