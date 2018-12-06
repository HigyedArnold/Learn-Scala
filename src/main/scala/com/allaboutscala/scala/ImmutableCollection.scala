package com.allaboutscala.scala

import scala.collection.immutable.Stream.cons
import scala.collection.immutable.{BitSet, HashMap, HashSet, ListMap, ListSet, Queue, SortedSet, Stack, TreeMap, TreeSet}

/**
  * Created by ArnoldHigyed on 16/11/2018
  */
object ImmutableCollection extends App {

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: How to initialize an immutable List with 3 elements")
  val list1: List[String] = List("Plain Donut","Strawberry Donut","Chocolate Donut")
  println(s"Elements of list1 = $list1")

  println("\nStep 2: How to access elements of immutable List at specific index")
  println(s"Element at index 0 = ${list1(0)}")
  println(s"Element at index 1 = ${list1(1)}")
  println(s"Element at index 2 = ${list1(2)}")

  println("\nStep 3: How to append elements at the end of immutable List using :+")
  val list2: List[String] = list1 :+ "Vanilla Donut"
  println(s"Append elements at the end using :+ = $list2")

  println("\nStep 4: How to prepend elements at the front of immutable List using +:")
  val list3: List[String] = "Vanilla Donut" +: list1
  println(s"Prepend elements at the front using +: = $list3")

  println("\nStep 5: How to add two immutable lists together using ::")
  val list4: List[Any] = list1 :: list2
  println(s"Add two lists together using :: = $list4")

  println("\nStep 6: How to add two immutable Lists together using :::")
  val list5: List[String] = list1 ::: list2
  println(s"Add two lists together using ::: = $list5")

  println("\nStep 7: How to initialize an empty immutable List")
  val emptyList: List[String] = List.empty[String]
  println(s"Empty list = $emptyList")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: How to initialize an immutable ListSet with 3 elements")
  val listSet1: ListSet[String] = ListSet("Plain Donut","Strawberry Donut","Chocolate Donut")
  println(s"Elements of listSet1 = $listSet1")

  println("\nStep 2: How to check elements of immutable ListSet")
  println(s"Element Plain Donut = ${listSet1("Plain Donut")}")
  println(s"Element Strawberry Donut = ${listSet1("Strawberry Donut")}")
  println(s"Element Chocolate Donut = ${listSet1("Chocolate Donut")}")

  println("\nStep 3: How to add elements of immutable ListSet using +")
  val listSet2: ListSet[String] = listSet1 + "Vanilla Donut"
  println(s"Adding element Vanilla to ListSet using + = $listSet2")

  println("\nStep 4: How to add two ListSet together using ++")
  val listSet3: ListSet[String] = listSet1 ++ ListSet("Glazed Donut")
  println(s"Add two lists together using ++ = $listSet3")

  println("\nStep 5: How to remove element from the ListSet using -")
  val listSet4: ListSet[String] = listSet1 - ("Plain Donut")
  println(s"ListSet without the element Plain Donut = $listSet4")

  println("\nStep 6: How to initialize an empty ListSet")
  val emptyListSet: ListSet[String] = ListSet.empty[String]
  println(s"Empty ListSet of type String = $emptyListSet")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: How to initialize a ListMap with 3 elements using key -> value notation")
  val listMap1: ListMap[String, String] = ListMap("PD" -> "Plain Donut", "SD" ->"Strawberry Donut", "CD" -> "Chocolate Donut")
  println(s"Elements of listMap1 = $listMap1")

  println("\nStep 2: How to access elements by specific key in the ListMap")
  println(s"Element by key PD = ${listMap1("PD")}")
  println(s"Element by key SD = ${listMap1("SD")}")

  println("\nStep 3: How to add elements to ListMap using +")
  val listMap2: ListMap[String, String] = listMap1 + ("KD" -> "Krispy Kreme Donut")
  println(s"Elements of listMap2 = $listMap2")

  println("\nStep 4: How to add two ListMaps together using ++")
  val listMap3: ListMap[String, String] = listMap1 ++ listMap2
  println(s"Elements of listMap3 = $listMap3")

  println("\nStep 5: How to remove key and value from ListMap using -")
  val listMap4: ListMap[String, String] = listMap1 - ("CD")
  println(s"ListMap without the key CD and its value = $listMap4")

