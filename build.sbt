ThisBuild / version := "0.1.3"
ThisBuild / organization := "com.github.jordanburke"
ThisBuild / scalaVersion := "3.3.3"

val Specs2Version = "5.5.8"

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
  "ch.qos.logback" % "logback-classic" % "1.5.15" % Runtime,
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
  "io.github.cdimascio" % "dotenv-java" % "3.1.0",
  "org.specs2" %% "specs2-core" % Specs2Version % Test,
)
