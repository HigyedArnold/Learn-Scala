package com.aibuild.chessproblem.entities

class Bishop(pos: Position) extends ChessPiece(pos) {

  override def name: String = "Bishop"

  override def check(p: Position): Boolean =
    // self
    p == position ||
      Math.abs(p.x - position.x) == Math.abs(p.y - position.y)

}
