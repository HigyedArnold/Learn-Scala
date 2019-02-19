package chessproblem.entities

class Rook(x:Int, y: Int) extends Movable(x:Int, y: Int) {

  override def check(p: Position): Boolean =
    p.x == position.x ||
    p.y == position.y

}