import scala.concurrent.{ExecutionContext, Future}
def timesTwo(n: Int)(implicit ec: ExecutionContext): Future[Int] =
  Future(n * 2)
import scala.concurrent.ExecutionContext.Implicits.global
timesTwo(20).onComplete { result => println(s"Result: $result") }
