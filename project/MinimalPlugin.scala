import sbt.AutoPlugin
import sbt._

import scala.sys.process.Process

object MinimalPlugin extends AutoPlugin { //Autoplugin puts dependencies in the right order for us.
  override def trigger = allRequirements //plugin doesn't require manual trigger

  object autoImport {
    val hello = taskKey[Unit]("greeting")
    val greetingName = settingKey[String]("name to address greeting to")
    val sayHello = taskKey[Unit]("say greeting")
    val sayVoice = settingKey[String]("voice to say hello in")
  }

  import autoImport._
  override lazy val globalSettings = List(
    greetingName := "Coco",
    sayVoice := "Fiona",
  )

  override lazy val projectSettings = List(
    hello := println(s"Hello ${greetingName.value}"),
    sayHello := {
      val voice = sayVoice.value
      Process(s"say -v $voice Hello World").!!
    }
  )
}

