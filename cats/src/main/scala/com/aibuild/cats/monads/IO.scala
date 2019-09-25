package com.aibuild.cats.monads

/**
  * Created by ArnoldHigyed on 25/10/2019
  */
class IO[A] private (returnValue: => A) {

  def run: A = returnValue

  def flatMap[B](f: A => IO[B]): IO[B] = IO(f(run).run)

  def map[B](f: A => B): IO[B] = flatMap(a => IO(f(a)))

}

object IO {
  def apply[A](a: => A): IO[A] = new IO(a)
}
