package com.aibuild.autoschema

import java.time.temporal.ChronoUnit
import java.time.{LocalDate, LocalDateTime}

object TimeTests extends App {

  /**
    * Use 'ChronoUnit.MINUTES.between' only on LocalDateTime type. LocalDate type throws exception!
    */
  def getMinutesInBetween(startInclusive: LocalDateTime, endExclusive: LocalDateTime): Int =
    ChronoUnit.MINUTES.between(startInclusive, endExclusive).toInt

  def getDaysInBetween(startInclusive: LocalDate, endExclusive: LocalDate): Int =
    ChronoUnit.DAYS.between(startInclusive, endExclusive).toInt

  def getDaysInBetween(startInclusive: LocalDateTime, endExclusive: LocalDateTime): Int =
    ChronoUnit.DAYS.between(startInclusive, endExclusive).toInt

  val dateTime1 = LocalDateTime.of(2020, 1, 21, 10, 0)
  val dateTime2 = LocalDateTime.of(2020, 1, 22, 10, 0)
  val date1     = LocalDate.of(2020,     1, 21)
  val date2     = LocalDate.of(2020,     1, 22)

  println(getMinutesInBetween(dateTime1, dateTime1))
  println(getMinutesInBetween(dateTime1, dateTime2))
  println(getMinutesInBetween(dateTime2, dateTime1))

  println(getDaysInBetween(dateTime1, dateTime1))
  println(getDaysInBetween(dateTime1, dateTime2))
  println(getDaysInBetween(dateTime2, dateTime1))

  println(getDaysInBetween(date1, date1))
  println(getDaysInBetween(date1, date2))
  println(getDaysInBetween(date2, date1))

}