  println("\nStep 6: How to initialize an empty ListMap")
  val emptyListMap: ListMap[String, String] = ListMap.empty[String,String]
  println(s"Empty ListMap with key type String and value also of type String= $emptyListMap")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: How to initialize a Map with 3 elements")
  val map1: Map[String, String] = Map(("PD","Plain Donut"),("SD","Strawberry Donut"),("CD","Chocolate Donut"))
  println(s"Elements of map1 = $map1")

  println("\nStep 2: How to initialize Map using key -> value notation")
  val map2: Map[String, String] = Map("VD"-> "Vanilla Donut", "GD" -> "Glazed Donut")
  println(s"Elements of map1 = $map2")

  println("\nStep 3: How to access elements by specific key")
  println(s"Element by key VD = ${map2("VD")}")
  println(s"Element by key GD = ${map2("GD")}")

  println("\nStep 4: How to add elements using +")
  val map3: Map[String, String] = map1 + ("KD" -> "Krispy Kreme Donut")
  println(s"Element in map3 = $map3")

  println("\nStep 5: How to add two Maps together using ++")
  val map4: Map[String, String] = map1 ++ map2
  println(s"Elements in map4 = $map4")

  println("\nStep 6: How to remove key and its value from map using -")
  val map5: Map[String, String] = map4 - ("CD")
  println(s"Map without the key CD and its value = $map5")

  println("\nStep 7: How to initialize an empty Map")
  val emptyMap: Map[String,String] = Map.empty[String,String]
  println(s"Empty Map = $emptyMap")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: How to initialize a HashMap with 3 elements using Tuples of key and value")
  val hashMap1: HashMap[String, String] = HashMap(("PD","Plain Donut"),("SD","Strawberry Donut"),("CD","Chocolate Donut"))
  println(s"Elements of hashMap1 = $hashMap1")

  println("\nStep 2: How to initialize HashMap using key -> value notation")
  val hashMap2: HashMap[String, String] = HashMap("VD"-> "Vanilla Donut", "GD" -> "Glazed Donut")
  println(s"Elements of hashMap2 = $hashMap2")

  println("\nStep 3: How to access elements in HashMap by specific key")
  println(s"Element by key VD = ${hashMap2("VD")}")
  println(s"Element by key GD = ${hashMap2("GD")}")

  println("\nStep 4: How to add elements to HashMap using +")
  val hashMap3: HashMap[String, String] = hashMap1 + ("KD" -> "Krispy Kreme Donut")
  println(s"Element in hashMap3 = $hashMap3")

  println("\nStep 5: How to add two HashMaps together using ++")
  val hashMap4: Map[String, String] = hashMap1 ++ hashMap2
  println(s"Elements in hashMap4 = $hashMap4")

  println("\nStep 6: How to remove key and its value from HashMap using -")
  val hashMap5: Map[String, String] = hashMap4 - ("CD")
  println(s"HashMap without the key CD and its value = $hashMap5")

  println("\nStep 7: How to initialize an empty HashMap")
  val emptyHashMap: HashMap[String,String] = HashMap.empty[String,String]
  println(s"Empty HashMap = $emptyHashMap")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: How to initialize a TreeMap with 3 elements using Tuples key and value")
  val treeMap1: TreeMap[String, String] = TreeMap(("PD","Plain Donut"),("SD","Strawberry Donut"),("CD","Chocolate Donut"))
  println(s"Elements of treeMap1 = $treeMap1")

  println("\nStep 2: How to initialize TreeMap using key -> value notation")
  val treeMap2: TreeMap[String, String] = TreeMap("VD"-> "Vanilla Donut", "GD" -> "Glazed Donut")
  println(s"Elements of treeMap2 = $treeMap2")

  println("\nStep 3: How to access elements of TreeMap by specific key")
  println(s"Element by key VD = ${treeMap2("VD")}")
  println(s"Element by key GD = ${treeMap2("GD")}")

  println("\nStep 4: How to add elements to TreeMap using +")
  val treeMap3: TreeMap[String, String] = treeMap1 + ("KD" -> "Krispy Kreme Donut")
  println(s"Elements in treeMap3 = $treeMap3")

