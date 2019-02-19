package apachespark2

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Created by ArnoldHigyed on 17/01/2019
  */
trait Context {

  lazy val sparkConf = new SparkConf()
    .setAppName("Learn Spark")
    .setMaster("local[*]")
    .set("spark.cores.max", "2")

  lazy val sparkSession = SparkSession
    .builder()
    .config(sparkConf)
    .getOrCreate()
}
