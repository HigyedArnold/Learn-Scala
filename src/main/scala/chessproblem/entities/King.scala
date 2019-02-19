package chessproblem.entities

class King(x:Int, y: Int) extends Movable(x:Int, y: Int) {

  override def check(p: Position): Boolean =
    p.x == position.x ||
    p.y == position.y ||
    Math.abs(p.x - position.x) <= 1 && Math.abs(p.y - position.y) <= 1

}