package com.aibuild.allaboutscala.base

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

/**
  * Created by ArnoldHigyed on 16/11/2018
  */
object MutableCollection extends App {

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /**
    * What is an Array?
    *
    * As per Wikipedia, an Array is a mutable data structure of fixed length. It also allows you to access and modify
    * elements at specific index.
    */
  println("Step 1: How to initialize a String Array with 3 elements")
  val array1: Array[String] = Array("Plain Donut", "Strawberry Donut", "Chocolate Donut")
  println(s"Elements of array1 = ${array1.mkString(", ")}")

  println("\nStep 2: How to access elements at specific index in an Array")
  println(s"Element at index 0 = ${array1(0)}")
  println(s"Element at index 1 = ${array1(1)}")
  println(s"Element at index 2 = ${array1(2)}")

  println("\nStep 3: How to initialize an Array by specifying it's capacity")
  val array2: Array[String] = new Array(3)
  array2(0) = "Plain Donut"
  array2(1) = "Strawberry Donut"
  array2(2) = "Chocolate Donut"
  println(s"Elements of array2 = ${array2.mkString(", ")}")

  println("\nStep 4: How to create a 2D Array (2 dimension array)")
  val rows    = 2
  val columns = 2
  val array3: Array[Array[String]] = Array.ofDim[String](rows, columns)
  array3(0)(0) = "Plain"
  array3(0)(1) = "Donut"
  array3(1)(0) = "Strawberry"
  array3(1)(1) = "Donut"
  println(s"Elements of 2 dimensional array = ${array3.deep.toList}")

  println("\nStep 5: How to create 3D Array (3 Dimension Array) using Array.ofDim() method")
  val array4: Array[Array[Array[String]]] = Array.ofDim[String](3, 3, 3)
  println(s"Elements of 3 dimensional array = ${array4.deep.toList}")

  println("\nStep 6: How to create an Array using tabulate function")
  val array5: Array[Int] = Array.tabulate(5)(_ + 1)
  println(s"Array of 5 columns = ${array5.toList}")

  println("\nStep 7: How to create dimensional Arrays using tabulate function")
  val row1                         = 1
  val column3                      = 3
  val arrayOfOneRowAndThreeColumns = Array.tabulate(row1, column3)((row, column) => row + column)
  println(s"Array with 1 row and 3 columns = ${arrayOfOneRowAndThreeColumns.deep.toString}")

  val row2                          = 2
  val arrayOfTowRowsAndThreeColumns = Array.tabulate(row2, column3)((row, column) => row + column)
  println(s"Array with 2 rows and 3 columns = ${arrayOfTowRowsAndThreeColumns.deep.toString}")

  println("\nStep 8: How to create Array using Range")
  val rangeArray: Array[Int] = (1 to 10).toArray[Int]
  println(s"Array using Range from 1 to 10 = ${rangeArray.mkString(", ")}")

  println("\nStep 9: How to copy an Array using Array.copy")
  val copyOfRangeArray: Array[Int] = new Array(rangeArray.length)
  Array.copy(rangeArray, 0, copyOfRangeArray, 0, rangeArray.length)
  println(s"copy of range array with elements from rangeArray = ${copyOfRangeArray.mkString(", ")}")

  println("\nStep 10: How to clone an Array")
  val clonedRangeArray = rangeArray.clone
  clonedRangeArray(0) = 10 // update index 0 to value 10
  println(s"clonedRangeArray = ${clonedRangeArray.mkString(", ")}")
  println(s"original range array still unchanged = ${rangeArray.mkString(", ")}")

  println("\nStep 11: How to iterate over an Array using for comprehension")
  for (d <- array1) {
    println(s"d = $d")
  }

  println("\nStep 12: How to merge two Arrays using Array.concat")
  val moreDonutsArray:  Array[String] = Array("Vanilla Donut", "Glazed Donut")
  val mergedDonutArray: Array[String] = Array.concat(array1,   moreDonutsArray)
  println(s"Merged Array of donuts = ${mergedDonutArray.mkString(", ")}")

  println("\nStep 13: How to check if two Arrays are equal")
  val arrayToCompare = Array[String]("Plain Donut", "Strawberry Donut", "Chocolate Donut")

  println(s"using == ${array1 sameElements moreDonutsArray}") // prints false

  println(s"using == ${array1 sameElements arrayToCompare}") // ALSO prints false ??? what ... be careful

  println(s"using sameElement function = ${array1 sameElements arrayToCompare}") // NOW this works and returns true!

