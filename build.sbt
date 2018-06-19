
name := "object-persistence"

version := "0.1"

scalaVersion := "2.12.6"

resolvers += "chenhuanming Maven Repository" at "https://raw.githubusercontent.com/zerouwar/my-maven-repo/master"

val akkaHttpV = "10.1.1"

val akkaV = "2.5.12"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaV,
  "com.typesafe.akka" %% "akka-stream" % akkaV,
  "com.typesafe.akka" %% "akka-http" % akkaHttpV,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpV,
  "com.qiniu" % "qiniu-java-sdk" % "7.2.12",
  "cn.chenhuanming" % "hooks-server" % "0.0.1",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test
)

enablePlugins(JavaAppPackaging)


