ThisBuild / version := "0.1.0"
ThisBuild / organization := "com.github.jordanburke"
ThisBuild / scalaVersion := "3.3.3"

val Specs2Version = "5.5.1"

lazy val root = (project in file("."))
  .settings(
    name := "scala-dotenv",
    publishTo := {
      val ghRepo = "https://maven.pkg.github.com/jordanburke/scala-dotenv"
      if (isSnapshot.value)
        Some("GitHub Package Registry" at ghRepo)
      else
        Some("GitHub Package Registry" at ghRepo)
    },
    credentials += Credentials(
      "GitHub Package Registry",
      "maven.pkg.github.com",
      sys.env.getOrElse("GITHUB_ACTOR", ""),
      sys.env.getOrElse("GITHUB_TOKEN", "")
    ),
    publishMavenStyle := true
  )

libraryDependencies ++= Seq(
  "org.log4s" %% "log4s" % "1.10.0",
  "io.github.cdimascio" % "dotenv-java" % "3.0.0",
  "org.specs2" %% "specs2-core" % Specs2Version % Test,
)