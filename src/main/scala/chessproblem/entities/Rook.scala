package chessproblem.entities

class Rook(position: Position) extends Movable(position: Position) {

  override def check(p: Position): Boolean =
    p.x == position.x ||
    p.y == position.y

}