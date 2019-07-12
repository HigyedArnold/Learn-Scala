/**
  * This project only aggregates other sub-projects, it never depends on them.
  * .aggregate simply propagates tasks like compile, test, and whatnot downstream.
  * .dependsOn signifies a direct source dependency between two modules.
  *
  */
lazy val root = Project(id = "Learn-Scala", base = file("."))
  .settings(commonSettings)
  .aggregate(
  )

/**
  * [[project]] is a macro that by default creates a
  * [[Project]] with id the name of the variable on the left hand side,
  * and expects a subfolder with the same name.
  * It's equivalent to this:
  * {{{
  *   Project(id = "core", base = file("core"))
  * }}}
  */
lazy val cats = project
  .settings(commonSettings)
  .settings(
    name in ThisProject := "cats",
    libraryDependencies ++= Seq(
      catsCore   withSources ()
    )
  )

lazy val commons = project
  .settings(commonSettings)
  .settings(
    name in ThisProject := "commons",
    libraryDependencies ++= Seq(
      catsCore   withSources (),
      catsEffect withSources ()
    )
  )

lazy val chessproblem = project
  .settings(commonSettings)
  .settings(
    name in ThisProject := "chessproblem",
    libraryDependencies ++= Seq(

    )
  )

lazy val allaboutscala = project
  .settings(commonSettings)
  .settings(
    name in ThisProject := "allaboutscala",
    libraryDependencies ++= Seq(
      logging withSources (),
      sparkCore withSources (),
      sparkSQL withSources (),
      sparkMllib withSources (),
      akkaActor withSources (),
      akkaStream withSources (),
      akkTestKit withSources (),
      akkaHttp withSources (),
      akkaHSpray withSources (),
      akkaHTest withSources (),
      scalaTest withSources ()
    )
  )

lazy val scalatest = project
  .settings(commonSettings)
  .settings(
    name in ThisProject := "scalatest",
    libraryDependencies ++= Seq(
      scalaTest withSources ()
    )
  )

lazy val fpinscala = project
  .settings(commonSettings)
  .settings(
    name in ThisProject := "fpinscala",
    libraryDependencies ++= Seq(
    )
  )

//=============================================================================
//=============================================================================
//=============================== DEPENDENCIES ================================
//=============================================================================
//=============================================================================
//you can also make your project cross-compile
lazy val mainScalaVersion:    String = "2.12.8"
lazy val shapelessVersion:    String = "2.3.3"
lazy val catsVersion:         String = "1.6.0"
lazy val catsEffectVersion:   String = "1.2.0"
lazy val fs2Version:          String = "1.0.3"
lazy val sparkVersion:        String = "2.4.0"
lazy val akkaVersion:         String = "2.5.23"
lazy val akkaHttpVersion:     String = "10.1.8"
//LOGGING
lazy val scalaLoggingVersion: String = "3.8.0"
//TESTING
lazy val scalaTestVersion:    String = "3.0.5"

lazy val catsCore:    ModuleID = "org.typelevel"      %% "cats-core"            % catsVersion
lazy val catsMacros:  ModuleID = "org.typelevel"      %% "cats-macros"          % catsVersion
lazy val catsKernel:  ModuleID = "org.typelevel"      %% "cats-kernel"          % catsVersion
lazy val catsLaws:    ModuleID = "org.typelevel"      %% "cats-laws"            % catsVersion
lazy val catsTestkit: ModuleID = "org.typelevel"      %% "cats-testkit"         % catsVersion

lazy val catsEffect:  ModuleID = "org.typelevel"      %% "cats-effect"          % catsEffectVersion
lazy val fs2:         ModuleID = "co.fs2"             %% "fs2-core"             % fs2Version

lazy val shapeless:   ModuleID = "com.chuusai"        %% "shapeless"            % shapelessVersion

lazy val sparkCore:   ModuleID = "org.apache.spark"   %% "spark-core"           % sparkVersion % "provided"
lazy val sparkSQL:    ModuleID = "org.apache.spark"   %% "spark-sql"            % sparkVersion
lazy val sparkMllib:  ModuleID = "org.apache.spark"   %% "spark-mllib"          % sparkVersion

lazy val akkaActor:   ModuleID = "com.typesafe.akka" %% "akka-actor"            % akkaVersion
lazy val akkaStream:  ModuleID = "com.typesafe.akka" %% "akka-stream"           % akkaVersion
lazy val akkTestKit:  ModuleID = "com.typesafe.akka" %% "akka-testkit"          % akkaVersion
lazy val akkaHttp:    ModuleID = "com.typesafe.akka" %% "akka-http"             % akkaHttpVersion
lazy val akkaHSpray:  ModuleID = "com.typesafe.akka" %% "akka-http-spray-json"  % akkaHttpVersion
lazy val akkaHTest:   ModuleID = "com.typesafe.akka" %% "akka-http-testkit"     % akkaHttpVersion

//LOGGING
lazy val logging: ModuleID = "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion

// TESTING
lazy val scalaTest: ModuleID = "org.scalatest" %% "scalatest" % scalaTestVersion// % Test

//COMPILER
/*
 * Eliminates useless, unintuitive, and sometimes broken additions of `withFilter`
 * when using generator arrows in for comprehensions. e.g.
 *
 * Vanila scala:
 * {{{
 *   for {
 *      x: Int <- readIntIO
 *      //
 *   } yield ()
 *   // instead of being `readIntIO.flatMap(x: Int => ...)`, it's something like .withFilter {case x: Int}, which is tantamount to
 *   // a runtime instanceof check. Absolutely horrible, and ridiculous, and unintuitive, and contrary to the often-
 *   // parroted mantra of "a for is just sugar for flatMap and map
 * }}}
 *
 * https://github.com/oleg-py/better-monadic-for
 */
