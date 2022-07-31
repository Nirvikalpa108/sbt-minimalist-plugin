import sbt.AutoPlugin
import sbt._

object MinimalPlugin extends AutoPlugin { //Autoplugin puts dependencies in the right order for us.
  override def trigger = allRequirements //plugin doesn't require manual trigger

  object autoImport {
    val hello = taskKey[Unit]("greeting")
  }

  import autoImport._
  override lazy val globalSettings = List(
    hello := println("Hello World!")
  )
}

