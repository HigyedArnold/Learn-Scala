package chessproblem.entities

abstract class Movable(position: Position) {

  /**
    * BACKCHECK -> NEGATIVE to ASSURE, on false if fine
    * Validate a chess piece position.
    * @param pieces as list of chess pieces already on the chess table
    * @return boolean signalling if the position is conflicting or not
    */
  def validate(pieces: List[Movable]): Boolean = {
    (for {
      res <- pieces.map(piece => check(piece.position))
    } yield res)
      .fold(false)(_ || _)
  }

  /**
    * FRONTCHECK -> POSITIVE to ELIMINATE, on true is fine
    * Mark the unsafe positions by dropping them.
    * @param chessTable as list of available positions
    * @return as list of available safe positions
    */
  def mark(chessTable: List[Position]): List[Position] =
    chessTable.filter(check(_))
  // Don't use filter sequence, it is inefficient

  /**
    * Check if position is unsafe. Drop it if it is.
    * @param p as position
    * @return
    */
  def check(p: Position): Boolean

  override def toString: String =
    "(" + position.x + ", " + position.y + ")"

}