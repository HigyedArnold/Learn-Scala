package com.aibuild.chessproblem.entities

class King(pos: Position) extends ChessPiece(pos) {

  override def name: String = "King"

  override def check(p: Position): Boolean =
    // self
    p == position ||
      Math.abs(p.x - position.x) <= 1 && Math.abs(p.y - position.y) <= 1

}
