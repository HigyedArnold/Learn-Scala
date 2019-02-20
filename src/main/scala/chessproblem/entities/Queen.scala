package chessproblem.entities

class Queen(pos: Position) extends Movable(pos) {

  override def name: String = "Queen"

  override def check(p: Position): Boolean =
  // self mark included
    p.x == position.x ||
    p.y == position.y ||
    Math.abs(p.x - position.x) == Math.abs(p.y - position.y)

}