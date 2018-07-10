val common = Seq(
  version := "1.0",
  scalaVersion := "2.12.6"
)

lazy val root = (project in file("."))
  .settings(common)
  .settings(
    name := "sbt-assignment",
    publishArtifact := false,
    libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.6.4"
  )
  .dependsOn(db, dto)
  .aggregate(db, dto)

lazy val db = (project in file("db-operations"))
  .settings(common)
  .settings(
    name := "db",
    libraryDependencies ++= Seq(
      "com.typesafe.slick" %% "slick" % "3.2.0",
      "com.h2database" % "h2" % "1.4.196",
      "org.scalatest" %% "scalatest" % "3.0.5" % "test"
    )
  ).dependsOn(dto)

lazy val dto = (project in file("dto"))
  .settings(common)
  .settings(
    name := "dto",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.5" % "test"
    )
  )