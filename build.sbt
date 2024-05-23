ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.3"

val Specs2Version = "5.5.1"

lazy val root = (project in file("."))
  .settings(
    name := "scala-dotenv"
  )

libraryDependencies ++= Seq(
  "org.log4s" %% "log4s" % "1.10.0",
  "io.github.cdimascio" % "dotenv-java" % "3.0.0",
  "org.specs2" %% "specs2-core" % Specs2Version % Test,
)