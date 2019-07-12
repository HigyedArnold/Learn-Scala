package com.aibuild.cats

import cats.data.{Writer, WriterT}
import cats.instances.future._
import cats.instances.list._
import cats.instances.option._
import cats.instances.vector._
import cats.syntax.applicative._
import cats.syntax.either._
import cats.syntax.writer._
import cats.{Eval, Id, Monad}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._

/**
  * Created by ArnoldHigyed on 06/02/2019
  */
object Monads extends App {

  //  A monad is a mechanism for sequencing computations.
  //
  //  trait Monad[F[_]] {
  //    def pure[A](value: A): F[A]
  //
  //    def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]
  //
  //    def map[A, B](value: F[A])(func: A => B): F[B] =
  //      flatMap(value)(a => pure(func(a)))
  //  }
  //
  //  Left identity: calling pure and transforming the result with func is the same as calling func:
  //    pure(a).flatMap(func) == func(a)
  //  Right identity: passing pure to flatMap is the same as doing nothing:
  //    m.flatMap(pure) == m
  //  Associativity: flatMapping over two functions f and g is the same as flatMapping over f and then flatMapping over g:
  //    m.flatMap(f).flatMap(g) == m.flatMap(x => f(x).flatMap(g))

  val opt1  = Monad[Option].pure(3)
  val opt2  = Monad[Option].flatMap(opt1)(a => Some(a + 2))
  val opt3  = Monad[Option].map(opt2)(a => 100 * a)
  val list1 = Monad[List].pure(3)
  val list2 = Monad[List].flatMap(List(1, 2, 3))(a => List(a, a * 10))
  val list3 = Monad[List].map(list2)(a => a + 123)

  println(opt1)
  println(opt2)
  println(opt3)
  println(list1)
  println(list2)
  println(list3)

  val fm     = Monad[Future]
  val future = fm.flatMap(fm.pure(1))(x => fm.pure(x + 2))
  println(Await.result(future, 1.second))

  /** -------------------------------------------------------------------------------------------------------------- **/
  /**
    * Identity Monad
    */
  def pure[A](value: A): Id[A] = value
  def map[A,     B](initial: Id[A])(func: A => B):     Id[B] = func(initial)
  def flatMap[A, B](initial: Id[A])(func: A => Id[B]): Id[B] = func(initial)

  "Error".asLeft[Int].getOrElse(0)
  "Error".asLeft[Int].orElse(2.asRight[String])
  (-1).asRight[String].ensure("Must be non-negative!")(_ > 0)
  "error".asLeft[Int].recover {
    case str: String => -1
  }
  "error".asLeft[Int].recoverWith {
    case str: String => Right(-1)
  }
  "foo".asLeft[Int].leftMap(_.reverse)
  6.asRight[String].bimap(_.reverse, _ * 7)
  "bar".asLeft[Int].bimap(_.reverse, _ * 7)
  123.asRight[String].swap

  for {
    a <- 1.asRight[String]
    b <- 0.asRight[String]
    c <- if (b == 0) "DIV0".asLeft[Int]
        else (a / b).asRight[String]
  } yield c * 100

  /** -------------------------------------------------------------------------------------------------------------- **/
  /**
    * Error Handling
    */
  sealed trait LoginError extends Product with Serializable
  final case class UserNotFound(username:      String) extends LoginError
  final case class PasswordIncorrect(username: String) extends LoginError
  case object UnexpectedError extends LoginError
  case class User(username: String, password: String)
  type LoginResult = Either[LoginError, User]

  // Choose error-handling behaviour based on type:
  def handleError(error: LoginError): Unit =
    error match {
      case UserNotFound(u) =>
        println(s"User not found: $u")
      case PasswordIncorrect(u) =>
        println(s"Password incorrect: $u")
      case UnexpectedError =>
        println(s"Unexpected error")
    }
  val result1: LoginResult = User("dave", "passw0rd").asRight
  // result1: LoginResult = Right(User(dave,passw0rd))
  val result2: LoginResult = UserNotFound("dave").asLeft
  // result2: LoginResult = Left(UserNotFound(dave))
  result1.fold(handleError, println)
  // User(dave,passw0rd)
  result2.fold(handleError, println)
  // User not found: dave

