package com.aibuild.chessproblem.entities

/**
  * A position on the chess table.
  * Precondition: x and y must be bigger than 0 (index starts from 1).
  */
case class Position(x: Int, y: Int) {
  require(x > 0)
  require(y > 0)
}
