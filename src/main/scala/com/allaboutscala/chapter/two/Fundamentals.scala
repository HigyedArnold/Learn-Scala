package com.allaboutscala.chapter.two

/**
  * Created by ArnoldHigyed on 15/11/2018
  */
object Fundamentals extends App {

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: Using String interpolation to print a variable")
  val favoriteDonut: String = "Glazed Donut"
  println(s"My favorite donut = $favoriteDonut")

  println("\nStep 2: Using String interpolation on object properties")
  case class Donut(name: String, tasteLevel: String)

  val favoriteDonut2: Donut = Donut("Glazed Donut", "Very Tasty")
  println(s"My favorite donut name = ${favoriteDonut2.name}, tasteLevel = ${favoriteDonut2.tasteLevel}")

  println("\nStep 3: Using String interpolation to evaluate expressions")
  val qtyDonutsToBuy: Int = 10
  println(s"Are we buying 10 donuts = ${qtyDonutsToBuy == 10}")

  println("\nStep 4: Using String interpolation for formatting text")
  val donutName: String = "Vanilla Donut"
  val donutTasteLevel: String = "Tasty"
  println(f"$donutName%20s $donutTasteLevel")

  println("\nStep 5: Using f interpolation to format numbers")
  val donutPrice: Double = 2.50
  println(s"Donut price = $donutPrice")
  println(f"Formatted donut price = $donutPrice%.2f")

  println("\nStep 6: Using raw interpolation")
  println(raw"Favorite donut\t$donutName")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("\nStep 2: Using backslash to escpae quotes")
  val donutJson2: String ="{\"donut_name\":\"Glazed Donut\",\"taste_level\":\"Very Tasty\",\"price\":2.50}"
  println(s"donutJson2 = $donutJson2")

  println("\nStep 3: Using triple quotes \"\"\" to escape characters")
  val donutJson3: String = """{"donut_name":"Glazed Donut","taste_level":"Very Tasty","price":2.50}"""
  println(s"donutJson3 = $donutJson3")

  val donutJson4: String =
    """
      |{
      |"donut_name":"Glazed Donut",
      |"taste_level":"Very Tasty",
      |"price":2.50
      |}
    """
      .stripMargin
  println(s"donutJson3 = $donutJson4")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: A simple for loop from 1 to 5 inclusive")
  for(numberOfDonuts <- 1 to 5){
    println(s"Number of donuts to buy = $numberOfDonuts")
  }

  // TODO no new before the List object, because it's a companion object
  println("\nStep 3: Filter values using if conditions in for loop")
  val donutIngredients = List("flour", "sugar", "egg yolks", "syrup", "flavouring")
  for(ingredient <- donutIngredients if ingredient == "sugar"){
    println(s"Found sweetening ingredient = $ingredient")
  }

  println("\nStep 4: Filter values using if conditions in for loop and return the result back using the yield keyword")
  val sweeteningIngredients = for {
    ingredient <- donutIngredients
    if (ingredient == "sugar" || ingredient == "syrup")
  } yield ingredient
  println(s"Sweetening ingredients = $sweeteningIngredients")

  val twoDimensionalArray = Array.ofDim[String](2,2)
  twoDimensionalArray(0)(0) = "flour"
  twoDimensionalArray(0)(1) = "sugar"
  twoDimensionalArray(1)(0) = "egg"
  twoDimensionalArray(1)(1) = "syrup"

  for { x <- 0 until 2
        y <- 0 until 2
  } println(s"Donut ingredient at index ${(x,y)} = ${twoDimensionalArray(x)(y)}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: Create a simple numeric range from 1 to 5 inclusive")
  val from1To5 = 1 to 5
  println(s"Range from 1 to 5 inclusive = $from1To5")

  println("\nStep 4: Create an alphabetical range to represent letter a to z")
  val alphabetRangeFromAToZ = 'a' to 'z'
  println(s"Range of alphabets from a to z = $alphabetRangeFromAToZ")

  // TODO skip the () if the function has no side effect fp concept
  println("\nStep 6: Storing our ranges into collections")
  val listFrom1To5 = (1 to 5).toList
  println(s"Range to list = ${listFrom1To5.mkString(" ")}")

  val setFrom1To5 = (1 to 5).toSet
  println(s"Range to set = ${setFrom1To5.mkString(" ")}")

  val sequenceFrom1To5 = (1 to 5).toSeq
  println(s"Range to sequence = ${sequenceFrom1To5.mkString(" ")}")

  val arrayFrom1To5 = (1 to 5).toArray
  println(s"Range to array = `${arrayFrom1To5.mkString(" ")}")

  arrayFrom1To5.foreach(print(_))

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  // TODO while is not preferred in pure functional programming, also avoid var
  println("Step 1: How to use while loop in Scala")
  var numberOfDonutsToBake = 10
  while (numberOfDonutsToBake > 0) {
    println(s"Remaining donuts to be baked = $numberOfDonutsToBake")
    numberOfDonutsToBake -= 1
  }

