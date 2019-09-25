package com.aibuild.cats.monads

/**
  * Created by ArnoldHigyed on 25/10/2019
  */
case class State[S, A](run: S => (S, A)) {

  def flatMap[B](g: A => State[S, B]): State[S, B] = State { s0: S =>
    val (s1, a) = run(s0)
    val s2  = g(a)
    val rez = s2.run(s1)
    rez
  }

  def map[B](f: A => B): State[S, B] = flatMap(a => State.point(f(a)))
}

object State {
  def point[S, A](v: A): State[S, A] = State(run = s => (s, v))
}
