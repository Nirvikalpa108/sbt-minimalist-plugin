lazy val root = (project in file("."))
  .enablePlugins(SbtPlugin)
  .settings(
    greetingName := "Amina",
    sayVoice := "Alice",
  )