lazy val betterMonadicForVersion = "0.3.0-M4"
lazy val betterMonadicFor        = "com.olegpy" %% "better-monadic-for" % betterMonadicForVersion

//=============================================================================
//=============================================================================
//=============================================================================
//=============================================================================

def commonSettings = Seq(
  organization in ThisBuild := "AIBuild",
  scalaVersion              := mainScalaVersion,
  scalacOptions ++= customScalaCompileFlagList ++ betterForPluginCompilerFlags,
  addCompilerPlugin(dependency = betterMonadicFor),
)

//=============================================================================
//=============================================================================
//============================== COMPILER FLAGS ===============================
//=============================================================================
//=============================================================================

def allCompilerFlags: Seq[String] = customScalaCompileFlagList ++ betterForPluginCompilerFlags

/**
  * tpolecat's glorious compile flag list:
  * https://tpolecat.github.io/2017/04/25/scalac-flags.html
  */
def customScalaCompileFlagList: Seq[String] = Seq(
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-encoding",
  "utf-8", // Specify character encoding used by source files.
  "-explaintypes", // Explain type errors in more detail.
  "-feature", // Emit warning and location for usages of features that should be imported explicitly.
  "-language:existentials", // Existential types (besides wildcard types) can be written and inferred
  "-language:higherKinds", // Allow higher-kinded types
  "-language:implicitConversions", // Allow definition of implicit functions called views
  "-unchecked", // Enable additional warnings where generated code depends on assumptions.
  "-Xcheckinit", // Wrap field accessors to throw an exception on uninitialized access.
  "-Xfuture", // Turn on future language features.
  "-Xlint:adapted-args", // Warn if an argument list is modified to match the receiver.
  "-Xlint:by-name-right-associative", // By-name parameter of right associative operator.
  "-Xlint:constant", // Evaluation of a constant arithmetic expression results in an error.
  "-Xlint:delayedinit-select", // Selecting member of DelayedInit.
  "-Xlint:doc-detached", // A Scaladoc comment appears to be detached from its element.
  "-Xlint:inaccessible", // Warn about inaccessible types in method signatures.
  "-Xlint:infer-any", // Warn when a type argument is inferred to be `Any`.
  "-Xlint:missing-interpolator", // A string literal appears to be missing an interpolator id.
  "-Xlint:nullary-override", // Warn when non-nullary `def f()' overrides nullary `def f'.
  "-Xlint:nullary-unit", // Warn when nullary methods return Unit.
  "-Xlint:option-implicit", // Option.apply used implicit view.
  "-Xlint:package-object-classes", // Class or object defined in package object.
  "-Xlint:poly-implicit-overload", // Parameterized overloaded implicit methods are not visible as view bounds.
  "-Xlint:private-shadow", // A private field (or class parameter) shadows a superclass field.
  "-Xlint:stars-align", // Pattern sequence wildcard must align with sequence component.
  "-Xlint:type-parameter-shadow", // A local type parameter shadows a type already in scope.
  "-Xlint:unsound-match", // Pattern match may not be typesafe.
  "-Yno-adapted-args", // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
  "-Ywarn-extra-implicit", // Warn when more than one implicit parameter section is defined.
  "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures.
  "-Ywarn-infer-any", // Warn when a type argument is inferred to be `Any`.
  "-Ywarn-nullary-override", // Warn when non-nullary `def f()' overrides nullary `def f'.
  "-Ywarn-nullary-unit", // Warn when nullary methods return Unit.
  "-Ywarn-numeric-widen", // Warn when numerics are widened.
  "-Ywarn-unused:implicits", // Warn if an implicit parameter is unused.
  "-Ywarn-unused:imports", // Warn if an import selector is not referenced.
  "-Ywarn-unused:locals", // Warn if a local definition is unused.
  "-Ywarn-unused:params", // Warn if a value parameter is unused.
  "-Ywarn-unused:patvars", // Warn if a variable bound in a pattern is unused.
  "-Ywarn-unused:privates", // Warn if a private member is unused.
  "-Ywarn-value-discard", // Warn when non-Unit expression results are unused.
  "-Ypartial-unification" // Enable partial unification in type constructor inference

  //"-Xfatal-warnings",                  // Fail the compilation if there are any warnings.

  //THESE are specific to typelevel scala 4 — check back once they get into core scala
  //"-Yinduction-heuristics",            // speeds up the compilation of inductive implicit resolution
  //"-Yliteral-types",                   // literals can appear in type position
  //"-Xstrict-patmat-analysis",          // more accurate reporting of failures of match exhaustivity
  //"-Xlint:strict-unsealed-patmat",     // warn on inexhaustive matches against unsealed traits
  //"-Ykind-polymorphism",               // type and method definitions with type parameters of arbitrary kinds
)

/**
  * These are flags specific to the "better-monadic-for" plugin:
  * https://github.com/oleg-py/better-monadic-for
  */
def betterForPluginCompilerFlags: Seq[String] = Seq(
  "-P:bm4:no-filtering:y", // see https://github.com/oleg-py/better-monadic-for#desugaring-for-patterns-without-withfilters--pbm4no-filteringy
  "-P:bm4:no-map-id:y", // see https://github.com/oleg-py/better-monadic-for#final-map-optimization--pbm4no-map-idy
  "-P:bm4:no-tupling:y", // see https://github.com/oleg-py/better-monadic-for#desugar-bindings-as-vals-instead-of-tuples--pbm4no-tuplingy,
  "-P:bm4:implicit-patterns:y" //see https://github.com/oleg-py/better-monadic-for#define-implicits-in-for-comprehensions-or-matches
)