  println("\nStep 5: How to add two TreeMaps together using ++")
  val treeMap4: TreeMap[String, String] = treeMap1 ++ treeMap2
  println(s"Elements in treeMap4 = $treeMap4")

  println("\nStep 6: How to remove key and its value from TreeMap using -")
  val treeMap5: TreeMap[String, String] = treeMap4 - ("CD")
  println(s"TreeMap without the key CD and its value = $treeMap5")

  println("\nStep 7: How to change ordering of TreeMap to descending alphabet")
  object AlphabetOrdering extends Ordering[String] {
    def compare(key1:String, key2:String) = key2.compareTo(key1)
  }
  val treeMap6: TreeMap[String, String] = TreeMap(("PD","Plain Donut"),("SD","Strawberry Donut"),("CD","Chocolate Donut"))(AlphabetOrdering)
  println(s"Elements of treeMap6 in descending order = $treeMap6")

  println("\nStep 8: How to initialize an empty TreeMap")
  val emptyTreeMap: TreeMap[String,String] = TreeMap.empty[String,String]
  println(s"Empty TreeMap = $emptyTreeMap")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: How to initialize a Queue with 3 elements")
  val queue1: Queue[String] = Queue("Plain Donut", "Strawberry Donut", "Chocolate Donut")
  println(s"Elements of queue1 = $queue1")

  println("\nStep 2: How to access elements at specific index in a Queue")
  println(s"Element at index 0 = ${queue1(0)}")
  println(s"Element at index 0 = ${queue1(1)}")
  println(s"Element at index 0 = ${queue1(2)}")

  println("\nStep 3: How to add elements in a Queue using :+")
  val queue2: Queue[String] = queue1 :+ "Glazed Donut"
  println(s"Elements of queue2 = $queue2")

  println("\nStep 4: How to add elements in Queue using enqueue function")
  val enqueue: Queue[String] = queue1.enqueue("Vanilla Donut")
  println(s"Enqueue element Vanilla Donut onto queue1 = $enqueue")

  println("\nStep 5: How to take the first element from the Queue using dequeue function")
  val dequeue: (String, Queue[String]) = queue1.dequeue
  println(s"First element dequeue = ${dequeue._1}")
  println(s"Remaining elements after dequeue = ${dequeue._2}")

  println("\nStep 6: How to add two Queues together using ++")
  val queue3: Queue[String] = queue1 ++ Queue[String]("Glazed Donut", "Vanilla Donut")
  println(s"Elements in queue3 = $queue3")

  println("\nStep 7: How to initialize an empty Queue")
  val emptyQueue: Queue[String] = Queue.empty[String]
  println(s"Empty Queue = $emptyQueue")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

  println("Step 1: How to initialize a Sequence with 3 elements")
  val seq1: Seq[String] = Seq("Plain Donut","Strawberry Donut","Chocolate Donut")
  println(s"Elements of seq1 = $seq1")

  println("\nStep 2: How to access elements in Sequence at specific index")
  println(s"Element at index 0 = ${seq1(0)}")
  println(s"Element at index 1 = ${seq1(1)}")
  println(s"Element at index 2 = ${seq1(2)}")

  println("\nStep 3: How to add elements to Sequence using :+")
  val seq2: Seq[String] = seq1 :+ "Vanilla Donut"
  println(s"Adding elements to Sequence using :+ = $seq2")

  println("\nStep 4: How to add two Sequence together using ++")
  val seq3: Seq[String] = seq1 ++ Seq[String]("Vanilla Donut", "Glazed Donut")
  println(s"Add two sequences together using ++ = $seq3")

  println("\nStep 5: How to initialize an empty Sequence")
  val emptySeq: Seq[String] = Seq.empty[String]
  println(s"Empty Sequence = $emptySeq")

  println("\n//-----------------------------------------------------------------------------------------------------\n")
  /**
    * What is a Set?
    *
    * As per Wikipedia, a Set is a data structure which allows you to store some values but where the values cannot be
    * repeatable.
    */

  println("Step 1: How to initialize a Set with 3 elements")
  val set1: Set[String] = Set("Plain Donut","Strawberry Donut","Chocolate Donut")
  println(s"Elements of set1 = $set1")

