package com.aibuild.scalatest.fixtures

import java.util.concurrent.ConcurrentHashMap

/**
  * Created by ArnoldHigyed on 5/12/2018
  */
object DbServer { // Simulating a database server

  type Db = StringBuffer
  private val databases = new ConcurrentHashMap[String, Db]

  def createDb(name: String): Db = {
    val db = new StringBuffer
    databases.put(name, db)
    db
  }

  def removeDb(name: String) {
    databases.remove(name)
  }

}
