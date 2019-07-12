package com.aibuild.chessproblem.timer
import com.aibuild.chessproblem.timer.Timer.TimerType.TimerType

object Timer {

  object TimerType extends Enumeration {
    type TimerType = Value
    val SEC, MIL, MIC = Value
  }

  def start: Long =
    System.nanoTime()

  def stop(started: Long): Long =
    System.nanoTime() - started

  def print(time: Long, ttype: TimerType): Unit = {
    val ftime = ttype match {
      case TimerType.SEC => time / 1000000000
      case TimerType.MIL => time / 1000000
      case TimerType.MIC => time / 1000
    }
    println(ftime + " " + ttype + " elapsed...")
  }

}
