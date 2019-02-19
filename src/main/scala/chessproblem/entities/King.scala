package chessproblem.entities

class King(position: Position) extends Movable(position: Position) {

  override def check(p: Position): Boolean =
    p.x == position.x ||
    p.y == position.y ||
    Math.abs(p.x - position.x) <= 1 && Math.abs(p.y - position.y) <= 1

}