  println("\nStep 14: How to check if two Arrays are equal using deep function and == ")
  println(s"using deep function = ${array1.deep == arrayToCompare.deep}")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /**
    * What is an ArrayBuffer?
    *
    * As per the Scala Documentation, an ArrayBuffer is a mutable data structure which allows you to access and modify
    * elements at specific index.
    * Compared to the previous tutorial on Array, an ArrayBuffer is resizable while an Array is fixed in size.
    */
  println("Step 1: How to initialize an ArrayBuffer with 3 elements")
  val arrayBuffer1: ArrayBuffer[String] = ArrayBuffer("Plain Donut", "Strawberry Donut", "Chocolate Donut")
  println(s"Elements of arrayBuffer1 = $arrayBuffer1")

  println("\nStep 2: How to access elements of an ArrayBuffer at specific index")
  println(s"Element at index 0 = ${arrayBuffer1(0)}")
  println(s"Element at index 1 = ${arrayBuffer1(1)}")
  println(s"Element at index 2 = ${arrayBuffer1(2)}")

  println("\nStep 3: How to add elements to an ArrayBuffer using +=")
  arrayBuffer1 += "Vanilla Donut"
  println(s"Elements of arrayBuffer1 = $arrayBuffer1")
  // NOTE: arrayBuffer1 is mutable and hence we were able to add a new element to it

  println("\nStep 4: How to add elements from a List to an ArrayBuffer using ++=")
  arrayBuffer1 ++= List[String]("Glazed Donut", "Krispy creme")
  println(s"Elements of arrayBuffer1 = $arrayBuffer1")

  println("\nStep 5: How to remove elements from an ArrayBuffer")
  arrayBuffer1 -= "Plain Donut"
  println(s"Elements of arrayBuffer1 = $arrayBuffer1")

  println("\nStep 6: How to remove elements of a List from ArrayBuffer using --=")
  arrayBuffer1 --= List[String]("Glazed Donut", "Krispy creme")
  println(s"Elements of arrayBuffer1 = $arrayBuffer1")

  println("\nStep 7: How to initialize an empty ArrayBuffer")
  val emptyArrayBuffer: ArrayBuffer[String] = ArrayBuffer.empty[String]
  println(s"Empty array buffer = $emptyArrayBuffer")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /**
    * What is an ArrayStack?
    *
    * As per Wikipedia, a Stack is a data structure which follows the LIFO (Last In First Out) semantics. It typically
    * provides a push() method to add element at the top of the Stack and a pop() method to take the most recently added
    * element from the top of the Stack.
    * As per Scala Documentation, an ArrayStack provides the Stack semantics while internally being backed by an Array
    * data structure.
    */
  println("Step 1: How to initialize ArrayStack with 3 elements")
  val arrayStack1: mutable.ArrayStack[String] = mutable.ArrayStack("Plain Donut", "Strawberry Donut", "Chocolate Donut")
  println(s"Elements of arrayStack1 = $arrayStack1")

  println("\nStep 2: How to check elements at specific index of an ArrayStack")
  println(s"Element at index 0 = ${arrayStack1(0)}")
  println(s"Element at index 1 = ${arrayStack1(1)}")
  println(s"Element at index 2 = ${arrayStack1(2)}")

  println("\nStep 3: How to add elements to an ArrayStack using +=")
  arrayStack1 += "Vanilla Donut"
  println(s"Elements of arrayStack1 = $arrayStack1")

  println("\nStep 4: How to add elements from a List to an ArrayStack using ++=")
  arrayStack1 ++= List[String]("Glazed Donut", "Krispy creme")
  println(s"Elements of arrayStack1 = $arrayStack1")

  println("\nStep 5: How to take an element from an ArrayStack using pop function")
  println(s"Pop element from stack = ${arrayStack1.pop}")
  println(s"Elements of stack1 = $arrayStack1")

  println("\nStep 6: How to push one element at the top of the ArrayStack using push function")
  arrayStack1.push("Krispy Creme")
  println(s"Elements after push = $arrayStack1")

  println("\nStep 7: How to initialize an empty ArrayStack")
  val emptyArrayStack: mutable.ArrayStack[Nothing] = mutable.ArrayStack.empty
  println(s"Empty Stack = $emptyArrayStack")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /**
    * What is a ListBuffer?
    *
    * As per the Scala Documentation, a ListBuffer is resizable similar to an ArrayBuffer, except that it uses a Linked
    * List as its internal data structure.
    */
  println("Step 1: How to initialize a ListBuffer with 3 elements")
  val listBuffer1: ListBuffer[String] = ListBuffer("Plain Donut", "Strawberry Donut", "Chocolate Donut")
  println(s"Elements of listBuffer1 = $listBuffer1")

