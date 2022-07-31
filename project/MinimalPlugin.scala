import sbt.AutoPlugin
import sbt.Keys._
import sbt._

import scala.sys.process.Process

object MinimalPlugin extends AutoPlugin { //Autoplugin puts dependencies in the right order for us.
  override def trigger = allRequirements //plugin doesn't require manual trigger

  object autoImport {
    val hello = taskKey[Unit]("greeting")
    val greetingName = settingKey[String]("name to address greeting to")
    val sayHello = taskKey[Unit]("say greeting")
    val sayVoice = settingKey[String]("voice to say hello in")
    val sayTestsPass = taskKey[Unit]("congratulatory phrase celebrating passing tests")
    val sayTestsFailOrError = taskKey[Unit]("celebrate tests failing or error-ing")
    val testResultOutcome = Def.taskDyn {
      if ((Test / executeTests).value.overall == TestResult.Passed) Def.task(sayTestsPass.value)
      else Def.task(sayTestsFailOrError.value)
    }
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
    },
    sayTestsPass := {
      val voice = sayVoice.value
      Process(s"say -v $voice Congratulations, your tests passed").!!
    },
    sayTestsFailOrError := {
      val voice = sayVoice.value
      Process(s"say -v $voice Better luck next time").!!
    },
    Test / test := {
      val testTask = (Test / test).value
      testResultOutcome.value
      testTask
    },
  )
}

