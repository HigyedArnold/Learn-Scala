package com.aibuild.allaboutscala.apachespark2

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Created by ArnoldHigyed on 17/01/2019
  */
trait Context {

  lazy val sparkConf: SparkConf = new SparkConf()
    .setAppName("Learn Spark")
    .setMaster("local[*]")
    .set("spark.cores.max", "2")

  lazy val sparkSession: SparkSession = SparkSession
    .builder()
    .config(sparkConf)
    .getOrCreate()
}