  println("\nStep 2: How to access elements at specific index in a ListBuffer")
  println(s"Element at index 0 = ${listBuffer1.head}")
  println(s"Element at index 1 = ${listBuffer1(1)}")
  println(s"Element at index 2 = ${listBuffer1(2)}")

  println("\nStep 3: How to add elements to a ListBuffer using +=")
  listBuffer1 += "Vanilla Donut"
  println(s"Elements of listBuffer1 = $listBuffer1")

  println("\nStep 4: How to add elements from a List to a ListBuffer using ++=")
  listBuffer1 ++= List[String]("Glazed Donut", "Krispy creme")
  println(s"Elements of listBuffer1 = $listBuffer1")

  println("\nStep 5: How to remove elements from a ListBuffer")
  listBuffer1 -= "Plain Donut"
  println(s"Elements of listBuffer1 = $listBuffer1")

  println("\nStep 6: How to remove elements from a List to a ListBuffer using --=")
  listBuffer1 --= List[String]("Glazed Donut", "Krispy creme")
  println(s"Elements of listBuffer1 = $listBuffer1")

  println("\nStep 7: How to initialize an empty ListBuffer")
  val emptyListBuffer: ListBuffer[String] = ListBuffer.empty[String]
  println(s"Empty list buffer = $emptyListBuffer")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /**
    * What is a ListBuffer?
    *
    * As per the Scala Documentation, a ListBuffer is resizable similar to an ArrayBuffer, except that it uses a Linked
    * List as its internal data structure.
    */
  println("\nStep 1: How to initialize a Map with 3 elements")
  val map1: mutable.Map[String, String] = mutable.Map(("PD", "Plain Donut"), ("SD", "Strawberry Donut"), ("CD", "Chocolate Donut"))
  println(s"Elements of map1 = $map1")

  println("\nStep 2: How to initialize a Map using key -> value notation")
  val map2: mutable.Map[String, String] = mutable.Map("VD" -> "Vanilla Donut", "GD" -> "Glazed Donut")
  println(s"Elements of map2 = $map2")

  println("\nStep 3: How to access elements of Map by specific key")
  println(s"Element by key VD = ${map2("VD")}")
  println(s"Element by key GD = ${map2("GD")}")

  println("\nStep 4: How to add elements to Map using +=")
  map1 += ("KD" -> "Krispy Kreme Donut")
  println(s"Element in map1 = $map1")

  println("\nStep 5: How to add elements from a Map to an existing Map using ++=")
  map1 ++= map2
  println(s"Elements in map1 = $map1")

  println("\nStep 6: How to remove key and its value from Map using -=")
  map1 -= "CD"
  println(s"Map without the key CD and its value = $map1")

  println("\nStep 7: How to initialize an empty Map")
  val emptyMap: mutable.Map[String, String] = mutable.Map.empty[String, String]
  println(s"Empty Map = $emptyMap")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /**
    * What is a HashMap?
    *
    * As per the Scala Documentation, a HashMap is a collection of key and value pairs which are stored internally
    * using a Hash Table data structure.
    */
  println("\nStep 1: How to initialize a HashMap with 3 elements")
  val hashMap1: mutable.HashMap[String, String] = mutable.HashMap(("PD", "Plain Donut"), ("SD", "Strawberry Donut"), ("CD", "Chocolate Donut"))
  println(s"Elements of hashMap1 = $hashMap1")

  println("\nStep 2: How to initialize HashMap using key -> value notation")
  val hashMap2: mutable.HashMap[String, String] = mutable.HashMap("VD" -> "Vanilla Donut", "GD" -> "Glazed Donut")
  println(s"Elements of hashMap2 = $hashMap2")

  println("\nStep 3: How to access elements of HashMap by specific key")
  println(s"Element by key VD = ${hashMap2("VD")}")
  println(s"Element by key GD = ${hashMap2("GD")}")

  println("\nStep 4: How to add elements to HashMap using +=")
  hashMap1 += ("KD" -> "Krispy Kreme Donut")
  println(s"Element in hashMap1 = $hashMap1")

  println("\nStep 5: How to add elements from a HashMap to an existing HashMap using ++=")
  hashMap1 ++= hashMap2
  println(s"Elements in hashMap1 = $hashMap1")

  println("\nStep 6: How to remove key and its value from HashMap using -=")
  hashMap1 -= "CD"
  println(s"HashMap without the key CD and its value = $hashMap1")

