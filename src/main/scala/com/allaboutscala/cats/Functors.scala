package com.allaboutscala.cats

import cats.Functor
import cats.instances.list._
import cats.instances.option._
import cats.syntax.functor._ // for Functor

/**
  * Created by ArnoldHigyed on 06/02/2019
  */
object Functors extends App {

  val list1 = List(1, 2, 3)
  val list2 = Functor[List].map(list1)(_ * 2)
  val option1 = Option(123)
  val option2 = Functor[Option].map(option1)(_.toString)
  val func = (x: Int) => x + 1
  val liftedFunc = Functor[Option].lift(func)
  liftedFunc(Option(1))

  /**
    * map
    */
  sealed trait Tree[+A]
  final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
  final case class Leaf[A](value: A) extends Tree[A]

  object Tree {
    def branch[A](left: Tree[A], right: Tree[A]): Tree[A] =
      Branch(left, right)

    def leaf[A](value: A): Tree[A] =
      Leaf(value)
  }

  implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
    def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = fa match {
      case Branch(left, right) => Branch(map(left)(f), map(right)(f))
      case Leaf(value) => Leaf(f(value))
    }
  }

  // Branch(Leaf(10), Leaf(20)).map(_ * 2) //-> compile error, add Tree object

  println(Tree.leaf(100).map(_ * 2))
  println(Tree.branch(Tree.leaf(10), Tree.leaf(20)).map(_ * 2))

  /**
    * contramap
    */
  trait Printable[A] {

    self =>

    def format(value: A): String

    def contramap[B](func: B => A): Printable[B] = new Printable[B] {
      def format(value: B): String =
        self.format(func(value))
    }
  }

  def format[A](value: A)(implicit p: Printable[A]): String =
    p.format(value)

  implicit val stringPrintable: Printable[String] = new Printable[String] {
    def format(value: String): String =
      "\"" + value + "\""
  }

  implicit val booleanPrintable: Printable[Boolean] = new Printable[Boolean] {
    def format(value: Boolean): String =
      if(value) "yes" else "no"
  }

  println(format("hello"))
  println(format(true))

  final case class Box[A](value: A)

  implicit def boxPrintable[A](implicit p: Printable[A]) =
    p.contramap[Box[A]](_.value)

  println(format(Box("hello world")))
  println(format(Box(true)))

  /**
    * imap
    */
  trait Codec[A] {
    def encode(value: A): String
    def decode(value: String): A
    def imap[B](dec: A => B, enc: B => A): Codec[B] = {
      val self = this
      new Codec[B] {
        def encode(value: B): String =
          self.encode(enc(value))

        def decode(value: String): B =
          dec(self.decode(value))
      }
    }
  }

  def encode[A](value: A)(implicit c: Codec[A]): String =
    c.encode(value)

  def decode[A](value: String)(implicit c: Codec[A]): A =
    c.decode(value)

  implicit val stringCodec: Codec[String] =
    new Codec[String] {
      def encode(value: String): String = value
      def decode(value: String): String = value
    }

  implicit val intCodec: Codec[Int] =
    stringCodec.imap(_.toInt, _.toString)

  implicit val booleanCodec: Codec[Boolean] =
    stringCodec.imap(_.toBoolean, _.toString)

  implicit val doubleCodec: Codec[Double] =
    stringCodec.imap[Double](_.toDouble, _.toString)

  implicit def boxCodec[A](implicit c: Codec[A]): Codec[Box[A]] =
    c.imap[Box[A]](Box(_), _.value)

  println(encode(123.4))
  println(decode[Double]("123.4"))
  println(encode(Box(123.4)))
  println(decode[Box[Double]]("123.4"))

}