  println("\nStep 2: How to check specific elements exists in Set")
  println(s"Element Plain Donut = ${set1("Plain Donut")}")
  println(s"Element Strawberry Donut = ${set1("Strawberry Donut")}")
  println(s"Element Chocolate Donut = ${set1("Chocolate Donut")}")

  println("\nStep 3: How to add elements in Set using +")
  val set2: Set[String] = set1 + "Vanilla Donut" + "Vanilla Donut"
  println(s"Adding elements to Set using + = $set2")

  println("\nStep 4: How to add two Sets together using ++")
  val set3: Set[String] = set1 ++ Set[String]("Vanilla Donut", "Glazed Donut")
  println(s"Add two Sets together using ++ = $set3")

  println("\nStep 5: How to remove element from Set using -")
  val set4: Set[String] = set1 - "Plain Donut"
  println(s"Set without Plain Donut element = $set4")

  println("\nStep 6: How to find the intersection between two Sets using &")
  val set5: Set[String] = Set("Vanilla Donut", "Glazed Donut", "Plain Donut")
  println(s"Intersection of set1 and set5 = ${set1 & set5}")

  println("\nStep 7: How to find the difference between two Sets using &~")
  println(s"Difference between set1 and set5 = ${set1 &~ set5}")

  println("\nStep 8: How to initialize an empty Set")
  val emptySet: Set[String] = Set.empty[String]
  println(s"Empty Set = $emptySet")

  println("\n//-----------------------------------------------------------------------------------------------------\n")
  /**
    * What is a HashSet?
    *
    * In Scala, a HashSet is a concrete implementation of Set semantics. The HashSet will use the element's hashCode
    * as a key to allow for fast lookup of the element's value within the HashSet.
    * The internal data structure of the HashSet in Scala is a HashTrie and this is different to a HashTable if you
    * come from a Java background. You can learn more about HashTrie from Wikipedia.
    */

  println("Step 1: How to initialize a HashSet with 3 elements")
  val hashSet1: HashSet[String] = HashSet("Plain Donut","Strawberry Donut","Chocolate Donut")
  println(s"Elements of hashSet1 = $hashSet1")

  println("\nStep 2: How to check specific elements in HashSet")
  println(s"Element Plain Donut = ${hashSet1("Plain Donut")}")
  println(s"Element Strawberry Donut = ${hashSet1("Strawberry Donut")}")
  println(s"Element Chocolate Donut = ${hashSet1("Chocolate Donut")}")

  println("\nStep 3: How to add elements in HashSet using +")
  val hashSet2: HashSet[String] = hashSet1 + "Vanilla Donut" + "Vanilla Donut"
  println(s"Adding elements to HashSet using + = $hashSet2")
  // NOTE: we only have one Vanilla Donut element and not two as HashSet is distinct

  println("\nStep 4: How to add two HashSets together using ++")
  val hashSet3: HashSet[String] = hashSet1 ++ HashSet[String]("Vanilla Donut", "Glazed Donut")
  println(s"Add two HashSets together using ++ = $hashSet3")

  println("\nStep 5: How to remove element in HashSet using -")
  val hashSet4: HashSet[String] = hashSet1 - "Plain Donut"
  println(s"HashSet without Plain Donut element = $hashSet4")

  println("\nStep 6: How to find the intersection between two HashSets using &")
  val hashSet5: HashSet[String] = HashSet("Vanilla Donut", "Glazed Donut", "Plain Donut")
  println(s"Intersection of hashSet1 and hashSet5 = ${hashSet1 & hashSet5}")

  println("\nStep 7: How to find the difference between two HashSets using &~")
  println(s"Difference of hashSet1 and hashSet5 = ${hashSet1 &~ hashSet5}")

  println("\nStep 8: How to initialize an empty HashSet")
  val emptyHashSet: HashSet[String] = HashSet.empty[String]
  println(s"Empty HashSet = $emptyHashSet")

  println("\n//-----------------------------------------------------------------------------------------------------\n")
  /**
    * What is a TreeSet?
    *
    * Following on from the previous tutorials on HashSet and Set, a TreeSet allows you to store unique elements but
    * also provides ordering of the elements.
    * As per the Scala documentation, TreeSet internally uses a Red-Back data structure to ensure a balanced tree that
    * will provide O(log n) for most operations.
    */

