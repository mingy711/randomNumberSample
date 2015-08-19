import play.PlayScala
import scalariform.formatter.preferences._
import com.typesafe.sbt.SbtScalariform.ScalariformKeys

name := """celtra-ad-api"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

resolvers ++= Seq("Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "Rhinofly Internal Release Repository" at "http://maven-repository.rhinofly.net:8081/artifactory/libs-release-local",
  "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype Release" at "https://oss.sonatype.org/content/repositories/release/")

libraryDependencies ++= Seq(
  cache,
  ws,
  "commons-io" % "commons-io" % "2.4",
  "org.scaldi" %% "scaldi-play" % "0.3.3",
  "org.scalaz" %% "scalaz-core" % "7.0.6",
  "org.reactivemongo" %% "play2-reactivemongo" % "0.10.5.akka23-SNAPSHOT" excludeAll ExclusionRule(organization = "org.apache.log4j"),
  "org.mongodb" % "mongo-java-driver" % "2.12.1",
  "log4j" % "log4j" % "1.2.17",
  "log4j" % "apache-log4j-extras" % "1.2.17"
)

//addCompilerPlugin("com.foursquare.lint" %% "linter" % "0.1.11")

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-language:reflectiveCalls",
  "-language:implicitConversions", "-language:postfixOps", "-language:dynamics","-language:higherKinds",
  "-language:existentials", "-language:experimental.macros", "-Xmax-classfile-name", "140"
)


scalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(DoubleIndentClassDeclaration, true)
  .setPreference(PreserveDanglingCloseParenthesis, true)
  .setPreference(AlignParameters, true)
  .setPreference(IndentLocalDefs, true)

//scalacOptions += "-Xplugin:lib/linter_2.10-0.1-SNAPSHOT.jar"
