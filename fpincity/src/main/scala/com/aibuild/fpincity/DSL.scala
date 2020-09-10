package com.aibuild.fpincity

object DSL {

  implicit class Compose[A, B](cmd1: Command[A, B]) {

    def ~>[C](cmd2: Command[B, C]): Command[A, C] =
      Command.Chain(cmd1, cmd2)
  }

  val start: Command[Idle,   Moving] = Command.Start
  val stop:  Command[Moving, Idle]   = Command.Stop
  def face(dir: Direction): Command[Idle, Idle] = Command.Face(dir)

  val north: Direction = Direction.North
  val east:  Direction = Direction.East
  val south: Direction = Direction.South
  val west:  Direction = Direction.West

  def move(d: Direction): Command[Idle, Idle] =
    face(d) ~> start ~> stop

  def movingLabel(cmd: Command[Moving, _]): String =
    cmd match {
      case Command.Stop => "stop"
      case _: Command.Chain[Moving, _, _] => "chain"
    }

  def label(d: Direction): Unit =
    d match {
      case Direction.East  =>
      case Direction.North =>
      case Direction.South =>
      case Direction.West  =>
    }

}
