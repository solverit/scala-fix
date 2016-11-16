package scalafix.sbt

import sbt.Keys._
import sbt._
//import complete.DefaultParsers._
//import java.io.{File => JFile}
//import java.net.URLClassLoader

//import scalafix.core.FixParser

object FixProtocolPlugin extends AutoPlugin {

  object autoImport {
      val fixGenerator = TaskKey[List[File]]("fix-generate", "Generate FIX Protocol Classes")
  }

  import autoImport._

  override lazy val projectSettings = Seq(
    fixGenerator := {
      val file = (sourceManaged in Compile).value / "demo" / "Test.scala"
      IO.write(file, """object Test extends App { println("Hi") }""")

      List(file)

//      val args = spaceDelimited("").parsed
//      val cp: Seq[File] = (fullClasspath in Runtime).value.files
//      val cpUrls = cp.map(_.asURL).toArray
//      val cl = new URLClassLoader(cpUrls, ClassLoader.getSystemClassLoader)

//      FixParser.generateFromFiles(cl)
    }

  )

}