  println("\nStep 7: How to initialize an empty HashMap")
  val emptyMap1: mutable.HashMap[String, String] = mutable.HashMap.empty[String, String]
  println(s"Empty HashMap = $emptyMap")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /**
    * What is a ListMap?
    *
    * As per the Scala Documentation, a ListMap is a collection of key and value pairs where the keys are backed
    * by a List data structure.
    */
  println("\nStep 1: How to initialize a ListMap with 3 elements")
  val listMap1: mutable.ListMap[String, String] = mutable.ListMap("PD" -> "Plain Donut", "SD" -> "Strawberry Donut", "CD" -> "Chocolate Donut")
  println(s"Elements of listMap1 = $listMap1")

  println("\nStep 2: How to initialize ListMap using key -> value notation")
  val listMap2: mutable.ListMap[String, String] = mutable.ListMap("VD" -> "Vanilla Donut", "GD" -> "Glazed Donut")
  println(s"Elements of listMap1 = $listMap2")

  println("\nStep 3: How to access elements of ListMap by specific key")
  println(s"Element by key VD = ${listMap2("VD")}")
  println(s"Element by key GD = ${listMap2("GD")}")

  println("\nStep 4: How to add elements to ListMap using +")
  listMap1 += ("KD" -> "Krispy Kreme Donut")
  println(s"Element of listMap1 = $listMap1")

  println("\nStep 5: How to add elements from a ListMap to an existing ListMap using ++=")
  listMap1 ++= listMap2
  println(s"Element of listMap1 = $listMap1")

  println("\nStep 6: How to remove key and its value from ListMap using -=")
  listMap1 -= "CD"
  println(s"ListMap without the key CD and its value = $listMap1")

  println("\nStep 7: How to initialize an empty ListMap")
  val emptyListMap: mutable.ListMap[String, String] = mutable.ListMap.empty[String, String]
  println(s"Empty ListMap of type String = $emptyListMap")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /**
    * What is a LinkedHashMap?
    *
    * As per the Scala Documentation, a LinkedHashMap is a collection of key and value pairs which are stored internally
    * using Hash Table data structure. But iterating through the elements is done in order.
    */
  println("\nStep 1: How to initialize a LinkedHashMap with 3 elements")
  val linkedHashMap1: mutable.LinkedHashMap[String, String] = mutable.LinkedHashMap("PD" -> "Plain Donut", "SD" -> "Strawberry Donut", "CD" -> "Chocolate Donut")
  println(s"Elements of linkedHashMap1 = $linkedHashMap1")

  println("\nStep 2: How to initialize a LinkedHashMap using key -> value notation")
  val linkedHashMap2: mutable.LinkedHashMap[String, String] = mutable.LinkedHashMap("VD" -> "Vanilla Donut", "GD" -> "Glazed Donut")
  println(s"LinkedHashMap1 = $linkedHashMap2")

  println("\nStep 3: How to access elements of LinkedHashMap by specific key")
  println(s"Element by key VD = ${linkedHashMap2("VD")}")
  println(s"Element by key GD = ${linkedHashMap2("GD")}")

  println("\nStep 4: How to add elements to LinkedHashMap using +=")
  linkedHashMap1 += ("KD" -> "Krispy Kreme Donut")
  println(s"Elements of linkedHashMap1 = $linkedHashMap1")

  println("\nStep 5: How to add elements from a LinkedHashMap to an existing LinkedHashMap using ++=")
  linkedHashMap1 ++= linkedHashMap2
  println(s"Elements of linkedHashMap1 = $linkedHashMap1")

  println("\nStep 6: How to remove key and its value from LinkedHashMap using -=")
  linkedHashMap1 -= "CD"
  println(s"LinkedHashMap without the key CD and its value = $linkedHashMap1")

  println("\nStep 7: How to initialize an empty LinkedHashMap")
  val emptyLinkedHashMap: mutable.LinkedHashMap[String, String] = mutable.LinkedHashMap.empty[String, String]
  println(s"Empty LinkedHashMap of type String = $emptyLinkedHashMap")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /**
    * What is a Queue?
    *
    * As per Wikipedia, a Queue is a data structure which follows the FIFO (First In First Out) semantics. In other
    * words, the first element that was added to the queue will be the first one to be removed.
    * As per the Scala Documentation, a mutable Queue has an internal data structure which allows you to insert and
    * retrieve elements in a FIFO manner.
    */
  println("\nStep 1: How to initialize a Queue with 3 elements")
  val queue1: mutable.Queue[String] = mutable.Queue("Plain Donut", "Strawberry Donut", "Chocolate Donut")
  println(s"Elements of queue1 = $queue1")