  println("Step 1: How to initialize a TreeSet with 3 elements")
  val treeSet1: TreeSet[String] = TreeSet("Plain Donut","Strawberry Donut","Chocolate Donut")
  println(s"Elements of treeSet1 = $treeSet1")

  println("\nStep 2: How to check specific elements in TreeSet")
  println(s"Element Plain Donut = ${treeSet1("Plain Donut")}")
  println(s"Element Strawberry Donut = ${treeSet1("Strawberry Donut")}")
  println(s"Element Chocolate Donut = ${treeSet1("Chocolate Donut")}")

  println("\nStep 3: How to add elements to TreeSet using +")
  val treeSet2: TreeSet[String] = treeSet1 + "Vanilla Donut" + "Vanilla Donut"
  println(s"Adding elements to TreeSet using + = $treeSet2")
  // NOTE: we only have one Vanilla Donut element and not two as sets are distinct

  println("\nStep 4: How to add two TreeSets together using ++")
  val treeSet3: TreeSet[String] = treeSet1 ++ TreeSet[String]("Vanilla Donut", "Glazed Donut")
  println(s"Add two TreeSets together using ++ = $treeSet3")

  println("\nStep 5: How to remove element in TreeSet using -")
  val treeSet4: TreeSet[String] = treeSet1 - "Plain Donut"
  println(s"TreeSet without Plain Donut element = $treeSet4")

  println("\nStep 6: How to find the intersection between two TreeSets using &")
  val treeSet5: TreeSet[String] = TreeSet("Vanilla Donut", "Glazed Donut", "Plain Donut")
  println(s"Intersection of treeSet1 and treeSet5 = ${treeSet1 & treeSet5}")

  println("\nStep 7: How to find the difference between two TreeSets using &~")
  println(s"Difference of treeSet1 and treeSet5 = ${treeSet1 &~ treeSet5}")

  println("\nStep 8: How to change ordering of TreeSet to descending alphabet")
  object AlphabetOrdering1 extends Ordering[String] {
    def compare(element1:String, element2:String) = element2.compareTo(element1)
  }
  val treeSet6: TreeSet[String] = TreeSet("Plain Do" +
    "" +
    "nut", "Strawberry Donut", "Chocolate Donut")(AlphabetOrdering1)
  println(s"Elements of treeSet6 = $treeSet6")

  println("\nStep 9: How to initialize an empty TreeSet")
  val emptyTreeSet: TreeSet[String] = TreeSet.empty[String]
  println(s"Empty TreeSet = $emptyTreeSet")

  println("\n//-----------------------------------------------------------------------------------------------------\n")
  /**
    * What is a SortedSet?
    *
    * Following on from the previous tutorials on Set, HashSet, and TreeSet, a SortedSet allows you to store unique
    * elements but also provides ordering of the elements.
    * As per the Scala documentation, SortedSet is a trait. Its concrete class implementation is a TreeSet which we
    * discussed in the previous tutorial.
    */

  println("Step 1: How to initialize a SortedSet with 3 elements")
  val sortedSet1: SortedSet[String] = SortedSet("Plain Donut","Strawberry Donut","Chocolate Donut")
  println(s"Elements of sortedSet1 = $sortedSet1")

  println("\nStep 2: How to check specific elements in SortedSet")
  println(s"Element Plain Donut = ${sortedSet1("Plain Donut")}")
  println(s"Element Strawberry Donut = ${sortedSet1("Strawberry Donut")}")
  println(s"Element Chocolate Donut = ${sortedSet1("Chocolate Donut")}")

  println("\nStep 3: How to add elements to SortedSet using +")
  val sortedSet2: SortedSet[String] = sortedSet1 + "Vanilla Donut" + "Vanilla Donut"
  println(s"Adding elements to SortedSet using + = $sortedSet2")
  // NOTE: we only have one Vanilla Donut element and not two as SortedSet is distinct

  println("\nStep 4: How to add two SortedSets together using ++")
  val sortedSet3: SortedSet[String] = sortedSet1 ++ SortedSet[String]("Vanilla Donut", "Glazed Donut")
  println(s"Add two SortedSets together using ++ = $sortedSet3")

  println("\nStep 5: How to remove element in SortedSet using -")
  val sortedSet4: SortedSet[String] = sortedSet1 - "Plain Donut"
  println(s"SortedSet without Plain Donut element = $sortedSet4")

