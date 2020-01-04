package com.aibuild.autoschema

object HackerRank extends App {
  //  Warm-up Challenges

  // Sock Merchant
  // Complete the sockMerchant function below.
  def sockMerchant(n: Int, ar: Array[Int]): Int =
    ar.groupBy(identity).mapValues(_.length).values.toList.map(_ / 2).sum

  // Counting Valleys
  // Complete the countingValleys function below.
  def countingValleys(n: Int, s: String): Int = {
    var seaLevel    = 0
    var valleyCount = 0
    for (i <- 0 until n) {
      if (s(i) == 'U') {
        seaLevel += 1
        if (seaLevel == 0) valleyCount += 1
      }
      else if (s(i) == 'D') seaLevel -= 1
      else println("Invalid character!")
    }
    valleyCount
  }

  //  Jumping on the Clouds
  // Complete the jumpingOnClouds function below.
  def jumpingOnClouds(c: Array[Int]): Int =
    jump(0, c.length - 1, c, 0)

  @scala.annotation.tailrec
  def jump(index: Int, end: Int, cloud: Array[Int], jumps: Int): Int = {
    if (index == end) jumps
    else {
      if ((index + 2 <= end) && cloud(index + 2) == 0) jump(index + 2, end, cloud, jumps + 1)
      else jump(index + 1,                                             end, cloud, jumps + 1)
    }
  }

  //  Repeated String
  // Complete the repeatedString function below.
  def repeatedString(s: String, n: Long): Long = {
    val slenght = s.length
    val rep     = n / slenght
    val rem     = n % slenght
    val as      = s.map(c => if (c == 'a') 1 else 0).sum
    rep * as + s.take(rem.toInt).map(c => if (c == 'a') 1 else 0).sum
  }

  // BlueWire interview
  def ransom(magazine: String, note: String): Boolean = {
    val magazineList = magazine.split(" ")
    val noteList     = note.split(" ")

    val magazineMap = magazineList.groupBy(identity).mapValues(_.length)
    val noteMap     = noteList.groupBy(identity).mapValues(_.length)

    noteMap.keys.toList
      .map(key => {
        val magazineCount = magazineMap.getOrElse(key, -1)
        val noteCount     = noteMap(key)
        if (magazineCount != -1 && noteCount <= magazineCount) true
        else false
      })
      .forall(identity)
  }
}
