name := """play-demo"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.8"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "com.typesafe.play" %% "play-slick" % "5.0.2"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "5.0.2"
libraryDependencies += "org.postgresql" % "postgresql" % "42.3.3"