  println("\nStep 6: How to find the intersection between two SortedSets using &")
  val sortedSet5: SortedSet[String] = SortedSet("Vanilla Donut", "Glazed Donut", "Plain Donut")
  println(s"Intersection of sortedSet1 and sortedSet5 = ${sortedSet1 & sortedSet5}")

  println("\nStep 7: How to find the difference between two SortedSets using &~")
  println(s"Difference of sortedSet1 and sortedSet5 = ${sortedSet1 &~ sortedSet5}")

  println("\nStep 8: How to change ordering of SortedSet to descending alphabet")
  object AlphabetOrdering2 extends Ordering[String] {
    def compare(element1:String, element2:String) = element2.compareTo(element1)
  }
  val sortedSet6: SortedSet[String] = SortedSet("Plain Donut", "Strawberry Donut", "Chocolate Donut")(AlphabetOrdering2)
  println(s"Elements of sortedSet6 = $sortedSet6")
  // NOTE: The elements are now printed in descending order first with Strawberry Donut, then Plain Donut and finally Chocolate Donut

  println("\nStep 9: How to initialize an empty SortedSet")
  val emptySortedSet: SortedSet[String] = SortedSet.empty[String]
  println(s"Empty SortedSet = $emptySortedSet")

  println("\n//-----------------------------------------------------------------------------------------------------\n")
  /**
    * Following on from the previous tutorials on Set, HashSet, TreeSet, and SortedSet, a BitSet also allows you to
    * store unique elements with no repeatable values.
    * As per the Scala documentation, a BitSet represents a collection of small integers as the bits of a larger integer.
    */

  println("Step 1: How to initialize a BitSet with 3 elements")
  val bitSet1: BitSet = BitSet(3, 2, 0)
  println(s"Elements of bitSet1 = $bitSet1")

  println("\nStep 2: How to check specific elements in BitSet")
  println(s"Element 0 = ${bitSet1(0)}")
  println(s"Element 2 = ${bitSet1(2)}")
  println(s"Element 3 = ${bitSet1(3)}")

  println("\nStep 3: How to add elements in BitSet using +")
  val bitSet2: BitSet = bitSet1 + 13 + 13
  println(s"Adding elements to BitSet using + = $bitSet2")
  // NOTE: we only have one 13 element and not two as BitSet is distinct

  println("\nStep 4: How to add two BitSets together using ++")
  val bitSet3: BitSet = bitSet1 ++ BitSet(13, 14, 15, 16, 17)
  println(s"Add two BitSets together using ++ = $bitSet3")

  println("\nStep 5: How to remove element in BitSet using -")
  val bitSet4: BitSet = bitSet1 - 0
  println(s"BitSet without element 0 = $bitSet4")

  println("\nStep 6: How to find the intersection between two BitSets using &")
  val bitSet5: BitSet = BitSet(0, 2, 4)
  println(s"Intersection of bitSet1 and bitSet5 = ${bitSet1 & bitSet5}")

  println("\nStep 7: How to find the difference between two BitSets using &~")
  println(s"Difference of bitSet1 and bitSet5 = ${bitSet1 &~ bitSet5}")

  println("\nStep 8: How to initialize an empty BitSet")
  val emptyBitSet: BitSet = BitSet.empty
  println(s"Empty BitSet = $emptyBitSet")

  println("\n//-----------------------------------------------------------------------------------------------------\n")
  /**
    * What is a Stack?
    *
    * As per Wikipedia, a Stack is a data structure which follows the LIFO (Last In First Out) semantics.
    *
    * It typically provides a push() method to add element at the top of the Stack and a pop() method to take the
    * most recently added element from the top of the Stack.
    */

  println("Step 1: How to initialize Stack with 3 elements")
  val stack1: Stack[String] = Stack("Plain Donut", "Strawberry Donut", "Chocolate Donut")
  println(s"Elements of stack1 = $stack1")

  println("\nStep 2: How to initialize a Stack using an Immutable List")
  val stack2: List[String] = List("Plain Donut","Strawberry Donut","Chocolate Donut")
  println(s"Using an Immutable List for stack, elements are = $stack2")

