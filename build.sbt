name := "imdb-stats"

version := "1.0"

scalaVersion := "2.12.4"

scalafmtOnCompile in ThisBuild := true

libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.11.6"
libraryDependencies += "com.univocity" % "univocity-parsers" % "2.5.9"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"
