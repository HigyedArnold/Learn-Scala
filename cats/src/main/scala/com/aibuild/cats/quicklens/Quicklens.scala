package com.aibuild.cats.quicklens

import com.softwaremill.quicklens._

/**
  * Created by ArnoldHigyed on 01/11/2019
  */
object Quicklens extends App {

  val user = User(
    id = 1,
    name = Name(
      firstName = "Arnold",
      lastName  = "Higyed"
    ),
    billingInfo = BillingInfo(
      creditCards = Seq(
        CreditCard(
          name   = Name("Arnold", "Higyed"),
          number = "1111111111111111",
          month  = 6,
          year   = 2021,
          cvv    = "666"
        )
      )
    ),
    phone = "111-111-1111",
    email = "ah@example.com"
  )

  val u = user
    .modify(_.phone)
    .setTo("000-000-0000")
    .modify(_.email)
    .setTo("test@example.com")
    .modify(_.name.firstName)
    .setTo("Test")
    .modify(_.billingInfo.creditCards.each.month)
    .setTo(12)
  println(u)

}