  println("\nStep 2: How to access elements of Queue at specific index")
  println(s"Element at index 0 = ${queue1.head}")
  println(s"Element at index 0 = ${queue1(1)}")
  println(s"Element at index 0 = ${queue1(2)}")

  println("\nStep 3: How to add elements to Queue using +=")
  queue1 += "Glazed Donut"
  println(s"Elements of queue1 = $queue1")

  println("\nStep 4: How to add elements to Queue using enqueue")
  queue1.enqueue("Vanilla Donut")
  println(s"Enqueue element Vanilla Donut onto queue1 = $queue1")

  println("\nStep 5: How to take the first element or head from the Queue")
  val dequeuedElement: String = queue1.dequeue
  println(s"Dequeued element = $dequeuedElement")
  println(s"Elements of queue1 after dequeue = $queue1")

  println("\nStep 6: How to initialize an empty Queue")
  val emptyQueue: mutable.Queue[String] = mutable.Queue.empty[String]
  println(s"Empty Queue = $emptyQueue")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /**
    * What is a PriorityQueue?
    *
    * As per Wikipedia, a PriorityQueue is similar to a queue data structure except that elements added to the queue
    * are associated with a priority. This priority is then used to determine which elements get dequeued or removed
    * from the queue.
    * As per the Scala Documentation, a mutable PriorityQueue is implemented using a heap data structure and only the
    * method dequeue and dequeueAll will return the elements in their priority order.
    */
  println("Step 1: How to declare a case class to represent Donut object")
  case class Donut(name: String, price: Double)

  println("\nStep 2: How to declare a function which defines the ordering of a PriorityQueue of Donut objects")
  def donutOrder(d: Donut) = d.price

  println("\nStep 3: How to initialize a PriorityQueue of Donut objects and specify the Ordering")

  val priorityQueue1: mutable.PriorityQueue[Donut] =
    mutable.PriorityQueue(Donut("Plain Donut", 1.50), Donut("Strawberry Donut", 2.0), Donut("Chocolate Donut", 2.50))(Ordering.by(donutOrder))
  println(s"Elements of priorityQueue1 = $priorityQueue1")

  println("\nStep 4: How to add an element to PriorityQueue using enqueue function")
  priorityQueue1.enqueue(Donut("Vanilla Donut", 1.0))
  println(s"Elements of priorityQueue1 after enqueue function is called = $priorityQueue1")

  println("\nStep 5: How to add an element to PriorityQueue using +=")
  priorityQueue1 += Donut("Krispy Kreme Donut", 1.0)
  println(s"Elements of priorityQueue1 after enqueue function is called = $priorityQueue1")

  println("\nStep 6: How to remove an element from PriorityQueue using the dequeue function")
  val donutDequeued: Donut = priorityQueue1.dequeue()
  println(s"Donut element dequeued = $donutDequeued")
  println(s"Elements of priorityQueue1 after dequeued function is called = $priorityQueue1")

  println("\nStep 7: How to initialize an empty PriorityQueue")
  val emptyPriorityQueue: mutable.PriorityQueue[String] = mutable.PriorityQueue.empty[String]
  println(s"Empty emptyPriorityQueue = $emptyPriorityQueue")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /**
    * What is a Set?
    *
    * As per Wikipedia, a Set is a data structure which allows you to store elements which are not repeatable. A Set
    * also does not guarantee the ordering of elements.
    * As per Scala Documentation, a Mutable Set is a generic trait to support set semantics and behaviour.
    */
  println("\nStep 1: How to initialize a Set with 3 elements")
  val set1: mutable.Set[String] = mutable.Set("Plain Donut", "Strawberry Donut", "Chocolate Donut")
  println(s"Elements of set1 = $set1")

  println("\nStep 2: How to check specific elements in Set")
  println(s"Element Plain Donut = ${set1("Plain Donut")}")
  println(s"Element Strawberry Donut = ${set1("Strawberry Donut")}")
  println(s"Element Chocolate Donut = ${set1("Chocolate Donut")}")

  println("\nStep 3: How to add elements to Set using +=")
  set1 += "Vanilla Donut"
  println(s"Elements of set1 after adding elements Vanilla Donut = $set1")

  println("\nStep 4: How to add all elements from another Set using ++=")
  set1 ++= mutable.Set[String]("Vanilla Donut", "Glazed Donut")
  println(s"Elements of set1 after adding second set = $set1")

  println("\nStep 5: How to remove element from Set using -=")
  set1 -= "Plain Donut"
  println(s"Elements of set1 without Plain Donut element = $set1")

