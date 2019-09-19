package com.aibuild.cats

class MIO[A] private (block: => A){

  def run: A = block

  def map[B](f: A => B): MIO[B] = flatMap(a => MIO(f(a)))

  def flatMap[B](f: A => MIO[B]): MIO[B] = MIO(f(run).run)

}

object MIO {
  def apply[A](block: => A): MIO[A] = new MIO(block)
}

object IOMain extends App {

  val result: MIO[Unit] = MIO(println("Hello Monad World!"))
  println(result)
  result.run

  val forResult = for {
  _ <- MIO(println("Hello"))
  _ <- MIO(println("Monad"))
  _ <- MIO(println("World!"))
  } yield ()

  println(forResult)
  forResult.run
}
