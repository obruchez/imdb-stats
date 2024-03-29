name := "imdb-stats"

version := "1.1"

scalaVersion := "2.13.8"

scalafmtOnCompile := true

libraryDependencies += "com.univocity" % "univocity-parsers" % "2.9.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % Test
libraryDependencies += "org.scalatest" %% "scalatest-flatspec" % "3.2.11" % Test
