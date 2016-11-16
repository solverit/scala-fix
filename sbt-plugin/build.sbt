name := "scala-fix-sbt"

version := "0.0.1"

lazy val commonSettings = Seq(
  organization := "com.github.solverit",
  version := "0.0.1",
  scalaVersion := "2.10.6",
  sbtPlugin := true,
  sbtVersion := "0.13.13",

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
    // "-Ywarn-unused-import",
    "-Xfuture"
  )
)

lazy val libDependencies = Seq(

)

lazy val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
)

lazy val sbtplugin = project.in(file("."))
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= (libDependencies ++ testDependencies))
