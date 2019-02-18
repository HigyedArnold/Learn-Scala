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
  sparkMllib,

  "com.typesafe.akka" %% "akka-actor" % "2.5.19",
  "com.typesafe.akka" %% "akka-stream" % "2.5.19",
  "com.typesafe.akka" %% "akka-http" % "10.1.6",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.6",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.1.6" % "test",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.19" % Test,
  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.scalatest" %% "scalatest" % "3.0.1" % Test,

  "org.typelevel" %% "cats-core" % "1.4.0"
)

/**
  * tpolecat's glorious compile flag list:
  * https://tpolecat.github.io/2017/04/25/scalac-flags.html
  */
//"-deprecation",                      // Emit warning and location for usages of deprecated APIs.
//"-encoding", "utf-8",                // Specify character encoding used by source files.
//"-explaintypes",                     // Explain type errors in more detail.
//"-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
//"-language:existentials",            // Existential types (besides wildcard types) can be written and inferred
//"-language:experimental.macros",     // Allow macro definition (besides implementation and application)
//"-language:higherKinds",             // Allow higher-kinded types
//"-language:implicitConversions",     // Allow definition of implicit functions called views
//"-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
//"-Xcheckinit",                       // Wrap field accessors to throw an exception on uninitialized access.
//"-Xfatal-warnings",                  // Fail the compilation if there are any warnings.
//"-Xfuture",                          // Turn on future language features.
//"-Xlint:adapted-args",               // Warn if an argument list is modified to match the receiver.
//"-Xlint:by-name-right-associative",  // By-name parameter of right associative operator.
//"-Xlint:constant",                   // Evaluation of a constant arithmetic expression results in an error.
//"-Xlint:delayedinit-select",         // Selecting member of DelayedInit.
//"-Xlint:doc-detached",               // A Scaladoc comment appears to be detached from its element.
//"-Xlint:inaccessible",               // Warn about inaccessible types in method signatures.
//"-Xlint:infer-any",                  // Warn when a type argument is inferred to be `Any`.
//"-Xlint:missing-interpolator",       // A string literal appears to be missing an interpolator id.
//"-Xlint:nullary-override",           // Warn when non-nullary `def f()' overrides nullary `def f'.
//"-Xlint:nullary-unit",               // Warn when nullary methods return Unit.
//"-Xlint:option-implicit",            // Option.apply used implicit view.
//"-Xlint:package-object-classes",     // Class or object defined in package object.
//"-Xlint:poly-implicit-overload",     // Parameterized overloaded implicit methods are not visible as view bounds.
//"-Xlint:private-shadow",             // A private field (or class parameter) shadows a superclass field.
//"-Xlint:stars-align",                // Pattern sequence wildcard must align with sequence component.
//"-Xlint:type-parameter-shadow",      // A local type parameter shadows a type already in scope.
//"-Xlint:unsound-match",              // Pattern match may not be typesafe.
//"-Yno-adapted-args",                 // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
//"-Ypartial-unification",             // Enable partial unification in type constructor inference
//"-Ywarn-dead-code",                  // Warn when dead code is identified.
//"-Ywarn-extra-implicit",             // Warn when more than one implicit parameter section is defined.
//"-Ywarn-inaccessible",               // Warn about inaccessible types in method signatures.
//"-Ywarn-infer-any",                  // Warn when a type argument is inferred to be `Any`.
//"-Ywarn-nullary-override",           // Warn when non-nullary `def f()' overrides nullary `def f'.
//"-Ywarn-nullary-unit",               // Warn when nullary methods return Unit.
//"-Ywarn-numeric-widen",              // Warn when numerics are widened.
//"-Ywarn-unused:implicits",           // Warn if an implicit parameter is unused.
//"-Ywarn-unused:imports",             // Warn if an import selector is not referenced.
//"-Ywarn-unused:locals",              // Warn if a local definition is unused.
//"-Ywarn-unused:params",              // Warn if a value parameter is unused.
//"-Ywarn-unused:patvars",             // Warn if a variable bound in a pattern is unused.
//"-Ywarn-unused:privates",            // Warn if a private member is unused.
//"-Ywarn-value-discard"               // Warn when non-Unit expression results are unused.

scalacOptions ++= Seq(
  "-encoding", "UTF-8",   // source files are in UTF-8
  "-deprecation",         // warn about use of deprecated APIs
  "-unchecked",           // warn about unchecked type parameters
  "-feature",             // warn about misused language features
  "-language:higherKinds",// allow higher kinded types without `import scala.language.higherKinds`
  "-Xlint",               // enable handy linter warnings
//  "-Xfatal-warnings",     // turn compiler warnings into errors
  "-Ypartial-unification" // allow the compiler to unify type constructors of different arities
)

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.3")