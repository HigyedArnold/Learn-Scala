package com.allaboutscala.cats

import cats.Monoid
import cats.instances.int._
import cats.instances.option._
import cats.kernel.Semigroup
import cats.syntax.semigroup._ // for |+|

/**
  * Created by ArnoldHigyed on 06/02/2019
  */
//trait Semigroup[A] {
//  def combine(x: A, y: A): A
//}
//
//trait Monoid[A] extends Semigroup[A] {
//  def empty: A
//}
//
//object Monoid {
//  def apply[A](implicit monoid: Monoid[A]) =
//    monoid
//}

object Monoids extends App {

  def associativeLaw[A](x: A, y: A, z: A)(implicit m: Monoid[A]): Boolean =
    m.combine(x, m.combine(y, z)) == m.combine(m.combine(x, y), z)

  def identityLaw[A](x: A)(implicit m: Monoid[A]): Boolean =
    (m.combine(x, m.empty) == x) && (m.combine(m.empty, x) == x)

  /**
    * Boolean
    */
  implicit val booleanAndMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = x && y
    override def empty: Boolean = true
  }

  implicit val booleanOrMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = x || y
    override def empty: Boolean = false
  }

  implicit val booleanEitherMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = (x && !y) || (!x && y)
    override def empty: Boolean = false
  }

  implicit val booleanXnorMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = (!x || y) && (x || !y)
    override def empty: Boolean = true
  }

  /**
    * Set
    */
  implicit def setUnionMonoid[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
    override def combine(x: Set[A], y: Set[A]): Set[A] = x union y
    override def empty: Set[A] = Set.empty[A]
  }

  implicit def setIntersectionSemigroup[A]: Semigroup[Set[A]] = new Semigroup[Set[A]] {
    override def combine(x: Set[A], y: Set[A]): Set[A] = x intersect y
  }

  implicit def setSymDiffMonoid[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
    override def combine(x: Set[A], y: Set[A]): Set[A] = (x diff y) union (y diff x)
    override def empty: Set[A] = Set.empty[A]
  }

//  val intSetMonoid = Monoid[Set[Int]]
//  val strSetMonoid = Monoid[Set[String]]
//  intSetMonoid.combine(Set(1, 2), Set(2, 3))
//  // res2: Set[Int] = Set(1, 2, 3)
//  strSetMonoid.combine(Set("A", "B"), Set("B", "C"))
//  // res3: Set[String] = Set(A, B, C)

  /**
    * Adding all the things
    */
  def add[A: Monoid](items: List[A]): A =
    items.foldLeft(Monoid[A].empty)(_ |+| _)

  add(List(1, 2, 3))
  // res9: Int = 6
  add(List(Some(1), None, Some(2), None, Some(3)))
  // res10: Option[Int] = Some(6)

  case class Order(totalCost: Double, quantity: Double)

  implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {
    def combine(o1: Order, o2: Order): Order =
      Order(o1.totalCost + o2.totalCost,
        o1.quantity + o2.quantity)
    def empty: Order = Order(0, 0)
  }

}