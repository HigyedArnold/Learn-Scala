package com.aibuild.fpincity.adt

final abstract class Idle
final abstract class Moving

sealed trait Command[A, B]

object Command {
  case class Face(dir: Direction) extends Command[Idle, Idle]
  case object Start extends Command[Idle,   Moving]
  case object Stop  extends Command[Moving, Idle]

  case class Chain[A, B, C](
    cmd1: Command[A, B],
    cmd2: Command[B, C]
  ) extends Command[A, C]

}
