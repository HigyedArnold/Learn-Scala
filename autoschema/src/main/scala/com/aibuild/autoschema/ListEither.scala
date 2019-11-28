package com.aibuild.autoschema

object ListEither extends App {

  val list1 = List(Left("x"), Right(1), Left("y"))
  val list2 = List(Right(2),  Right(1))

  def sequenceO[A, B](s: Seq[Either[A, B]]): Either[A, Seq[B]] =
    s.collectFirst { case Left(x) => Left(x) } getOrElse Right(s collect { case Right(x) => x })

  def sequenceU[A, B](s: Seq[Either[A, B]]): Either[A, Seq[B]] =
    s.foldLeft(Right(Seq.empty[B]): Either[A, Seq[B]])(
      (acc, e) =>
        for {
          xs <- acc.right
          x  <- e.right
        } yield xs :+ x
    )

  println(sequenceO(list1))
  println(sequenceO(list2))
  println(sequenceU(list1))
  println(sequenceU(list2))
}