  trait MonadError[F[_], E] extends Monad[F] {
    // Lift an error into the `F` context:
    def raiseError[A](e: E): F[A]
    // Handle an error, potentially recovering from it:
    def handleError[A](fa: F[A])(f: E => A): F[A]
    // Test an instance of `F`,
    // failing if the predicate is not satisfied:
    def ensure[A](fa: F[A])(e: E)(f: A => Boolean): F[A]
  }

  /** -------------------------------------------------------------------------------------------------------------- **/
  /**
    * Eval Monad
    */
  val now = Eval.now(math.random + 1000)
  // now: cats.Eval[Double] = Now(1000.5473732231594)
  val later = Eval.later(math.random + 2000)
  // later: cats.Eval[Double] = cats.Later@68492ea2
  val always = Eval.always(math.random + 3000)
  // always: cats.Eval[Double] = cats.Always@4bbcc69c
  now.value
  // res6: Double = 1000.5473732231594
  later.value
  // res7: Double = 2000.9010215354635
  always.value
  // res8: Double = 3000.283950879214

  //  Scala     Cats    Properties
  //  val       Now     eager, memoized
  //  lazy val  Later   lazy, memoized
  //  def       Always  lazy, not memoized

  val ans = for {
    a <- Eval.now { println("Calculating A"); 40 }
    b <- Eval.always { println("Calculating B"); 2 }
  } yield {
    println("Adding A and B")
    a + b
  }
  // Calculating A
  // ans: cats.Eval[Int] = cats.Eval$$anon$8@6c0993a2
  ans.value // first access
  // Calculating B
  // Adding A and B
  // res16: Int = 42
  ans.value // second access
  // Calculating B
  // Adding A and B
  // res17: Int = 42

  def factorial(n: BigInt): Eval[BigInt] =
    if (n == 1) {
      Eval.now(n)
    }
    else {
      Eval.defer(factorial(n - 1).map(_ * n))
    }
  //  factorial(50000).value

  def foldRightEval[A, B](as: List[A], acc: Eval[B])(fn: (A, Eval[B]) => Eval[B]): Eval[B] =
    as match {
      case head :: tail =>
        Eval.defer(fn(head, foldRightEval(tail, acc)(fn)))
      case Nil =>
        acc
    }

  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B =
    foldRightEval(as, Eval.now(acc)) { (a, b) =>
      b.map(fn(a, _))
    }.value

  //  foldRight((1 to 100000).toList, 0L)(_ + _)
  // res22: Long = 5000050000

  /** -------------------------------------------------------------------------------------------------------------- **/
  /**
    * Writer Monad
    */
  Writer(
    Vector(
      "It was the best of times",
      "it was the worst of times"
    ),
    1859
  )
  // res0: cats.data.WriterT[cats.Id,scala.collection.immutable.Vector[String],Int] =
  // WriterT((Vector(It was the best of times, it wasthe worst of times),1859))

  type Writer[W, A] = WriterT[Id, W, A]
  type Logged[A] = Writer[Vector[String], A]

  123.pure[Logged]
  // res2: Logged[Int] = WriterT((Vector(),123))

  Vector("msg1", "msg2", "msg3").tell
  // res3: cats.data.Writer[scala.collection.immutable.Vector[String], Unit] = WriterT((Vector(msg1, msg2, msg3),()))

  val a = Writer(Vector("msg1", "msg2", "msg3"), 123)
  // a: cats.data.WriterT[cats.Id,scala.collection.immutable.Vector[String],Int] = WriterT((Vector(msg1, msg2, msg3),123))
  val b = 123.writer(Vector("msg1", "msg2", "msg3"))
  // b: cats.data.Writer[scala.collection.immutable.Vector[String],Int] = WriterT((Vector(msg1, msg2, msg3),123))