  println("\nStep 6: How to find the intersection between two Sets using &")
  val set2: mutable.Set[String] = mutable.Set("Vanilla Donut", "Glazed Donut", "Plain Donut")
  println(s"Intersection of set1 and set5 = ${set1 & set2}")

  println("\nStep 7: How to find the difference between two Sets using &~")
  println(s"Difference of set1 and set2 = ${set1 &~ set2}")

  println("\nStep 8: How to initialize an empty Set")
  val emptySet: mutable.Set[String] = mutable.Set.empty[String]
  println(s"Empty Set = $emptySet")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /**
    * What is a HashSet?
    *
    * As per Wikipedia, a Set is a data structure which allows you to store elements which are not repeatable. A Set
    * also does not guarantee the ordering of elements.
    * As per Scala Documentation, a Mutable HashSet is the concrete implementation of the mutable Set trait.
    */
  println("\nStep 1: How to initialize a HashSet with 3 elements")
  val hashSet1: mutable.HashSet[String] = mutable.HashSet("Plain Donut", "Strawberry Donut", "Chocolate Donut")
  println(s"Elements of hashSet1 = $hashSet1")

  println("\nStep 2: How to check specific elements in HashSet")
  println(s"Element Plain Donut = ${hashSet1("Plain Donut")}")
  println(s"Element Strawberry Donut = ${hashSet1("Strawberry Donut")}")
  println(s"Element Chocolate Donut = ${hashSet1("Chocolate Donut")}")

  println("\nStep 3: How to add elements to HashSet using +=")
  hashSet1 += "Vanilla Donut"
  println(s"Elements of hashSet1 after adding Vanilla Donut element = $hashSet1")

  println("\nStep 4: How to add two HashSets together using ++=")
  hashSet1 ++= mutable.HashSet[String]("Vanilla Donut", "Glazed Donut")
  println(s"Elements of hashSet1 after adding another HashSet = $hashSet1")

  println("\nStep 5: How to remove element from HashSet using -=")
  hashSet1 -= "Plain Donut"
  println(s"HashSet without Plain Donut element = $hashSet1")

  println("\nStep 6: How to find the intersection between two HashSet using &")
  val hashSet2: mutable.HashSet[String] = mutable.HashSet("Vanilla Donut", "Glazed Donut", "Plain Donut")
  println(s"Intersection of hashSet1 and hashSet2 = ${hashSet1 & hashSet2}")

  println("\nStep 7: How to find the difference between two HashSets using &~")
  println(s"Difference of hashSet1 and hashSet5 = ${hashSet1 &~ hashSet2}")

  println("\nStep 8: How to initialize an empty HashSet")
  val emptyHashSet: mutable.HashSet[String] = mutable.HashSet.empty[String]
  println(s"Empty HashSet = $emptyHashSet")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /**
    * What is a SortedSet?
    *
    * As per Wikipedia, a Set is a data structure which allows you to store elements which are not repeatable. While a
    * Set also does not guarantee the ordering of elements, a SortedSet will produce elements in a given order.
    * As per Scala Documentation, a SortedSet is a trait which provides the Set semantics but also allows you to drive
    * the ordering of the elements within the SortedSet.
    */
  println("\nStep 1: How to initialize a SortedSet with 3 elements")
  val sortedSet1: mutable.SortedSet[String] = mutable.SortedSet("Plain Donut", "Strawberry Donut", "Chocolate Donut")
  println(s"Elements of sortedSet1 = $sortedSet1")

  println("\nStep 2: How to check specific elements in SortedSet")
  println(s"Element Plain Donut = ${sortedSet1("Plain Donut")}")
  println(s"Element Strawberry Donut = ${sortedSet1("Strawberry Donut")}")
  println(s"Element Chocolate Donut = ${sortedSet1("Chocolate Donut")}")

  println("\nStep 3: How to add elements to SortedSet using +=")
  sortedSet1 += "Vanilla Donut"
  println(s"Elements of sortedSet1 after adding Vanilla Donut element = $sortedSet1")

  println("\nStep 4: How to add two SortedSets together using ++=")
  sortedSet1 ++= mutable.SortedSet[String]("Vanilla Donut", "Glazed Donut")
  println(s"Elements of sortedSet1 after adding second SortedSet = $sortedSet1")

  println("\nStep 5: How to remove element from SortedSet using -=")
  sortedSet1 -= "Plain Donut"
  println(s"sortedSet1 without Plain Donut element = $sortedSet1")

  println("\nStep 6: How to find the intersection between two SortedSets using &")
  val sortedSet2: mutable.SortedSet[String] = mutable.SortedSet("Vanilla Donut", "Glazed Donut", "Plain Donut")
  println(s"Intersection of sortedSet1 and sortedSet5 = ${sortedSet1 & sortedSet2}")

