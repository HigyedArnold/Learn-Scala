name := "allaboutscala"

version := "0.1"

scalaVersion := "2.12.7"

val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.8.0"
val logback = "ch.qos.logback" %  "logback-classic" % "1.1.7"
val jodaTime = "joda-time" % "joda-time" % "2.9.3"
val jodaConvert = "org.joda" % "joda-convert" % "1.8"

val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1"

val akkaActor = "com.typesafe.akka" %% "akka-actor" % "2.5.12"
val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % "2.5.12"

val sparkCore = "org.apache.spark" %% "spark-core" % "2.4.0" % "provided"
val sparkSQL = "org.apache.spark" %% "spark-sql" % "2.4.0"
val sparkMllib = "org.apache.spark" %% "spark-mllib" % "2.4.0"

libraryDependencies ++= Seq (
  scalaLogging,
  logback,
  jodaTime,
  jodaConvert,
  scalaTest,
  akkaActor,
  akkaTestkit,
  sparkCore,
  sparkSQL,
  sparkMllib
)