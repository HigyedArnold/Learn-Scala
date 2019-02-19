package chessproblem.entities

class Queen(position: Position) extends Movable(position: Position) {

  override def check(p: Position): Boolean =
    p.x == position.x ||
    p.y == position.y ||
    Math.abs(p.x - position.x) == Math.abs(p.y - position.y)

}