  val aResult: Int = a.value
  // aResult: Int = 123
  val aLog: Vector[String] = a.written
  // aLog: Vector[String] = Vector(msg1, msg2, msg3)
  val (log, result) = b.run
  // log: scala.collection.immutable.Vector[String] = Vector(msg1, msg2, msg3)
  // result: Int = 123

  val writer1 = for {
    a <- 10.pure[Logged]
    _ <- Vector("a", "b", "c").tell
    b <- 32.writer(Vector("x", "y", "z"))
  } yield a + b
  // writer1: cats.data.WriterT[cats.Id,Vector[String],Int] = WriterT((Vector(a, b, c, x, y, z),42)) writer1.run
  // res4: cats.Id[(Vector[String], Int)] = (Vector(a, b, c, x, y, z), 42)
  writer1.run
  // res4: cats.Id[(Vector[String], Int)] = (Vector(a, b, c, x, y, z), 42)

  val writer2 = writer1.mapWritten(_.map(_.toUpperCase))
  // writer2: cats.data.WriterT[cats.Id,scala.collection.immutable.Vector[String],Int] = WriterT((Vector(A, B, C, X, Y, Z),42))
  writer2.run
  // res5: cats.Id[(scala.collection.immutable.Vector[String], Int)] = (Vector(A, B, C, X, Y, Z),42)

  val writer3 = writer1.bimap(
    log => log.map(_.toUpperCase),
    res => res * 100
  )
  // writer3: cats.data.WriterT[cats.Id,scala.collection.immutable. Vector[String],Int] = WriterT((Vector(A, B, C, X, Y, Z),4200))

  val writer4 = writer1.mapBoth { (log, res) =>
    val log2 = log.map(_ + "!")
    val res2 = res * 1000
    (log2, res2)
  }
  // writer4: cats.data.WriterT[cats.Id,scala.collection.immutable. Vector[String],Int] = WriterT((Vector(a!, b!, c!, x!, y!, z!) ,42000))

  val writer5 = writer1.reset
  // writer5: cats.data.WriterT[cats.Id,Vector[String],Int] = WriterT((Vector(),42))
  writer5.run
  // res8: cats.Id[(Vector[String], Int)] = (Vector(),42)
  val writer6 = writer1.swap
  // writer6: cats.data.WriterT[cats.Id,Int,Vector[String]] = WriterT((42,Vector(a, b, c, x, y, z)))
  writer6.run
  // res9: cats.Id[(Int, Vector[String])] = (42,Vector(a, b, c, x, y, z))

  def slowly[A](body: => A) =
    try body
    finally Thread.sleep(100)

  def factorial(n: Int): Logged[Int] = {
    for {
      ans <- if (n == 0) {
              1.pure[Logged]
            }
            else {
              slowly(factorial(n - 1).map(_ * n))
            }
      _ <- Vector(s"fact $n $ans").tell
    } yield ans
  }

  println(factorial(5).run)
  // log: Vector[String] = Vector(fact 0 1, fact 1 1, fact 2 2, fact 3 6, fact 4 24, fact 5 120)
  // res: Int = 120

  println(
    Await.result(
      Future.sequence(
        Vector(
          Future(factorial(3).run),
          Future(factorial(5).run)
        )
      ),
      5.seconds
    )
  )
  // logA: Vector[String] = Vector(fact 0 1, fact 1 1, fact 2 2, fact 3 6)
  // ansA: Int = 6
  // logB: Vector[String] = Vector(fact 0 1, fact 1 1, fact 2 2, fact 3 6, fact 4 24, fact 5 120)
  // ansB: Int = 120

  /** -------------------------------------------------------------------------------------------------------------- **/

  /**
  * Reader Monad
  */

}