  println("\nStep 3: Push one element at the top of the stack using :: of Immutable List")
  val stack3: List[String] = "Vanilla Donut" :: stack2
  println(s"Using an Immutable List for stack, elements after push = $stack3")

  println("\nStep 4: Push N elements at the top of the stack using :: of Immutable List")
  val stack4: List[String] = "Glazed Donut" :: "Vanilla Donut" :: stack2
  println(s"Using an Immutable List for stack, elements after pushing N elements = $stack4")

  println("\nStep 5: Pop element from the Stack using tail function of Immutable List")
  val stack5: List[String] = stack2.tail
  println(s"Using an Immutable List for stack, elements after tail function to simulate Stack pop = $stack5")

  println("\nStep 6: How to initialize an empty Stack using Immutable List")
  val emptyStack: List[String] = List.empty[String]
  println(s"Using an Immutable List for stack, the empty stack = $emptyStack")

  println("\n//-----------------------------------------------------------------------------------------------------\n")
  /**
    * What is a Stream?
    *
    * As per the Scala Documentation, a Stream is a similar data structure to a list except that the elements of the
    * Stream will be lazily computer. As a result, you can have infinitely long Streams!
    */

  println("Step 1: How to create a Stream with 3 numbers using #::")
  val stream1: Stream[Int] = 1 #:: 2 #:: 3 #:: Stream.empty[Int]
  println(s"Elements of stream1 = $stream1")

  println("\nStep 2: How to create a Stream with 3 numbers using Stream.cons")
  val stream2: Stream[Int] = cons(1, cons(2, cons(3, Stream.empty) ) )
  println(s"Elements of stream2 = ${stream2}")

  println("\nStep 3: How to print all 3 numbers from stream2 using the take function")
  print("Take first 3 numbers from stream2 = ")
  stream2.take(3).print

  print("\nTake first 10 numbers from stream2 = ")
  stream2.take(10).print

  println("\n\nStep 4: How to define an infinite Stream of numbers using Stream.cons")
  def inifiniteNumberStream(number: Int): Stream[Int] = Stream.cons(number, inifiniteNumberStream(number + 1))
  print("Take only the first 20 numbers from the infinite number stream = ")
  inifiniteNumberStream(1).take(20).print

  println("\n\nStep 5: How to define an infinite stream of numbers using Stream.from")
  val stream3: Stream[Int] = Stream.from(1)
  print("Take only the first 20 numbers from the infinite number stream = ")
  stream3.take(20).print

  println("\n\nStep 6: How to initialize an empty Stream")
  val emptyStream: Stream[Int] = Stream.empty[Int]
  println(s"Empty Stream = $emptyStream")

  println("\n//-----------------------------------------------------------------------------------------------------\n")
  /**
    * What is a Vector?
    *
    * As per the Scala Documentation, a Vector is data structure which is similar to a List. However, it addresses the
    * inefficiencies of random access within a List.
    */

  println("Step 1: How to initialize a Vector with 3 elements")
  val vector1: Vector[String] = Vector("Plain Donut", "Strawberry Donut", "Chocolate Donut")
  println(s"Elements of vector1 = $vector1")

  println("\nStep 2: How to access elements of Vector at specific index")
  println(s"Element at index 0 = ${vector1(0)}")
  println(s"Element at index 1 = ${vector1(1)}")
  println(s"Element at index 2 = ${vector1(2)}")

  println("\nStep 3: How to append elements at the end of Vector using :+")
  val vector2 = vector1 :+ "Vanilla Donut"
  println(s"Adding elements to Vector using :+ = $vector2")

  println("\nStep 4: How to prepend elements in front of Vector using +:")
  val vector3 = "Vanilla Donut" +: vector1
  println(s"Adding elements to Vector using :+ = $vector3")

  println("\nStep 5: How to add two Vectors together using ++")
  val vector4 = vector1 ++ Vector[String]("Glazed Donut")
  println(s"Add two vectors together using ++ = $vector3")
  // NOTE: this return a new Vector(...), elements from the second vector)

  println("\nStep 6: How to initialize an empty Vector")
  val emptyVector: Vector[String] = Vector.empty[String]
  println(s"Empty vector of type String = $emptyVector")

  println("\n//-----------------------------------------------------------------------------------------------------\n")

}