package com.aibuild.cats

/**
  * Created by ArnoldHigyed on 18/10/2019
  */
case class State[S, A](run: S => (S, A)) {

  def flatMap[B](f: A => State[S, B]): State[S, B] = State { s0 =>
    {
      val (s1, a) = run(s0)
      f(a).run(s1)
    }
  }

//  def map[B](f: A => B): State[S, B] = flatMap(a => State.point(f(a)))

  def map[B](f: A => B): State[S, B] = State { s0 =>
    {
      val (s1, a) = run(s0)
      (s1, f(a))
    }
  }
}

object State {
  def point[S, A](v: A): State[S, A] = {
    State(s => (s, v))
  }
}