  println("\nStep 7: How to find the difference between two SortedSets using &~")
  println(s"Difference of sortedSet1 and sortedSet5 = ${sortedSet1 &~ sortedSet2}")

  println("\nStep 8: How to change ordering to descending alphabet in SortedSet")

  object DescendingAlphabetOrdering extends Ordering[String] {
    def compare(element1: String, element2: String): Int = element2.compareTo(element1)
  }
  val sortedSet6: mutable.SortedSet[String] = mutable.SortedSet("Plain Donut", "Strawberry Donut", "Chocolate Donut")(DescendingAlphabetOrdering)
  println(s"Elements of sortedSet6 = $sortedSet6")

  println("\nStep 9: How to initialize an empty SortedSet")
  val emptySortedSet: mutable.SortedSet[String] = mutable.SortedSet.empty[String]
  println(s"Empty SortedSet = $emptySortedSet")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /**
    * What is a TreeSet?
    *
    * As per Wikipedia, a Set is a data structure which allows you to store elements which are not repeatable. While a
    * Set also does not guarantee the ordering of elements, a TreeSet will produce elements in a given order.
    * As per Scala Documentation, a SortedSet is a trait which provides the Set semantics but also allows you to drive
    * the ordering of the elements within the SortedSet. A TreeSet is a class implementation of the SortedSet trait and
    * it uses a Red Black Tree as its underlying data structure.
    */
  println("\nStep 1: How to initialize a TreeSet with 3 elements")
  val treeSet1: mutable.TreeSet[String] = mutable.TreeSet("Plain Donut", "Strawberry Donut", "Chocolate Donut")
  println(s"Elements of treeSet1 = $treeSet1")

  println("\nStep 2: How to check specific elements in TreeSet")
  println(s"Element Plain Donut = ${treeSet1("Plain Donut")}")
  println(s"Element Strawberry Donut = ${treeSet1("Strawberry Donut")}")
  println(s"Element Chocolate Donut = ${treeSet1("Chocolate Donut")}")

  println("\nStep 3: How to add elements to TreeSet using +=")
  treeSet1 += "Vanilla Donut"
  println(s"Elements of treeSet1 after adding Vanilla Donut element = $treeSet1")

  println("\nStep 4: How to add two TreeSets together using ++=")
  treeSet1 ++= mutable.TreeSet[String]("Vanilla Donut", "Glazed Donut")
  println(s"Elements of treeSet1 after adding second set = $treeSet1")

  println("\nStep 5: How to remove element from TreeSet using -=")
  treeSet1 -= "Plain Donut"
  println(s"treeSet1 without Plain Donut element = $treeSet1")

  println("\nStep 6: How to find the intersection between two TreeSets using &")
  val treeSet2: mutable.TreeSet[String] = mutable.TreeSet("Vanilla Donut", "Glazed Donut", "Plain Donut")
  println(s"Intersection of treeSet1 and treeSet2 = ${treeSet1 & treeSet2}")

  println("\nStep 7: How to find the difference between two TreeSets using &~")
  println(s"Difference of treeSet1 and treeSet2 = ${treeSet1 &~ treeSet2}")

  println("\nStep 8: How to change ordering to descending alphabet in TreeSet")

  object DescendingAlphabetOrdering1 extends Ordering[String] {
    def compare(element1: String, element2: String): Int = element2.compareTo(element1)
  }
  val treeSet3: mutable.TreeSet[String] = mutable.TreeSet("Plain Donut", "Strawberry Donut", "Chocolate Donut")(DescendingAlphabetOrdering1)
  println(s"Elements of treeSet3 = $treeSet3")

  println("\nStep 9: How to initialize an empty TreeSet")
  val emptyTreeSet: mutable.TreeSet[String] = mutable.TreeSet.empty[String]
  println(s"Empty TreeSet = $emptyTreeSet")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /**
    * What is a LinkedHashSet?
    *
    * As per Wikipedia, a Set is a data structure which allows you to store elements which are not repeatable. A Set
    * also does not guarantee the ordering of elements.
    * As per the Scala Documentation, a LinkedHashSet will produce elements in order they were inserted to the
    * LinkedHashSet. As mentioned in the previous tutorials, TreeSet and SortedSet allow you to drive the order of the
    * elements. TreeSet and SortedSet should not be confused with LinkedHashSet where the order of elements will be
    * according to the insertion order of the elements.
    */
  println("\nStep 1: How to initialize a LinkedHashSet with 3 elements")
  val linkedHashSet1: mutable.LinkedHashSet[String] = mutable.LinkedHashSet("Plain Donut", "Strawberry Donut", "Chocolate Donut")
  println(s"Elements of linkedHashSet1 = $linkedHashSet1")

