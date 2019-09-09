package com.aibuild.cats

/**
  * Created by ArnoldHigyed on 06/02/2019
  */
trait Printable[A] {
  def format(value: A): String
}

object PrintableInstances {

  implicit val printableString: Printable[String] = (value: String) => value

  implicit val printableInt: Printable[Int] = (value: Int) => value.toString

  implicit val printableCat: Printable[Cat] = (cat: Cat) => {
    val name  = Printable.format(cat.name)
    val age   = Printable.format(cat.age)
    val color = Printable.format(cat.color)
    s"$name is a $age year-old $color cat."
  }

}

object Printable {

  def format[A](value: A)(implicit p: Printable[A]): String =
    p.format(value)

  def print[A](value: A)(implicit p: Printable[A]): Unit =
    println(p.format(value))

}

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {

    def format(implicit p: Printable[A]): String =
      Printable.format(value)

    def print(implicit p: Printable[A]): Unit =
      Printable.print(value)
  }
}

final case class Cat(name: String, age: Int, color: String)

object Introduction_PrintableLib extends App {

  import PrintableInstances._
  import PrintableSyntax._

  val cat = Cat("Garfield", 38, "ginger and black")

  // Printable.print(cat)
  cat.print

}