  println("\nStep 2: How to use do while loop in Scala")
  var numberOfDonutsBaked = 0
  do {
    numberOfDonutsBaked += 1
    println(s"Number of donuts baked = $numberOfDonutsBaked")
  } while (numberOfDonutsBaked < 5)

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("\nStep 2: Pattern matching and return the result")
  val donutType = "Glazed Donut"
  val tasteLevel = donutType match {
    case "Glazed Donut" => "Very tasty"
    case "Plain Donut" => "Tasty"
  }
  println(s"Taste level of $donutType = $tasteLevel")

  println("\nStep 4: Pattern matching with two or more items on the same condition")
  val tasteLevel3 = donutType match {
    case "Glazed Donut" | "Strawberry Donut" => "Very tasty"
    case "Plain Donut" => "Tasty"
    case _ => "Tasty"
  }
  println(s"Taste level of $donutType = $tasteLevel3")

  println("\nStep 6: Pattern matching by types")
  val priceOfDonut: Any = 2.50
  val priceType = priceOfDonut match {
    case price: Int => "Int"
    case price: Double => "Double"
    case price: Float => "Float"
    case price: String => "String"
    case price: Boolean => "Boolean"
    case price: Char => "Char"
    case price: Long => "Long"
  }
  println(s"Donut price type = $priceType")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: Using Tuple2 to store 2 data points")
  val glazedDonutTuple = Tuple2("Glazed Donut", "Very Tasty")
  println(s"Glazed Donut with 2 data points = $glazedDonutTuple")

  println("\nStep 2: Access each element in tuple")
  println(s"${glazedDonutTuple._1} taste level is ${glazedDonutTuple._2}")

  println("\nStep 4: How to use pattern matching on Tuples")
  val strawberryDonut = Tuple3("Strawberry Donut", "Very Tasty", 2.50)
  val plainDonut = Tuple3("Plain Donut", "Tasty", 2)
  val glazedDonut = Tuple3("Glazed Donut", "Very Tasty", 2.50)

  val donutList = List(glazedDonut, strawberryDonut, plainDonut)

  val priceOfPlainDonut = donutList.foreach { tuple => {
    tuple match {
      case ("Plain Donut", _, price) => println(s"Donut type = Plain Donut, price = $price")
      case d if d._1 == "Glazed Donut" => println(s"Donut type = ${d._1}, price = ${d._3}")
      case _ => None
    }
  }}

  println("\nTip 1: A more elegant pattern matching within foreach function")
  donutList.foreach {
    case ("Plain Donut", taste, price) => println(s"Donut type = Plain Donut, price = $price")
    case d if d._1 == "Glazed Donut" => println(s"Donut type = ${d._1}, price = ${d._3}")
    case _ => None
  }

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: How to use Option and None - a basic example")
  val glazedDonutTaste: Option[String] = Some("Very Tasty")
  println(s"Glazed Donut taste = ${glazedDonutTaste.get}")

  println("\nStep 2: How to use Option and None - a basic example")
  val glazedDonutName: Option[String] = None
  println(s"Glazed Donut name = ${glazedDonutName.getOrElse("Glazed Donut")}")

  println("\nStep 3: How to use Pattern Matching with Option")
  glazedDonutName match {
    case Some(name) => println(s"Received donut name = $name")
    case None       => println(s"No donut name was found!")
  }

  println("\nTip 1: Filter None using map function")
  glazedDonutTaste.map(taste => println(s"glazedDonutTaste = $taste"))
  glazedDonutName.map(name => println(s"glazedDonutName = $name"))

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: Declare a variable of type Any")
  val favoriteDonutAny: Any = "Glazed Donut"
  println(s"favoriteDonut of type Any = $favoriteDonutAny")

  println("\nStep 2: Declare a variable of type AnyRef")
  val donutNameAny: AnyRef = "Glazed Donut"
  println(s"donutName of type AnyRef = $donutNameAny")

  println("\nStep 3: Declare a variable of type AnyVal")
  val donutPriceAny: AnyVal = 2.50
  println(s"donutPrice of type AnyVal = $donutPriceAny")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: How to create an enumeration")
  object Donut extends Enumeration {
    type Donut = Value

    val Glazed      = Value("Glazed")
    val Strawberry  = Value("Strawberry")
    val Plain       = Value("Plain")
    val Vanilla     = Value("Vanilla")
  }

  println("\nStep 2: How to print the String value of the enumeration")
  println(s"Vanilla Donut string value = ${Donut.Vanilla}")
  println(s"Vanilla Donut's id = ${Donut.Vanilla.id}")
  println(s"Donut types = ${Donut.values}")

  println("\nStep 5: How to pattern match on enumeration values")
  Donut.values.foreach {
    case d if (d == Donut.Strawberry || d == Donut.Glazed) => println(s"Found favourite donut = $d")
    case _ => None
  }

  println("\nStep 6: How to change the default ordering of enumeration values")
  object DonutTaste extends Enumeration{
    type DonutTaste = Value

    val Tasty       = Value(0, "Tasty")
    val VeryTasty   = Value(1, "Very Tasty")
    val Ok          = Value(-1, "Ok")
  }

  println(s"Donut taste values = ${DonutTaste.values}")
  println(s"Donut taste of OK id = ${DonutTaste.Ok.id}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

}