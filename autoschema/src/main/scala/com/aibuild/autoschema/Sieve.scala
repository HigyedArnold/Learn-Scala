package com.aibuild.autoschema

object Sieve extends App {

  def streamFrom(n: Int): Stream[Int] = Stream.from(n)

  def sieve (stream: Stream[Int]): Stream[Int] =
    stream.head #:: sieve(stream.tail.filter(_ % stream.head != 0))

  val prim = sieve(streamFrom(2))

  println(prim)
  prim.take(5).foreach(println)

  val p = List("", "9#greaT", "is great", "greater", "23#pp", "Aa#@4")
  val result = p
  .filterNot(_.isEmpty)
  .filter(_.exists(_.isDigit))
  .filter(_.exists(_.isUpper))
  .filter(_.matches( """^.*[\W].*$"""))
  .filter(_.length >= 5)

  println(result)

  val resultV = p.view
    .filterNot(_.isEmpty)
    .filter(_.exists(_.isDigit))
    .filter(_.exists(_.isUpper))
    .filter(_.matches( """^.*[\W].*$"""))
    .filter(_.length >= 5)
  val x = resultV.take(6)
  println(x.force)
}