name := "scala-fix"

version := "0.0.1"

lazy val commonSettings = Seq(
  organization := "com.github.solverit",
  version := "0.0.1",
  scalaVersion := "2.12.0",

  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8", // 2 args
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-unchecked",
    "-Xfatal-warnings",
    // "-Xlint",
    "-Yno-adapted-args",
    // "-Ywarn-dead-code", // N.B. doesn't work well with the ??? hole
    // "-Ywarn-numeric-widen",
    // "-Ywarn-value-discard",
    "-Ywarn-unused-import",
    "-Xfuture"
  )
)

lazy val libDependencies = Seq(
  "org.scalactic" %% "scalactic" % "3.0.0"
)

lazy val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.0.0" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
)

lazy val root = project.in(file("."))
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= (libDependencies ++ testDependencies))
//  .enablePlugins(scalafix.sbt.FixProtocolPlugin)


