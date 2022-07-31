import sbt.AutoPlugin
import sbt._

object MinimalPlugin extends AutoPlugin { //Autoplugin puts dependencies in the right order for us.
  override def trigger = allRequirements //plugin doesn't require manual trigger
}

