name := "KataPokerHands"

version := "1.0"

scalaVersion := "2.11.7"

val parserCombinator = "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.3"
val scalaTest = "org.scalatest" %% "scalatest" % "2.2.4" % "test"

libraryDependencies ++= Seq(parserCombinator, scalaTest)

scalacOptions ++= Seq( "-unchecked", "-deprecation" )