  println("\nStep 2: How to check specific elements in LinkedHashSet")
  println(s"Element Plain Donut = ${linkedHashSet1("Plain Donut")}")
  println(s"Element Strawberry Donut = ${linkedHashSet1("Strawberry Donut")}")
  println(s"Element Chocolate Donut = ${linkedHashSet1("Chocolate Donut")}")

  println("\nStep 3: How to add elements to LinkedHashSet using +=")
  linkedHashSet1 += "Vanilla Donut"
  println(s"Elements of linkedHashSet1 after adding Vanilla Donut element = $linkedHashSet1")

  println("\nStep 4: How to add two LinkedHashSets together using ++=")
  linkedHashSet1 ++= mutable.LinkedHashSet[String]("Vanilla Donut", "Glazed Donut")
  println(s"Elements of linkedHashSet1 after adding another HashSet = $linkedHashSet1")

  println("\nStep 5: How to remove element from LinkedHashSet using -=")
  linkedHashSet1 -= "Plain Donut"
  println(s"Set without Plain Donut element = $linkedHashSet1")

  println("\nStep 6: How to find the intersection between two LinkedHashSets using &")
  val linkedHashSet2: mutable.LinkedHashSet[String] = mutable.LinkedHashSet("Vanilla Donut", "Glazed Donut", "Plain Donut")
  println(s"Intersection of linkedHashSet1 and linkedHashSet2 = ${linkedHashSet1 & linkedHashSet2}")

  println("\nStep 7: How to find the difference between two LinkedHashSets using &~")
  println(s"Difference of linkedHashSet1 and linkedHashSet2 = ${linkedHashSet1 &~ linkedHashSet2}")

  println("\nStep 8: How to initialize an empty LinkedHashSet")
  val emptyLinkedHashSet: mutable.LinkedHashSet[String] = mutable.LinkedHashSet.empty[String]
  println(s"Empty LinkedHashSet = $emptyLinkedHashSet")

  println("\nStep 9: How to print elements in order inserted to LinkedHashSet using foreach function")
  val linkedHashSet3: mutable.LinkedHashSet[String] = mutable.LinkedHashSet.empty[String]
  linkedHashSet3 += "Vanilla Donut"
  linkedHashSet3 += "Glazed Donut"
  linkedHashSet3 += "Plain Donut"
  linkedHashSet3 += "Chocolate Donut"
  linkedHashSet3.foreach(donut => println(s"$donut"))

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  /**
    * What is a BitSet?
    *
    * As per Wikipedia, a Set is a data structure which allows you to store elements which are not repeatable. A Set
    * also does not guarantee the ordering of elements.
    * As per the Scala Documentation, a BitSet represents a collection of small integers as the bits of a larger integer.
    */
  println("\nStep 1: How to initialize a BitSet")
  val bitSet1: mutable.BitSet = mutable.BitSet(0, 2, 4, 6, 8)
  println(s"Elements of bitSet1 = $bitSet1")

  println("\nStep 2: How to check specific elements in BitSet")
  println(s"Element 0 = ${bitSet1(0)}")
  println(s"Element 1 = ${bitSet1(1)}")
  println(s"Element 2 = ${bitSet1(2)}")

  println("\nStep 3: How to add elements to BitSet using +=")
  bitSet1 += 10
  println(s"Elements of bitSet1 after adding element 10 = $bitSet1")

  println("\nStep 4: How to add two BitSets together using ++=")
  bitSet1 ++= mutable.BitSet(12, 14, 16, 18, 20)
  println(s"Elements of bitSet1 after adding second BitSet = $bitSet1")

  println("\nStep 5: How to remove element from BitSet using -=")
  bitSet1 -= 0
  println(s"bitSet1 without element 0 = $bitSet1")

  println("\nStep 6: How to find the intersection between two BitSets using &")
  val bitSetEven2: mutable.BitSet = mutable.BitSet(6, 8, 10)
  println(s"Intersection of bitSet1 and bitSetEven2 = ${bitSet1 & bitSetEven2}")

  println("\nStep 7: How to find the difference between two BitSets using &~")
  println(s"Difference of bitSet1 and bitSetEven2 = ${bitSet1 &~ bitSetEven2}")

  println("\nStep 8: How to initialize an empty BitSet")
  val emptyBitSet: mutable.BitSet = mutable.BitSet.empty
  println(s"Empty BitSet = $emptyBitSet")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

}
