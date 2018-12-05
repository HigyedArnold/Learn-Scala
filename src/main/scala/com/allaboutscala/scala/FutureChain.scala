package com.allaboutscala.scala

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global //We need an executionContext to run futures
import scala.concurrent.duration._ //This provides the "1 second" syntax

/**
  * Created by ArnoldHigyed on 5/12/2018
  */
object FutureChain extends App {

  class CoffeeBeans()
  class GroundCoffee()
  class ColdWater()
  class WarmWater()
  class UnfilteredCoffee()
  class FilteredCoffee()

  //we start out with beans and cold water
  val beans = new CoffeeBeans()
  val water = new ColdWater()

  def grindBeans(beans: CoffeeBeans) = {
    println("Grinding beans...")
    Future { new GroundCoffee() }
  }

  def heatWater(water: ColdWater) = {
    println("Heating water...")
    Future { new WarmWater() }
  }

  def combine(groundCoffee: GroundCoffee, water: WarmWater) = {
    println("Combining ingredients...")
    Future { new UnfilteredCoffee() }
  }

  def filter(unfilteredCoffee: UnfilteredCoffee) = {
    print("Filtering coffee...")
    Future { new FilteredCoffee() }
  }

  val fGrind: Future[GroundCoffee] = grindBeans(beans)
  val fHeat: Future[WarmWater] = heatWater(water)
  val fStep1: Future[(GroundCoffee, WarmWater)] = fGrind.zip(fHeat)

  /** 1 */
  println("\n1st\n")

  fStep1.onSuccess { case (groundCoffee, warmWater) =>
    val fCombine: Future[UnfilteredCoffee] = combine(groundCoffee, warmWater)
    fCombine.onSuccess { case unfilteredCoffee =>
      val fFilter: Future[FilteredCoffee] = filter(unfilteredCoffee)
      fFilter.onSuccess { case successCoffee =>
        println(s"$successCoffee is ready!")
      }
    }
  }

  Thread.sleep(5000)

  /** 2 */
  println("\n2ns\n")

  val fCombine: Future[UnfilteredCoffee] = fStep1.flatMap {
    case (groundCoffee, warmWater) => combine(groundCoffee, warmWater)
  }
  val fFilter: Future[FilteredCoffee] = fCombine.flatMap {
    case unfilteredCoffee => filter(unfilteredCoffee)
  }
  val flatmapCoffee: FilteredCoffee = Await.result(fFilter, 1 second)

  Thread.sleep(5000)

  /** 3 */
  println("\n3rd\n")

  val fFor = for {
    groundCoffee <- grindBeans(beans)
    warmWater <- heatWater(water)
    unfilteredCoffee <- combine(groundCoffee, warmWater)
  } yield filter(unfilteredCoffee)
  val forCoffee: Future[FilteredCoffee] = Await.result(fFor, 1 second)

}