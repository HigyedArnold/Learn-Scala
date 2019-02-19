package chessproblem.entities

class Bishop(x:Int, y: Int) extends Movable(x:Int, y: Int) {

  override def check(p: Position): Boolean =
    (Math.abs(p.x - position.x) == 2 &&  Math.abs(p.y - position.y) == 1) ||
    (Math.abs(p.x - position.x) == 1 &&  Math.abs(p.y - position.y) == 2)

}