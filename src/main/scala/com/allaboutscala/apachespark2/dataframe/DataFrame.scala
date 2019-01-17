package com.allaboutscala.apachespark2.dataframe

import com.allaboutscala.apachespark2.utils.Context

/**
  * Created by ArnoldHigyed on 17/01/2019
  */
object DataFrame extends App with Context {

  // Create a DataFrame from reading a CSV file
  val dfTags = sparkSession
    .read
    .option("header", "true")
    .option("inferSchema","true")
    .csv("src/main/resources/question_tags_10K.csv")
    .toDF("id", "tag")

  dfTags.show(10)

  dfTags.printSchema()

  // Query dataframe: select columns from a dataframe
  dfTags.select("id", "tag").show(10)

  // DataFrame Query: filter by column value of a dataframe
  dfTags.filter("tag == 'php'").show(10)

  // DataFrame Query: count rows of a dataframe
  println(s"Number of php tags = ${ dfTags.filter("tag == 'php'").count() }")

  // DataFrame Query: SQL like query
  dfTags.filter("tag like 's%'").show(10)

  // DataFrame Query: Multiple filter chaining
  dfTags
    .filter("tag like 's%'")
    .filter("id == 25 or id == 108")
    .show(10)

  // DataFrame Query: SQL IN clause
  dfTags.filter("id in (25, 108)").show(10)

  // DataFrame Query: SQL Group By
  println("Group by tag value")
  dfTags.groupBy("tag").count().show(10)

  // DataFrame Query: SQL Group By with filter
  dfTags.groupBy("tag").count().filter("count > 5").show(10)

  // DataFrame Query: SQL order by
  dfTags.groupBy("tag").count().filter("count > 5").orderBy("tag").show(10)

  // DataFrame Query: Cast columns to specific data type
  val dfQuestionsCSV = sparkSession
    .read
    .option("header", "true")
    .option("inferSchema", "true")
    .option("dateFormat","yyyy-MM-dd HH:mm:ss")
    .csv("src/main/resources/questions_10K.csv")
    .toDF("id", "creation_date", "closed_date", "deletion_date", "score", "owner_userid", "answer_count")

  val dfQuestions = dfQuestionsCSV.select(
    dfQuestionsCSV.col("id").cast("integer"),
    dfQuestionsCSV.col("creation_date").cast("timestamp"),
    dfQuestionsCSV.col("closed_date").cast("timestamp"),
    dfQuestionsCSV.col("deletion_date").cast("date"),
    dfQuestionsCSV.col("score").cast("integer"),
    dfQuestionsCSV.col("owner_userid").cast("integer"),
    dfQuestionsCSV.col("answer_count").cast("integer")
  )

  dfQuestions.printSchema()
  dfQuestions.show(10)

  //UNTIL: DataFrame Query: Operate on a filtered dataframe

}
