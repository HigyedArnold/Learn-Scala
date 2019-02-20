package chessproblem.entities

class Knight(pos: Position) extends Movable(pos) {

  override def name: String = "Knight"

  override def check(p: Position): Boolean =
  // self
    p == position ||
    (Math.abs(p.x - position.x) == 2 &&  Math.abs(p.y - position.y) == 1) ||
    (Math.abs(p.x - position.x) == 1 &&  Math.abs(p.y - position.y) == 2)

}