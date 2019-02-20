package chessproblem.entities

class Rook(pos: Position) extends Movable(pos) {

  override def name: String = "Rook"

  override def check(p: Position): Boolean =
  // self mark included
    p.x == position.x ||
    p.y == position.y

}