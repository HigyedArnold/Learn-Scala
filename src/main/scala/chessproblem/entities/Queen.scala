package chessproblem.entities

class Queen(x:Int, y: Int) extends Movable(x:Int, y: Int) {

  override def check(p: Position): Boolean =
    p.x == position.x ||
    p.y == position.y ||
    Math.abs(p.x - position.x) == Math.abs(p.y - position.y)

}